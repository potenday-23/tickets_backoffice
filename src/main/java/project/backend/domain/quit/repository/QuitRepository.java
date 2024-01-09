package project.backend.domain.quit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.backend.domain.quit.entity.Quit;

public interface QuitRepository extends JpaRepository<Quit, Long> {
}
