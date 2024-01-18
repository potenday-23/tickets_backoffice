package project.backend.domain.traffic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.backend.domain.traffic.entity.Traffic;

import java.util.Optional;

public interface TrafficRepository extends JpaRepository<Traffic, Long> {
    Optional<Traffic> findFirstByOrderByCreatedDateDesc();
}
