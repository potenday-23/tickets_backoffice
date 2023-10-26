package project.backend.domain.user.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.backend.domain.user.dto.UserPostRequestDto;
import project.backend.domain.user.dto.UserResponseDto;
import project.backend.domain.user.entity.User;
import project.backend.domain.user.mapper.UserMapper;
import project.backend.domain.user.service.UserService;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;


    @PostMapping
    public UserResponseDto postUser(@RequestBody UserPostRequestDto userPostRequestDto) {
        User user = userService.createUser(userPostRequestDto);
        return userMapper.userToUserResponseDto(user);
    }
}
