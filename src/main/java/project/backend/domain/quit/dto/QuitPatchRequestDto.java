package project.backend.domain.quit.dto;

import lombok.*;

import javax.persistence.Column;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuitPatchRequestDto {
    public String reason;
    public Integer count;
}
