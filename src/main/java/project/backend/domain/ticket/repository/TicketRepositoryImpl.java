package project.backend.domain.ticket.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import project.backend.domain.ticket.entity.IsPrivate;
import project.backend.domain.ticket.entity.Ticket;

import java.time.LocalDateTime;
import java.util.List;

import static project.backend.domain.ticket.entity.QTicket.ticket;

@RequiredArgsConstructor
public class TicketRepositoryImpl implements TicketRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<Ticket> getTicketList(List<String> categorys, List<LocalDateTime> startAndEndList, String search) {
        if (categorys == null || categorys.size() == 0) {
            return queryFactory.selectFrom(ticket)
                    .where(ticket.isPrivate.eq(IsPrivate.PUBLIC),
                            ticket.title.contains(search),
                            ticket.ticketDate.between(startAndEndList.get(0), startAndEndList.get(1)))
                    .orderBy(ticket.ticketDate.desc())
                    .fetch();
        }

        else {
            return queryFactory.selectFrom(ticket)
                    .where(ticket.isPrivate.eq(IsPrivate.PUBLIC),
                            ticket.category.name.in(categorys),
                            ticket.title.contains(search),
                            ticket.ticketDate.between(startAndEndList.get(0), startAndEndList.get(1))
                    )
                    .orderBy(ticket.ticketDate.desc())
                    .fetch();
        }
    }
}
