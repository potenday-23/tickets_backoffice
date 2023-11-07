package project.backend.domain.ticket.repository;
import project.backend.domain.member.dto.MemberStatisticsResponseDto;
import project.backend.domain.member.entity.Member;
import project.backend.domain.ticket.entity.Ticket;
import java.time.LocalDateTime;
import java.util.List;

public interface TicketRepositoryCustom {

    List<Ticket> getTotalTicketList(List<String> categorys, List<LocalDateTime> startAndEndList, String search);
    List<Ticket> getMyTicketList(List<String> categorys, List<LocalDateTime> startAndEndList, String search, Member member);

    List<MemberStatisticsResponseDto> getStatisticsList(Member member);

}
