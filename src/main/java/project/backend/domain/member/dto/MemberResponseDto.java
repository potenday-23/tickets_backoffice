package project.backend.domain.member.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberResponseDto {
    public Long id;
    public String nickname;
    public String profileUrl;
}
