package project.backend.domain.member.dto;

import lombok.*;
import project.backend.domain.member.entity.SocialType;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberPatchRequestDto {
    @Size(min=2, max=10, message = "2~10자 이내로 입력해주세요.")
    @Pattern(regexp = "^[ㄱ-ㅎ가-힣a-zA-Z0-9-_ ]*$", message = "특수문자는 사용할 수 없어요.")
    public String nickname;
    public String profileUrl;
    public String refreshToken;
}
