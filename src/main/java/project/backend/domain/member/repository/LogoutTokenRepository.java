package project.backend.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.backend.domain.member.entity.LogoutToken;

import java.util.List;

public interface LogoutTokenRepository extends JpaRepository<LogoutToken, Long> {
    List<LogoutToken> findAllByToken(String token);
}
