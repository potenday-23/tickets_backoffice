package project.backend.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.backend.domain.member.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
