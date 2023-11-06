package project.backend.domain.memberTicketLike.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.backend.domain.memberTicketLike.dto.MemberTicketLikePostRequestDto;
import project.backend.domain.memberTicketLike.dto.MemberTicketLikeResponseDto;
import project.backend.domain.memberTicketLike.mapper.MemberTicketLikeMapper;
import project.backend.domain.memberTicketLike.service.MemberTicketLikeService;
import project.backend.domain.memberTicketLike.entity.MemberTicketLike;

import java.util.List;

@Api(tags = "로그인 API")
@RestController
@RequestMapping("/api/likes")
@RequiredArgsConstructor
public class MemberTicketLikeController {

    private final MemberTicketLikeService memberTicketLikeService;
    private final MemberTicketLikeMapper memberTicketLikeMapper;


    @PostMapping
    public ResponseEntity postMemberTicketLike(@RequestBody MemberTicketLikePostRequestDto memberTicketLikePostRequestDto) {
        MemberTicketLike memberTicketLike = memberTicketLikeService.createMemberTicketLike(memberTicketLikePostRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(memberTicketLikeMapper.memberTicketLikeToMemberTicketLikeResponseDto(memberTicketLike));
    }

    @ApiOperation(value = "공지 목록")
    @GetMapping("/{memberTicketLikeId}")
    public ResponseEntity getMemberTicketLike(@PathVariable Long memberTicketLikeId) {
        MemberTicketLikeResponseDto memberTicketLikeResponseDto = memberTicketLikeMapper.memberTicketLikeToMemberTicketLikeResponseDto(memberTicketLikeService.getMemberTicketLike(memberTicketLikeId));
        return ResponseEntity.status(HttpStatus.OK).body(memberTicketLikeResponseDto);
    }

    @GetMapping
    public ResponseEntity getMemberTicketLikeList() {
        List<MemberTicketLikeResponseDto> memberTicketLikeResponseDtoList = memberTicketLikeMapper.memberTicketLikesToMemberTicketLikeResponseDtos(memberTicketLikeService.getMemberTicketLikeList());
        return ResponseEntity.status(HttpStatus.OK).body(memberTicketLikeResponseDtoList);
    }

    @DeleteMapping("/{memberTicketLikeId}")
    public ResponseEntity deleteMemberTicketLike(@PathVariable Long memberTicketLikeId) {
        memberTicketLikeService.deleteMemberTicketLike(memberTicketLikeId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
