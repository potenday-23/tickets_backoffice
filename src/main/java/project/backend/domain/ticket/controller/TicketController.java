package project.backend.domain.ticket.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import project.backend.domain.jwt.service.JwtService;
import project.backend.domain.member.entity.Member;
import project.backend.domain.ticket.dto.TicketPatchRequestDto;
import project.backend.domain.ticket.dto.TicketPostRequestDto;
import project.backend.domain.ticket.entity.Ticket;
import project.backend.domain.ticket.mapper.TicketMapper;
import project.backend.domain.ticket.service.TicketService;
import project.backend.domain.ticket.dto.TicketResponseDto;
import project.backend.global.s3.service.ImageService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/tickets")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;
    private final TicketMapper ticketMapper;
    private final ImageService imageService;
    private final JwtService jwtService;

    @RequestMapping(method=RequestMethod.POST, consumes="multipart/form-data")
    public ResponseEntity postTicket(
            @RequestHeader("Authorization") String accessToken,
            @RequestPart(value="image") MultipartFile image,
            @RequestPart(value="ticketImage") MultipartFile ticketImage,
            @Valid @RequestPart TicketPostRequestDto request) {

        // image, ticketImage 등록
        request.setImageUrl(imageService.updateImage(image, "Ticket", "imageUrl"));
        request.setTicketImageUrl(imageService.updateImage(ticketImage, "Ticket", "ticketImageURl"));

        // 작성자 등록
        request.setMember(jwtService.getMemberFromAccessToken(accessToken));

        // Ticket 생성
        Ticket ticket = ticketService.createTicket(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(ticketMapper.ticketToTicketResponseDto(ticket));
    }

    @GetMapping("/{ticketId}")
    public ResponseEntity getTicket(@PathVariable Long ticketId) {
        TicketResponseDto ticketResponseDto = ticketMapper.ticketToTicketResponseDto(ticketService.getTicket(ticketId));
        return ResponseEntity.status(HttpStatus.OK).body(ticketResponseDto);
    }

    /**
     * 회원 인증 받지 않아도 조회 가능한 api
     * @return
     */
    @GetMapping
    public ResponseEntity getTicketList(
            @RequestParam(value = "categorys", required = false) List<String> categorys,
            @RequestParam(value = "period", required = false) String period // 일주일, 한달, 6개월, 기간, 하루
    ) {
        List<Ticket> ticketList = ticketService.getTicketList(categorys);
        List<TicketResponseDto> ticketResponseDtoList = ticketMapper.ticketsToTicketResponseDtos(ticketList);
        return ResponseEntity.status(HttpStatus.OK).body(ticketResponseDtoList);
    }

    @PatchMapping("/{ticketId}")
    public ResponseEntity putTicket(
            @PathVariable Long ticketId,
            @RequestBody TicketPatchRequestDto ticketPatchRequestDto) {
        TicketResponseDto ticketResponseDto = ticketMapper.ticketToTicketResponseDto(ticketService.patchTicket(ticketId, ticketPatchRequestDto));
        return ResponseEntity.status(HttpStatus.OK).body(ticketResponseDto);
    }

    @DeleteMapping("/{ticketId}")
    public ResponseEntity deleteTicket(@PathVariable Long ticketId) {
        ticketService.deleteTicket(ticketId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
