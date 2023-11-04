package project.backend.domain.ticket.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.backend.domain.ticket.dto.TicketPatchRequestDto;
import project.backend.domain.ticket.dto.TicketPostRequestDto;
import project.backend.domain.ticket.entity.Ticket;
import project.backend.domain.ticket.mapper.TicketMapper;
import project.backend.domain.ticket.service.TicketService;
import project.backend.domain.ticket.dto.TicketResponseDto;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;
    private final TicketMapper ticketMapper;


    @PostMapping
    public ResponseEntity postTicket(@RequestBody TicketPostRequestDto ticketPostRequestDto) {
        Ticket ticket = ticketService.createTicket(ticketPostRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(ticketMapper.ticketToTicketResponseDto(ticket));
    }

    @GetMapping("/{ticketId}")
    public ResponseEntity getTicket(@PathVariable Long ticketId) {
        TicketResponseDto ticketResponseDto = ticketMapper.ticketToTicketResponseDto(ticketService.getTicket(ticketId));
        return ResponseEntity.status(HttpStatus.OK).body(ticketResponseDto);
    }

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
