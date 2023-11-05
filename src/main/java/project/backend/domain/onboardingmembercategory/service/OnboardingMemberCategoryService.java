package project.backend.domain.onboardingmembercategory.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.backend.domain.category.entity.Category;
import project.backend.domain.member.entity.Member;
import project.backend.domain.onboardingmembercategory.entity.OnboardingMemberCategory;
import project.backend.domain.onboardingmembercategory.repository.OnboardingMemberCategoryRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class OnboardingMemberCategoryService {

    private final OnboardingMemberCategoryRepository onboardingMemberCategoryRepository;

    public OnboardingMemberCategory createOnboardingMemberCategory(Member member, Category category) {
        OnboardingMemberCategory onboardingMemberCategory = new OnboardingMemberCategory();
        onboardingMemberCategory.setOnboardingMemberCategory(member, category);
        onboardingMemberCategoryRepository.save(onboardingMemberCategory);
        return onboardingMemberCategory;
    }

    public void deleteOnboardingMemberCategory(OnboardingMemberCategory onboardingMemberCategory) {
        onboardingMemberCategoryRepository.delete(onboardingMemberCategory);
    }

    public void deleteOnboardingMemberCategoryByMember(Member member) {
        onboardingMemberCategoryRepository.deleteAll(findOnboardingMemberCategoryByMember(member));
    }

    private List<OnboardingMemberCategory> findOnboardingMemberCategoryByMember(Member member) {
        return onboardingMemberCategoryRepository.findAllByMember(member);
    }

}
