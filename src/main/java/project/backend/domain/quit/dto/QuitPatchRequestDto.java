package project.backend.domain.quit.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuitPatchRequestDto {
    public String title;
    public String content;
}
