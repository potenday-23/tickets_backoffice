package project.backend.domain.user.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import project.backend.domain.user.dto.UserPatchRequestDto;
import project.backend.domain.user.dto.UserPostRequestDto;
import project.backend.domain.user.dto.UserResponseDto;
import project.backend.domain.user.entity.User;

import java.util.List;

// todo: ReportingPolicy.IGNORE은 무슨 뜻일까?
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    User userPostRequestDtoToUser(UserPostRequestDto userPostRequestDto);

    User userPatchRequestDtoToUser(UserPatchRequestDto userPatchRequestDto);

    UserResponseDto userToUserResponseDto(User user);

    List<UserResponseDto> usersToUserResponseDtos(List<User> user);
}
