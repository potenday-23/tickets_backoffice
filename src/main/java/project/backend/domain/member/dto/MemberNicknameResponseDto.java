package project.backend.domain.member.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberNicknameResponseDto {

    private String message;
    private String nickname;
}
