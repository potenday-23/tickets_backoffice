package project.backend.domain.onboardingmembercategory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.backend.domain.category.entity.Category;
import project.backend.domain.onboardingmembercategory.entity.OnboardingMemberCategory;
import project.backend.domain.onboardingmembercategory.service.OnboardingMemberCategoryService;

import java.util.Optional;

public interface OnboardingMemberCategoryRepository extends JpaRepository<OnboardingMemberCategory, Long> {
}
