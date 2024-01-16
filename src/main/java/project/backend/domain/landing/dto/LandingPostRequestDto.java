package project.backend.domain.landing.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LandingPostRequestDto {
    public String email;
}