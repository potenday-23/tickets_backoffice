package project.backend.domain.ticket.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import project.backend.domain.ticket.dto.TicketPatchRequestDto;
import project.backend.domain.ticket.dto.TicketPostRequestDto;
import project.backend.domain.ticket.entity.Ticket;
import project.backend.domain.ticket.mapper.TicketMapper;
import project.backend.domain.ticket.service.TicketService;
import project.backend.domain.ticket.dto.TicketResponseDto;
import project.backend.global.s3.service.ImageService;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;
    private final TicketMapper ticketMapper;
    private final ImageService imageService;

    @RequestMapping(method=RequestMethod.POST, consumes="multipart/form-data")
    public ResponseEntity postTicket(
            @RequestPart(value="image") MultipartFile image,
            @RequestPart(value="ticketImage") MultipartFile ticketImage,
            @RequestPart TicketPostRequestDto request) {

        // image, ticketImage 등록
        request.setImageUrl(imageService.updateImage(image, "Ticket", "imageUrl"));
        request.setTicketImageUrl(imageService.updateImage(ticketImage, "Ticket", "ticketImageURl"));

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
    public ResponseEntity getTicketList() {
        List<TicketResponseDto> ticketResponseDtoList = ticketMapper.ticketsToTicketResponseDtos(ticketService.getTicketList());
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
