package project.backend.domain.ticket.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import project.backend.domain.ticket.entity.IsPrivate;
import project.backend.domain.ticket.entity.Ticket;

import java.util.List;

import static project.backend.domain.ticket.entity.QTicket.ticket;
import static project.backend.domain.category.entity.QCategory.category;

@RequiredArgsConstructor
public class TicketRepositoryImpl implements TicketRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<Ticket> getTicketList(List<String> categorys) {
        if (categorys == null) {
            return queryFactory.selectFrom(ticket)
                    .where(ticket.isPrivate.eq(IsPrivate.PUBLIC))
                    .orderBy(ticket.ticketDate.desc())
                    .fetch();
        }

        // Category List가 null이 아닌 경우
        else {
            return queryFactory.selectFrom(ticket)
                    .where(ticket.isPrivate.eq(IsPrivate.PUBLIC), ticket.category.name.in(categorys))
                    .orderBy(ticket.ticketDate.desc())
                    .fetch();
        }
    }
}
