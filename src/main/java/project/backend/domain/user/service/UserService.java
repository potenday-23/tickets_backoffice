package project.backend.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.backend.domain.user.dto.UserPostRequestDto;
import project.backend.domain.user.entity.SocialType;
import project.backend.domain.user.entity.User;
import project.backend.domain.user.repository.UserRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final UserRepository userRepository;

    public User createUser(UserPostRequestDto userPostRequestDto){

        User user = User.builder().socialType(SocialType.KAKAO).socialId(userPostRequestDto.socialId).build();
        userRepository.save(user);
        return user;
    }

}
