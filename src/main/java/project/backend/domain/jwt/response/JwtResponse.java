package project.backend.domain.jwt.response;

import lombok.Builder;
import lombok.Getter;
import project.backend.domain.member.dto.MemberResponseDto;

@Builder
@Getter
public class JwtResponse {
    private TokenResponse token;
    private MemberResponseDto member;
}
