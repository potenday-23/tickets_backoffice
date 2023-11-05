package project.backend.domain.ticket.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.backend.domain.category.service.CategoryService;
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
    private final CategoryService categoryService;

    public Ticket createTicket(TicketPostRequestDto ticketPostRequestDto) {
        Ticket ticket = Ticket.builder()
                .imageUrl(ticketPostRequestDto.imageUrl)
                .ticketImageUrl(ticketPostRequestDto.ticketImageUrl)
                .ticketDate(ticketPostRequestDto.ticketDate)
                .rating(ticketPostRequestDto.rating)
                .memo(ticketPostRequestDto.memo)
                .seat(ticketPostRequestDto.seat)
                .location(ticketPostRequestDto.location)
                .price(ticketPostRequestDto.price)
                .friend(ticketPostRequestDto.friend)
                .isPrivate(ticketPostRequestDto.isPrivate)
                .build();

        // 연관관계 매핑
        ticket.setMember(ticketPostRequestDto.member);
        ticket.setCategory(categoryService.verifiedCategory(ticketPostRequestDto.categoryName));

        // 저장
        ticketRepository.save(ticket);
        return ticket;
    }

    public Ticket getTicket(Long id) {
        return verifiedTicket(id);
    }

    public List<Ticket> getTicketList() {
        return ticketRepository.getTicketList();
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
        return ticketRepository.findById(id).orElseThrow(() -> new BusinessException(ErrorCode.TICKET_NOT_FOUND));
    }

}
