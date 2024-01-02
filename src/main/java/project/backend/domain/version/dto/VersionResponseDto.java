package project.backend.domain.version.dto;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VersionResponseDto {
    public Long id;
    public String version;
    public LocalDateTime createdDate;
    public LocalDateTime updatedDate;
}