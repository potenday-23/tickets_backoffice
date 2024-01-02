package project.backend.domain.version.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VersionPostRequestDto {
    public String version;
}