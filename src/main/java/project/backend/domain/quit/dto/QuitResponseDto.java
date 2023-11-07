package project.backend.domain.quit.dto;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuitResponseDto {
    public String title;
    public String content;
    public LocalDateTime createdDate;
    public LocalDateTime updatedDate;
}