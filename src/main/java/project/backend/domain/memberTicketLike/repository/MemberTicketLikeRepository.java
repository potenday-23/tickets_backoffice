package project.backend.domain.memberTicketLike.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.backend.domain.memberTicketLike.entity.MemberTicketLike;

public interface MemberTicketLikeRepository extends JpaRepository<MemberTicketLike, Long> {
}
