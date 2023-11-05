package project.backend.domain.category.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.backend.domain.category.entity.Category;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findFirstByName(String name);
}
