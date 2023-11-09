package project.backend.domain.quit.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import project.backend.domain.quit.dto.QuitPatchRequestDto;
import project.backend.domain.quit.dto.QuitPostRequestDto;
import project.backend.domain.quit.dto.QuitResponseDto;
import project.backend.domain.quit.entity.Quit;
import project.backend.domain.quit.mapper.QuitMapper;
import project.backend.domain.quit.service.QuitService;
import project.backend.global.error.exception.BusinessException;
import project.backend.global.error.exception.ErrorCode;

import java.util.List;

@Api(tags = "탈퇴 API")
@RestController
@RequestMapping("/api/quits")
@RequiredArgsConstructor
public class QuitController {

    private final QuitService quitService;
    private final QuitMapper quitMapper;

    @ApiOperation(
            value = "탈퇴 사유 update하기",
            notes = " - quits : [1, 2, 3]" +
                    " - 탈퇴 사유 목록을 조회 후, 해당 id를 list로 넣어주세요.")
    @PostMapping("/reasons")
    public ResponseEntity updateQuitReason(
            @RequestPart(required = false) List<Long> quits) {
        if (ObjectUtils.isEmpty(quits)){
            throw new BusinessException(ErrorCode.MISSING_REQUEST);
        }
        quitService.updateQuitReason(quits);
        return ResponseEntity.status(HttpStatus.OK).body(quitMapper.quitsToQuitResponseDtos(quitService.getQuitList()));
    }

    @ApiOperation(value = "탈퇴 사유 목록")
    @GetMapping
    public ResponseEntity getQuitList() {
        List<QuitResponseDto> quitResponseDtoList = quitMapper.quitsToQuitResponseDtos(quitService.getQuitList());
        return ResponseEntity.status(HttpStatus.OK).body(quitResponseDtoList);
    }

    @PostMapping
    public ResponseEntity postQuit(@RequestBody(required = false) QuitPostRequestDto quitPostRequestDto) {
        if (ObjectUtils.isEmpty(quitPostRequestDto)){
            throw new BusinessException(ErrorCode.MISSING_REQUEST);
        }
        Quit quit = quitService.createQuit(quitPostRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(quitMapper.quitToQuitResponseDto(quit));
    }

    @PatchMapping("/{quitId}")
    public ResponseEntity putQuit(
            @PathVariable(required = false) Long quitId,
            @RequestBody(required = false) QuitPatchRequestDto quitPatchRequestDto) {
        if (ObjectUtils.isEmpty(quitId) || ObjectUtils.isEmpty(quitPatchRequestDto)){
            throw new BusinessException(ErrorCode.MISSING_REQUEST);
        }
        QuitResponseDto quitResponseDto = quitMapper.quitToQuitResponseDto(quitService.patchQuit(quitId, quitPatchRequestDto));
        return ResponseEntity.status(HttpStatus.OK).body(quitResponseDto);
    }

    @DeleteMapping("/{quitId}")
    public ResponseEntity deleteQuit(@PathVariable(required = false) Long quitId) {
        if (ObjectUtils.isEmpty(quitId)){
            throw new BusinessException(ErrorCode.MISSING_REQUEST);
        }
        quitService.deleteQuit(quitId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
