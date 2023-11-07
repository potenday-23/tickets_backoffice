package project.backend.domain.quit.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.backend.domain.quit.dto.QuitPatchRequestDto;
import project.backend.domain.quit.dto.QuitPostRequestDto;
import project.backend.domain.quit.dto.QuitResponseDto;
import project.backend.domain.quit.entity.Quit;
import project.backend.domain.quit.mapper.QuitMapper;
import project.backend.domain.quit.service.QuitService;

import java.util.List;

@Api(tags = "탈퇴 API")
@RestController
@RequestMapping("/api/quits")
@RequiredArgsConstructor
public class QuitController {

    private final QuitService quitService;
    private final QuitMapper quitMapper;

    @PostMapping("/reasons")
    public ResponseEntity getQuitList(
            @RequestPart List<Long> quits) {
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
    public ResponseEntity postQuit(@RequestBody QuitPostRequestDto quitPostRequestDto) {
        Quit quit = quitService.createQuit(quitPostRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(quitMapper.quitToQuitResponseDto(quit));
    }

    @PatchMapping("/{quitId}")
    public ResponseEntity putQuit(
            @PathVariable Long quitId,
            @RequestBody QuitPatchRequestDto quitPatchRequestDto) {
        QuitResponseDto quitResponseDto = quitMapper.quitToQuitResponseDto(quitService.patchQuit(quitId, quitPatchRequestDto));
        return ResponseEntity.status(HttpStatus.OK).body(quitResponseDto);
    }

    @DeleteMapping("/{quitId}")
    public ResponseEntity deleteQuit(@PathVariable Long quitId) {
        quitService.deleteQuit(quitId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
