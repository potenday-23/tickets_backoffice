package project.backend.domain.quit.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuitPostRequestDto {
    public String reason;
}