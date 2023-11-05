package project.backend.domain.member.dto;

import lombok.*;
import project.backend.domain.member.entity.SocialType;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor // todo : AllArgsConstructor는 무슨 뜻일까
public class MemberPatchRequestDto {
    public SocialType socialType;
    public String socialId;
}
