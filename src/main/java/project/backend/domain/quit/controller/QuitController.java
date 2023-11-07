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

@Api(tags = "공지 API")
@RestController
@RequestMapping("/api/quits")
@RequiredArgsConstructor
public class QuitController {

    private final QuitService quitService;
    private final QuitMapper quitMapper;


    @PostMapping
    public ResponseEntity postQuit(@RequestBody QuitPostRequestDto quitPostRequestDto) {
        Quit quit = quitService.createQuit(quitPostRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(quitMapper.quitToQuitResponseDto(quit));
    }

    @ApiOperation(value = "공지 목록")
    @GetMapping("/{quitId}")
    public ResponseEntity getQuit(@PathVariable Long quitId) {
        QuitResponseDto quitResponseDto = quitMapper.quitToQuitResponseDto(quitService.getQuit(quitId));
        return ResponseEntity.status(HttpStatus.OK).body(quitResponseDto);
    }

    @GetMapping
    public ResponseEntity getQuitList() {
        List<QuitResponseDto> quitResponseDtoList = quitMapper.quitsToQuitResponseDtos(quitService.getQuitList());
        return ResponseEntity.status(HttpStatus.OK).body(quitResponseDtoList);
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
