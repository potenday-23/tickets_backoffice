package project.backend.domain.version.controller;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import project.backend.domain.version.dto.VersionPostRequestDto;
import project.backend.domain.version.dto.VersionResponseDto;
import project.backend.domain.version.entity.Version;
import project.backend.domain.version.mapper.VersionMapper;
import project.backend.domain.version.service.VersionService;
import project.backend.global.error.exception.BusinessException;
import project.backend.global.error.exception.ErrorCode;


@Api(tags = "버전 API")
@RestController
@RequestMapping("/api/versions")
@RequiredArgsConstructor
public class VersionController {

    private final VersionService versionService;
    private final VersionMapper versionMapper;

    @PostMapping
    public ResponseEntity postVersion(@RequestBody(required = false) VersionPostRequestDto versionPostRequestDto) {
        if (ObjectUtils.isEmpty(versionPostRequestDto)){
            throw new BusinessException(ErrorCode.MISSING_REQUEST);
        }
        Version version = versionService.createVersion(versionPostRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(versionMapper.versionToVersionResponseDto(version));
    }

    @GetMapping("/new")
    public ResponseEntity getVersion() {
        VersionResponseDto versionResponseDto = versionMapper.versionToVersionResponseDto(versionService.getNewVersion());
        return ResponseEntity.status(HttpStatus.OK).body(versionResponseDto);
    }
}
