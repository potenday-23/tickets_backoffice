package project.backend.domain.member.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.backend.domain.jwt.service.JwtService;
import project.backend.domain.member.dto.MemberPostRequestDto;
import project.backend.domain.member.dto.MemberResponseDto;
import project.backend.domain.member.dto.MemberPatchRequestDto;
import project.backend.domain.member.entity.Member;
import project.backend.domain.member.mapper.MemberMapper;
import project.backend.domain.member.service.MemberService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final MemberMapper memberMapper;
    private final JwtService jwtService;


    @PostMapping
    public ResponseEntity postMember(@RequestBody MemberPostRequestDto memberPostRequestDto) {
        Member member = memberService.createMember(memberPostRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(memberMapper.memberToMemberResponseDto(member));
    }

    @GetMapping("/{memberId}") // todo : 관리자 권한 있어야 실행 가능한 것으로 바꾸기
    public ResponseEntity getMember(
            @RequestHeader("Authorization") String accessToken,
            @PathVariable Long memberId) {
        MemberResponseDto memberResponseDto = memberMapper.memberToMemberResponseDto(memberService.getMember(memberId));
        return ResponseEntity.status(HttpStatus.OK).body(memberResponseDto);
    }

    @GetMapping
    public ResponseEntity getMember(
            @RequestHeader("Authorization") String accessToken) {
        Member member = jwtService.getMemberFromAccessToken(accessToken);
        MemberResponseDto memberResponseDto = memberMapper.memberToMemberResponseDto(member);
        return ResponseEntity.status(HttpStatus.OK).body(memberResponseDto);
    }

    @GetMapping("/list")
    public ResponseEntity getMemberList(
            @RequestHeader("Authorization") String accessToken) {
        List<MemberResponseDto> memberResponseDtoList = memberMapper.membersToMemberResponseDtos(memberService.getMemberList());
        return ResponseEntity.status(HttpStatus.OK).body(memberResponseDtoList);
    }

    @PatchMapping
    public ResponseEntity patchMember(
            @RequestHeader("Authorization") String accessToken,
            @Valid @RequestBody MemberPatchRequestDto memberPatchRequestDto) {
        Member member = jwtService.getMemberFromAccessToken(accessToken);
        MemberResponseDto memberResponseDto = memberMapper.memberToMemberResponseDto(memberService.patchMember(member.getId(), memberPatchRequestDto));
        return ResponseEntity.status(HttpStatus.OK).body(memberResponseDto);
    }

    @DeleteMapping("/{memberId}")
    public ResponseEntity deleteMember(@PathVariable Long memberId) {
        memberService.deleteMember(memberId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
