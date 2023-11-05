package project.backend.domain.jwt.controller;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import project.backend.domain.jwt.dto.KakaoUserInfo;
import project.backend.domain.jwt.response.JwtResponse;
import project.backend.domain.jwt.response.TokenResponse;
import project.backend.domain.jwt.service.JwtService;
import project.backend.domain.member.dto.MemberResponseDto;
import project.backend.domain.member.entity.Member;
import project.backend.domain.member.mapper.MemberMapper;
import project.backend.domain.member.service.MemberService;
import project.backend.global.error.exception.BusinessException;
import project.backend.global.error.exception.ErrorCode;

@RestController
@RequestMapping("/api/auth")
@Api(tags = "로그인 API")
@Validated
@AllArgsConstructor
@Slf4j
public class JwtController {

    private final MemberService memberService;
    private final JwtService jwtService;
    private final MemberMapper memberMapper;

    @GetMapping("/login")
    public ResponseEntity login(@RequestParam(value = "token", required = false) String token,
                                @RequestParam(value = "code", required = false) String code,
                                @RequestParam(value = "redirect_url", required = false) String redirect_url) {

        // 유저 정보 얻기
        if(!StringUtils.isEmpty(code)) {
            if (StringUtils.isEmpty(redirect_url)){
                throw new BusinessException(ErrorCode.MISSING_REDIRECT_REQUEST_PARAM);
            }
            token = jwtService.getKakaoAccessToken(code, redirect_url);
        } else if(StringUtils.isEmpty(token)) {
            throw new BusinessException(ErrorCode.MISSING_REQUEST_PARAM);
        }
        KakaoUserInfo kakaoUserInfo = jwtService.getKakaoUserInfo(token);

        // 해당 kakao ID를 가진 Member 반환
        log.info(kakaoUserInfo.getKakaoId());
        Member member = memberService.findMemberBySocialId(kakaoUserInfo.getKakaoId());

        // accessToken과 refreshToken발급
        String accessToken = jwtService.getAccessToken(member); // 에러 발생
        String refreshToken = member.getRefreshToken();

        // 응답
        MemberResponseDto memberResponse = memberMapper.memberToMemberResponseDto(member);
        TokenResponse tokenResponse = TokenResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken).build();
        JwtResponse jwtResponse = JwtResponse.builder()
                .token(tokenResponse)
                .member(memberResponse).build();
        return new ResponseEntity<>(jwtResponse, HttpStatus.CREATED);
    }

}