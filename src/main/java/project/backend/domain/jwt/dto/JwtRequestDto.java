package project.backend.domain.jwt.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class JwtRequestDto {
    public String userId;
    public String profileUrl;
}
