package project.backend.domain.user.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.backend.domain.user.dto.UserPostRequestDto;
import project.backend.domain.user.dto.UserResponseDto;
import project.backend.domain.user.dto.UserPatchRequestDto;
import project.backend.domain.user.entity.User;
import project.backend.domain.user.mapper.UserMapper;
import project.backend.domain.user.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;


    @PostMapping
    public ResponseEntity postUser(@RequestBody UserPostRequestDto userPostRequestDto) {
        User user = userService.createUser(userPostRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(userMapper.userToUserResponseDto(user));
    }

    @GetMapping("/{userId}")
    public ResponseEntity getUser(@PathVariable Long userId) {
        UserResponseDto userResponseDto = userMapper.userToUserResponseDto(userService.getUser(userId));
        return ResponseEntity.status(HttpStatus.OK).body(userResponseDto);
    }

    @GetMapping
    public ResponseEntity getUserList() {
        List<UserResponseDto> userResponseDtoList = userMapper.usersToUserResponseDtos(userService.getUserList());
        return ResponseEntity.status(HttpStatus.OK).body(userResponseDtoList);
    }

    @PatchMapping("/{userId}")
    public ResponseEntity putUser(
            @PathVariable Long userId,
            @RequestBody UserPatchRequestDto userPatchRequestDto) {
        UserResponseDto userResponseDto = userMapper.userToUserResponseDto(userService.patchUser(userId, userPatchRequestDto));
        return ResponseEntity.status(HttpStatus.OK).body(userResponseDto);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
