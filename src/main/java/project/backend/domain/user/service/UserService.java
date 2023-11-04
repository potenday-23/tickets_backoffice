package project.backend.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.backend.domain.user.dto.UserPostRequestDto;
import project.backend.domain.user.dto.UserPatchRequestDto;
import project.backend.domain.user.entity.SocialType;
import project.backend.domain.user.entity.User;
import project.backend.domain.user.mapper.UserMapper;
import project.backend.domain.user.repository.UserRepository;
import project.backend.global.error.exception.BusinessException;
import project.backend.global.error.exception.ErrorCode;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public User createUser(UserPostRequestDto userPostRequestDto){
        User user = User.builder().socialType(SocialType.KAKAO).socialId(userPostRequestDto.socialId).build();
        userRepository.save(user);
        return user;
    }

    public User getUser(Long id) {
        return verifiedUser(id);
    }

    public List<User> getUserList() {
        return userRepository.findAll();
    }

    public User patchUser(Long id, UserPatchRequestDto userPatchRequestDto) {
        User user = verifiedUser(id).patchUser(userPatchRequestDto);
        userRepository.save(user);
        return user;
    }

    public void deleteUser(Long id) {
        userRepository.delete(verifiedUser(id));
    }

    private User verifiedUser(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));
    }

}
