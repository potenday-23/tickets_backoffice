package project.backend.domain.quit.dto;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuitResponseDto {
    public Long id;
    public String reason;
    public Integer count;
    public LocalDateTime createdDate;
    public LocalDateTime updatedDate;
}