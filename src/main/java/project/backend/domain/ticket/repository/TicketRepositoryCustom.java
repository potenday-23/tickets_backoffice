package project.backend.domain.ticket.repository;

import project.backend.domain.category.entity.Category;
import project.backend.domain.ticket.dto.TicketResponseDto;
import project.backend.domain.ticket.entity.Ticket;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface TicketRepositoryCustom {

    List<Ticket> getTicketList(List<String> categorys);
}
