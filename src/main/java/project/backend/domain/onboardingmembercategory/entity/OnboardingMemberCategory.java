package project.backend.domain.onboardingmembercategory.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import project.backend.domain.category.entity.Category;
import project.backend.domain.member.entity.Member;
import project.backend.domain.ticket.entity.Ticket;

import javax.persistence.*;
import java.util.Optional;

@Entity
@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class OnboardingMemberCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "onboardingMemberCategoryId")
    public Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId")
    public Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoryId")
    public Category category;

    // == 생성자 == //
    @Builder
    public OnboardingMemberCategory(Member member, Category category) {
        this.member = member;
        this.category = category;
    }

    // == 연관관계 매핑 == //
    public void setMemberCategory(Member member, Category category) {
        if (this.member != null) {
            if (this.member.getOnboardingMemberCategories().contains(this)) {
                this.member.getOnboardingMemberCategories().remove(this);
            }
        }
        if (this.category != null) {
            if (this.category.getOnboardingMemberCategories().contains(this)) {
                this.category.getOnboardingMemberCategories().remove(this);
            }
        }
        this.member = Optional.ofNullable(member).orElse(this.member);
        this.member.getOnboardingMemberCategories().add(this);
        this.category = Optional.ofNullable(category).orElse(this.category);
        this.category.getOnboardingMemberCategories().add(this);
    }
}
