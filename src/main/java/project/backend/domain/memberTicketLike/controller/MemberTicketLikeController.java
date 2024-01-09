package project.backend.domain.memberTicketLike.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import project.backend.domain.memberTicketLike.dto.MemberTicketLikePostRequestDto;
import project.backend.domain.memberTicketLike.dto.MemberTicketLikeResponseDto;
import project.backend.domain.memberTicketLike.mapper.MemberTicketLikeMapper;
import project.backend.domain.memberTicketLike.service.MemberTicketLikeService;
import project.backend.domain.memberTicketLike.entity.MemberTicketLike;
import project.backend.domain.ticket.dto.TicketResponseDto;
import project.backend.domain.ticket.entity.Ticket;
import project.backend.domain.ticket.mapper.TicketMapper;
import project.backend.global.error.exception.BusinessException;
import project.backend.global.error.exception.ErrorCode;

import javax.validation.constraints.Positive;
import java.util.List;

@Api(tags = "찜하기 API")
@RestController
@RequestMapping("/api/likes")
@RequiredArgsConstructor
public class MemberTicketLikeController {

    private final MemberTicketLikeService memberTicketLikeService;
    private final MemberTicketLikeMapper memberTicketLikeMapper;
    private final TicketMapper ticketMapper;


    @ApiOperation(
            value = "찜하기/찜 취소하기",
            notes = " - 찜이 되어있으면 -> 찜을 취소한다.\n" +
                    " - 찜이 되어있지 않으면 -> 찜을 한다.\n" +
                    " - 찜 상태 : true\n" +
                    " - 찜 안하기 상태 : false")
    @PostMapping("/{ticketId}")
    public ResponseEntity postMemberTicketLike(
            @Positive @PathVariable(required = false) Long ticketId,
            @RequestHeader(value = "Authorization", required = false) String accessToken) {
        if (ObjectUtils.isEmpty(ticketId) || ObjectUtils.isEmpty(accessToken)){
            throw new BusinessException(ErrorCode.MISSING_REQUEST);
        }
        MemberTicketLikeResponseDto memberTicketLikeResponseDto = MemberTicketLikeResponseDto.builder().status(memberTicketLikeService.changeMemberTicketLike(ticketId, accessToken)).build();
        return ResponseEntity.status(HttpStatus.CREATED).body(memberTicketLikeResponseDto);
    }

    @ApiOperation(
            value = "찜 상태 확인하기",
            notes = " - 찜 상태 : true\n" +
                    " - 찜 안하기 상태 : false")
    @GetMapping("/{ticketId}")
    public ResponseEntity getMemberTicketLike(
            @Positive @PathVariable(required = false) Long ticketId,
            @RequestHeader(value = "Authorization", required = false) String accessToken) {
        if (ObjectUtils.isEmpty(ticketId) || ObjectUtils.isEmpty(accessToken)){
            throw new BusinessException(ErrorCode.MISSING_REQUEST);
        }
        MemberTicketLikeResponseDto memberTicketLikeResponseDto = MemberTicketLikeResponseDto.builder().status(memberTicketLikeService.getMemberTicketLike(ticketId, accessToken)).build();
        return ResponseEntity.status(HttpStatus.OK).body(memberTicketLikeResponseDto);
    }

    @ApiOperation(
            value = "나의 찜 목록 확인하기",
            notes = "나의 찜 목록 확인하기")
    @GetMapping
    public ResponseEntity getMemberTicketLikeList(
            @RequestHeader(value = "Authorization", required = false) String accessToken) {
        if (ObjectUtils.isEmpty(accessToken)){
            throw new BusinessException(ErrorCode.MISSING_REQUEST);
        }
        List<Ticket> ticketList = memberTicketLikeService.getMemberTicketLikeList(accessToken);
        List<TicketResponseDto> ticketResponseDtoList = ticketMapper.ticketsToTicketResponseDtos(ticketList);

        // 좋아요 여부 추가
        ticketResponseDtoList.forEach(t -> t.setIsLike(memberTicketLikeService.getMemberTicketLike(t.getId(), accessToken)));

        return ResponseEntity.status(HttpStatus.OK).body(ticketResponseDtoList);
    }

    @DeleteMapping("/{memberTicketLikeId}")
    public ResponseEntity deleteMemberTicketLike(@PathVariable(required = false) Long memberTicketLikeId) {
        if (ObjectUtils.isEmpty(memberTicketLikeId)){
            throw new BusinessException(ErrorCode.MISSING_REQUEST);
        }
        memberTicketLikeService.deleteMemberTicketLike(memberTicketLikeId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
