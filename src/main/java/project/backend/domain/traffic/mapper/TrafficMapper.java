package project.backend.domain.traffic.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import project.backend.domain.traffic.dto.TrafficResponseDto;
import project.backend.domain.traffic.entity.Traffic;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TrafficMapper {
    TrafficResponseDto trafficToTrafficResponseDto(Traffic traffic);
}
