package project.backend.domain.ticket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.backend.domain.ticket.entity.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long>, TicketRepositoryCustom {
}
