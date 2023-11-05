package project.backend.domain.onboardingmembercategory.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import project.backend.domain.category.dto.CategoryResponseDto;
import project.backend.domain.category.entity.Category;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OnboardingMemberCategoryMapper {
}
