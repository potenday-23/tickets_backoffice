package project.backend.domain.memberTicketLike.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.backend.domain.member.entity.Member;
import project.backend.domain.memberTicketLike.entity.MemberTicketLike;
import project.backend.domain.ticket.entity.Ticket;

import java.util.List;
import java.util.Optional;

public interface MemberTicketLikeRepository extends JpaRepository<MemberTicketLike, Long> {

    List<MemberTicketLike> findByMemberAndTicket(Member member, Ticket ticket);
}
