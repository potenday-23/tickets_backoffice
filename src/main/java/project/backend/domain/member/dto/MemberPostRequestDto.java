package project.backend.domain.member.dto;

import lombok.*;
import project.backend.domain.member.entity.SocialType;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberPostRequestDto {
    public SocialType socialType;
    public String socialId;
    public String nickname;
    public String profileUrl;
}
