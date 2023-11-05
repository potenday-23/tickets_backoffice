package project.backend.domain.category.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import project.backend.domain.category.dto.CategoryResponseDto;
import project.backend.domain.category.entity.Category;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryMapper {

    CategoryResponseDto categoryToCategoryResponseDto(Category category);

    List<CategoryResponseDto> categorysToCategoryResponseDtos(List<Category> category);
}
