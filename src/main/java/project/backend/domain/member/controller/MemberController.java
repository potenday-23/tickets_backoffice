package project.backend.domain.member.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.backend.domain.member.dto.MemberPostRequestDto;
import project.backend.domain.member.dto.MemberResponseDto;
import project.backend.domain.member.dto.MemberPatchRequestDto;
import project.backend.domain.member.entity.Member;
import project.backend.domain.member.mapper.MemberMapper;
import project.backend.domain.member.service.MembrService;

import java.util.List;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {

    private final MembrService membrService;
    private final MemberMapper memberMapper;


    @PostMapping
    public ResponseEntity postMember(@RequestBody MemberPostRequestDto memberPostRequestDto) {
        Member member = membrService.createMember(memberPostRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(memberMapper.memberToMemberResponseDto(member));
    }

    @GetMapping("/{memberId}")
    public ResponseEntity getMember(@PathVariable Long memberId) {
        MemberResponseDto memberResponseDto = memberMapper.memberToMemberResponseDto(membrService.getMember(memberId));
        return ResponseEntity.status(HttpStatus.OK).body(memberResponseDto);
    }

    @GetMapping
    public ResponseEntity getMemberList() {
        List<MemberResponseDto> memberResponseDtoList = memberMapper.membersToMemberResponseDtos(membrService.getMemberList());
        return ResponseEntity.status(HttpStatus.OK).body(memberResponseDtoList);
    }

    @PatchMapping("/{memberId}")
    public ResponseEntity putMember(
            @PathVariable Long memberId,
            @RequestBody MemberPatchRequestDto memberPatchRequestDto) {
        MemberResponseDto memberResponseDto = memberMapper.memberToMemberResponseDto(membrService.patchMember(memberId, memberPatchRequestDto));
        return ResponseEntity.status(HttpStatus.OK).body(memberResponseDto);
    }

    @DeleteMapping("/{memberId}")
    public ResponseEntity deleteMember(@PathVariable Long memberId) {
        membrService.deleteMember(memberId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
