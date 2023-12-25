package project.backend.domain.category.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryPostRequestDto {
    public String name;
    public Integer num;
    public String basicImage;
    public String clickImage;
}
