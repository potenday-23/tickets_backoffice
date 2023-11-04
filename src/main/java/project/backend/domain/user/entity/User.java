package project.backend.domain.user.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import project.backend.domain.common.entity.BaseEntity;
import project.backend.domain.user.dto.UserPatchRequestDto;

import javax.persistence.*;
import java.util.Optional;

@Entity
@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // todo : IDENTITY와 AUTO 차이점이 뭔지?
    @Column(name = "user_id")
    public Long id;

    @Enumerated(value = EnumType.STRING)
    public SocialType socialType;

    @Column(name = "social_id")
    public String socialId;

    @Builder
    public User(SocialType socialType, String socialId){
        this.socialType = socialType;
        this.socialId = socialId;
    }

    // Patch
    public User patchUser(UserPatchRequestDto userPatchRequestDto){
        this.socialType = Optional.ofNullable(userPatchRequestDto.getSocialType()).orElse(this.socialType);
        this.socialId = Optional.ofNullable(userPatchRequestDto.getSocialId()).orElse(this.socialId);
        return this;
    }
}
