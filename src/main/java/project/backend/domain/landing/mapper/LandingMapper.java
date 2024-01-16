package project.backend.domain.landing.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import project.backend.domain.landing.dto.LandingResponseDto;
import project.backend.domain.landing.entity.Landing;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LandingMapper {
    LandingResponseDto landingToLandingResponseDto(Landing landing);
}
