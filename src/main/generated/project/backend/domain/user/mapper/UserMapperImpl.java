package project.backend.domain.member.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import project.backend.domain.member.dto.UserPostRequestDto;
import project.backend.domain.member.dto.UserResponseDto;
import project.backend.domain.member.dto.UserResponseDto.UserResponseDtoBuilder;
import project.backend.domain.member.entity.User;
import project.backend.domain.member.entity.User.UserBuilder;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-10-27T00:41:05+0900",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.16.1 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User userPostRequestDtoToUser(UserPostRequestDto userPostRequestDto) {
        if ( userPostRequestDto == null ) {
            return null;
        }

        UserBuilder user = User.builder();

        user.socialType( userPostRequestDto.getSocialType() );
        user.socialId( userPostRequestDto.getSocialId() );

        return user.build();
    }

    @Override
    public UserResponseDto userToUserResponseDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserResponseDtoBuilder userResponseDto = UserResponseDto.builder();

        userResponseDto.id( user.getId() );
        userResponseDto.socialType( user.getSocialType() );
        userResponseDto.socialId( user.getSocialId() );

        return userResponseDto.build();
    }
}
