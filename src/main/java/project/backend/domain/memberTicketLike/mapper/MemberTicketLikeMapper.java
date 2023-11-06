package project.backend.domain.memberTicketLike.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import project.backend.domain.memberTicketLike.dto.MemberTicketLikePostRequestDto;
import project.backend.domain.memberTicketLike.dto.MemberTicketLikeResponseDto;
import project.backend.domain.memberTicketLike.entity.MemberTicketLike;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MemberTicketLikeMapper {
    MemberTicketLike memberTicketLikePostRequestDtoToMemberTicketLike(MemberTicketLikePostRequestDto memberTicketLikePostRequestDto);

    MemberTicketLikeResponseDto memberTicketLikeToMemberTicketLikeResponseDto(MemberTicketLike memberTicketLike);

    List<MemberTicketLikeResponseDto> memberTicketLikesToMemberTicketLikeResponseDtos(List<MemberTicketLike> memberTicketLike);
}
