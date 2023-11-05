package project.backend.domain.ticket.repository;
import project.backend.domain.ticket.entity.Ticket;
import java.time.LocalDateTime;
import java.util.List;

public interface TicketRepositoryCustom {

    List<Ticket> getTicketList(List<String> categorys, List<LocalDateTime> startAndEndList);
}
