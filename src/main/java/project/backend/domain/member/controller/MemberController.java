package project.backend.domain.member.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import project.backend.domain.jwt.service.JwtService;
import project.backend.domain.member.dto.MemberPostRequestDto;
import project.backend.domain.member.dto.MemberResponseDto;
import project.backend.domain.member.dto.MemberPatchRequestDto;
import project.backend.domain.member.entity.Member;
import project.backend.domain.member.entity.SocialType;
import project.backend.domain.member.mapper.MemberMapper;
import project.backend.domain.member.service.MemberService;
import project.backend.domain.onboardingmembercategory.entity.OnboardingMemberCategory;
import project.backend.global.error.exception.BusinessException;
import project.backend.global.error.exception.ErrorCode;
import project.backend.global.s3.service.ImageService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
@Api(tags = "멤버 API")
public class MemberController {

    private final MemberService memberService;
    private final MemberMapper memberMapper;
    private final JwtService jwtService;
    private final ImageService imageService;

    @GetMapping("/{memberId}") // todo : 관리자 권한 있어야 실행 가능한 것으로 바꾸기
    public ResponseEntity getMember(
            @RequestHeader("Authorization") String accessToken,
            @PathVariable Long memberId) {
        MemberResponseDto memberResponseDto = memberMapper.memberToMemberResponseDto(memberService.getMember(memberId));
        return ResponseEntity.status(HttpStatus.OK).body(memberResponseDto);
    }

    @ApiOperation(
            value = "Member 조회",
            notes = "1. AccessToken으로 조회할 경우 : Header의 Authorization에 accessToken을 넣어주세요.\n" +
                    "2. socialId와 socialType으로 조회할 경우 : ?socialId=abcdefg&socialType=KAKAO" +
                    "" +
                    " - 해당 Member 없을 경우 -> 400에러, code : U001, message : 사용자를 찾을 수 없습니다.\n" +
                    " - socialType은 KAKAO와 APPLE만 가능합니다.")
    @GetMapping
    public ResponseEntity getMember(
            @RequestHeader(value = "Authorization", required = false) String accessToken,
            @RequestParam(required = false) String socialId,
            @RequestParam(required = false) SocialType socialType) {

        Member member;
        if (accessToken != null) {
            member = jwtService.getMemberFromAccessToken(accessToken);
        } else if (socialId != null && socialType != null) {
            member = memberService.getMemberBySocialIdAndSocialType(socialId, socialType);
        } else {
            throw new BusinessException(ErrorCode.INVALID_REQUEST);
        }
        MemberResponseDto memberResponseDto = memberMapper.memberToMemberResponseDto(member);
        memberResponseDto.setCategorys(member.getOnboardingMemberCategories().stream().map(c -> c.getCategory().getName()).collect(Collectors.toList()));
        return ResponseEntity.status(HttpStatus.OK).body(memberResponseDto);
    }

    @GetMapping("/list")
    public ResponseEntity getMemberList(
            @RequestHeader("Authorization") String accessToken) {
        List<MemberResponseDto> memberResponseDtoList = memberMapper.membersToMemberResponseDtos(memberService.getMemberList());
        return ResponseEntity.status(HttpStatus.OK).body(memberResponseDtoList);
    }

    @ApiOperation(
            value = "닉네임 & 프로필 이미지 등록, 온보딩 기능",
            notes = " - 닉네임 변경 원할 시 : request -> {\"nickname\" : \"가방이\", \"marketingAgree\":\"AGREE\", \"pushAgree\":\"DISAGREE\"}\n" +
                    " - 프로필 이미지 변경 원할 시 : profileImage -> MultipartFile으로 파일 입력 \n" +
                    " - 온보딩 입력 & 수정 원힐 시 : categorys -> [\"기타\", \"영화\"] \n" +
                    " - 닉네임과 온보딩 입력란은 application/json형식으로 요청해주세요.(swagger에서는 작동하지 않습니다.)" +
                    " - Header의 AccessToken만 필수 값이고, 나머지는 필수 값이 아님")
    @RequestMapping(method = RequestMethod.PATCH, consumes = "multipart/form-data")
    public ResponseEntity patchMember(
            @RequestHeader("Authorization") String accessToken,
            @RequestPart(value = "profileImage", required = false) MultipartFile profileImage,
            @Valid @RequestPart(value = "request", required = false) MemberPatchRequestDto request,
            @RequestPart(value = "categorys", required = false) List<String> categorys
    ) {
        memberService.verifiedNickname(request.getNickname());
        Member member = jwtService.getMemberFromAccessToken(accessToken);
        if (request == null) {
            request = MemberPatchRequestDto.builder().build();
        }
        if (profileImage != null) {
            request.setProfileUrl(imageService.updateImage(profileImage, "Member", "profileUrl"));
        }
        if (categorys != null) {
            categorys = categorys.stream().distinct().collect(Collectors.toList());
            memberService.onboardingMember(member.id, categorys);
        }
        MemberResponseDto memberResponseDto = memberMapper.memberToMemberResponseDto(memberService.patchMember(member.getId(), request));
        memberResponseDto.setCategorys(member.getOnboardingMemberCategories().stream().map(c -> c.getCategory().getName()).collect(Collectors.toList()));
        return ResponseEntity.status(HttpStatus.OK).body(memberResponseDto);
    }

    @ApiOperation(value = "회원 탈퇴")
    @DeleteMapping
    public ResponseEntity deleteMember(
            @RequestHeader("Authorization") String accessToken) {
        memberService.deleteMember(jwtService.getMemberFromAccessToken(accessToken).getId());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
