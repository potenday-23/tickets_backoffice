package project.backend.domain.version.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import project.backend.domain.version.entity.Version;

import java.util.List;
import java.util.Optional;

public interface VersionRepository extends JpaRepository<Version, Long> {
    Optional<Version> findFirstByOrderByCreatedDateDesc();
}
