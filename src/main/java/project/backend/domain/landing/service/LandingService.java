package project.backend.domain.landing.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.backend.domain.landing.dto.LandingPostRequestDto;
import project.backend.domain.landing.entity.Landing;
import project.backend.domain.landing.repository.LandingRepository;
import project.backend.global.error.exception.BusinessException;
import project.backend.global.error.exception.ErrorCode;

@Service
@RequiredArgsConstructor
@Transactional
public class LandingService {
    private final LandingRepository landingRepository;

    public Landing createLanding(LandingPostRequestDto landingPostRequestDto) {
        Landing landing = Landing.builder().email(landingPostRequestDto.getEmail()).build();
        landingRepository.save(landing);
        return landing;
    }

    public Landing getNewLanding() {
        return landingRepository.findFirstByOrderByCreatedDateDesc().orElseThrow(() -> new BusinessException(ErrorCode.VERSION_NOT_FOUND));
    }

}
