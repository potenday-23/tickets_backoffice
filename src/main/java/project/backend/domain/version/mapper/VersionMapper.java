package project.backend.domain.version.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import project.backend.domain.version.dto.VersionPostRequestDto;
import project.backend.domain.version.dto.VersionResponseDto;
import project.backend.domain.version.entity.Version;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface VersionMapper {
    VersionResponseDto versionToVersionResponseDto(Version version);
}
