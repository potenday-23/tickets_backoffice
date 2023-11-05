package project.backend.domain.ticket.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import project.backend.domain.ticket.entity.IsPrivate;
import project.backend.domain.ticket.entity.Ticket;

import java.util.List;

import static project.backend.domain.ticket.entity.QTicket.ticket;

@RequiredArgsConstructor
public class TicketRepositoryImpl implements TicketRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<Ticket> getTicketList() {
        return queryFactory.selectFrom(ticket)
                .where(ticket.isPrivate.eq(IsPrivate.PUBLIC))
                .orderBy(ticket.ticketDate.asc())
                .fetch();
    }
}
