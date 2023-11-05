package project.backend.domain.member.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import project.backend.domain.memberTicketLike.entity.MemberTicketLike;
import project.backend.domain.common.entity.BaseEntity;
import project.backend.domain.ticket.entity.Ticket;
import project.backend.domain.member.dto.MemberPatchRequestDto;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // todo : IDENTITY와 AUTO 차이점이 뭔지?
    @Column(name = "memberId")
    public Long id;

    @Enumerated(value = EnumType.STRING)
    public SocialType socialType;

    @Column(name = "socialId")
    public String socialId;

    @OneToMany(mappedBy = "member", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Ticket> tickets = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    public List<MemberTicketLike> memberTicketLikes = new ArrayList<>();

    @Builder
    public Member(SocialType socialType, String socialId){
        this.socialType = socialType;
        this.socialId = socialId;
    }

    // Patch
    public Member patchMember(MemberPatchRequestDto memberPatchRequestDto){
        this.socialType = Optional.ofNullable(memberPatchRequestDto.getSocialType()).orElse(this.socialType);
        this.socialId = Optional.ofNullable(memberPatchRequestDto.getSocialId()).orElse(this.socialId);
        return this;
    }


}
