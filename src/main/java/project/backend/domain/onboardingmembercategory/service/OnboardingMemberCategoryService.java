package project.backend.domain.onboardingmembercategory.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.backend.domain.category.entity.Category;
import project.backend.domain.member.entity.Member;
import project.backend.domain.onboardingmembercategory.entity.OnboardingMemberCategory;
import project.backend.domain.onboardingmembercategory.repository.OnboardingMemberCategoryRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class OnboardingMemberCategoryService {

    private final OnboardingMemberCategoryRepository onboardingMemberCategoryRepository;

    public OnboardingMemberCategory createOnboardingMemberCategory(Member member, Category category) {
        OnboardingMemberCategory onboardingMemberCategory = OnboardingMemberCategory.builder().member(member).category(category).build();
        onboardingMemberCategory.setOnboardingMemberCategory(member, category);
        onboardingMemberCategoryRepository.save(onboardingMemberCategory);
        return onboardingMemberCategory;
    }

}
