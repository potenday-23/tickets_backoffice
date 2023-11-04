package project.backend.domain.ticket.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.backend.domain.ticket.dto.TicketPatchRequestDto;
import project.backend.domain.ticket.dto.TicketPostRequestDto;
import project.backend.domain.ticket.entity.Ticket;
import project.backend.domain.ticket.mapper.TicketMapper;
import project.backend.domain.ticket.repository.TicketRepository;
import project.backend.global.error.exception.BusinessException;
import project.backend.global.error.exception.ErrorCode;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class TicketService {
    private final TicketRepository ticketRepository;
    private final TicketMapper ticketMapper;

    public Ticket createTicket(TicketPostRequestDto ticketPostRequestDto){
        Ticket ticket = Ticket.builder().socialId(ticketPostRequestDto.socialId).build();
        ticketRepository.save(ticket);
        return ticket;
    }

    public Ticket getTicket(Long id) {
        return verifiedTicket(id);
    }

    public List<Ticket> getTicketList() {
        return ticketRepository.findAll();
    }

    public Ticket patchTicket(Long id, TicketPatchRequestDto ticketPatchRequestDto) {
        Ticket ticket = verifiedTicket(id).patchTicket(ticketPatchRequestDto);
        ticketRepository.save(ticket);
        return ticket;
    }

    public void deleteTicket(Long id) {
        ticketRepository.delete(verifiedTicket(id));
    }

    private Ticket verifiedTicket(Long id) {
        return ticketRepository.findById(id).orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));
    }

}
