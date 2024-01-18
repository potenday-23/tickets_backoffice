package project.backend.domain.traffic.controller;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import project.backend.domain.jwt.service.JwtService;
import project.backend.domain.member.entity.Member;
import project.backend.domain.traffic.dto.TrafficPostRequestDto;
import project.backend.domain.traffic.dto.TrafficResponseDto;
import project.backend.domain.traffic.entity.Traffic;
import project.backend.domain.traffic.mapper.TrafficMapper;
import project.backend.domain.traffic.service.TrafficService;
import project.backend.global.error.exception.BusinessException;
import project.backend.global.error.exception.ErrorCode;


@Api(tags = "트래픽 API")
@RestController
@RequestMapping("/api/traffics")
@RequiredArgsConstructor
public class TrafficController {

    private final TrafficService trafficService;
    private final TrafficMapper trafficMapper;
    private final JwtService jwtService;

    @PostMapping
    public ResponseEntity postTraffic(@RequestBody(required = false) TrafficPostRequestDto trafficPostRequestDto,
                                      @RequestHeader(value = "Authorization", required = false) String accessToken) {
        Member member = null;
        if (ObjectUtils.isEmpty(trafficPostRequestDto)) {
            throw new BusinessException(ErrorCode.MISSING_REQUEST);
        }
        if (!ObjectUtils.isEmpty(accessToken)) {
            member = jwtService.getMemberFromAccessToken(accessToken);
        }

        Traffic traffic = trafficService.createTraffic(trafficPostRequestDto, member);
        return ResponseEntity.status(HttpStatus.CREATED).body(trafficMapper.trafficToTrafficResponseDto(traffic));
    }
}
