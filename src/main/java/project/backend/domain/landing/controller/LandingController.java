package project.backend.domain.landing.controller;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import project.backend.domain.landing.dto.LandingPostRequestDto;
import project.backend.domain.landing.dto.LandingResponseDto;
import project.backend.domain.landing.entity.Landing;
import project.backend.domain.landing.mapper.LandingMapper;
import project.backend.domain.landing.service.LandingService;
import project.backend.global.error.exception.BusinessException;
import project.backend.global.error.exception.ErrorCode;


@Api(tags = "랜딩 API")
@RestController
@RequestMapping("/api/landings")
@RequiredArgsConstructor
public class LandingController {

    private final LandingService landingService;
    private final LandingMapper landingMapper;

    @PostMapping
    public ResponseEntity postLanding(@RequestBody(required = false) LandingPostRequestDto landingPostRequestDto) {
        if (ObjectUtils.isEmpty(landingPostRequestDto)){
            throw new BusinessException(ErrorCode.MISSING_REQUEST);
        }
        Landing landing = landingService.createLanding(landingPostRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(landingMapper.landingToLandingResponseDto(landing));
    }

    @GetMapping("/new")
    public ResponseEntity getLanding() {
        LandingResponseDto landingResponseDto = landingMapper.landingToLandingResponseDto(landingService.getNewLanding());
        return ResponseEntity.status(HttpStatus.OK).body(landingResponseDto);
    }
}
