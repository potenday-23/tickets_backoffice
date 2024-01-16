package project.backend.domain.landing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.backend.domain.landing.entity.Landing;

import java.util.Optional;

public interface LandingRepository extends JpaRepository<Landing, Long> {
    Optional<Landing> findFirstByOrderByCreatedDateDesc();
}
