package project.backend.domain.category.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryPatchRequestDto {
    public String name;
    public String basicImage;
    public String clickImage;
}
