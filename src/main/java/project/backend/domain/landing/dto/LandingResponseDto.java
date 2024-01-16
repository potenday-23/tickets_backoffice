package project.backend.domain.landing.dto;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LandingResponseDto {
    public Long id;
    public String email;
    public LocalDateTime createdDate;
    public LocalDateTime updatedDate;
}