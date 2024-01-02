package project.backend.domain.version.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.backend.domain.version.dto.VersionPostRequestDto;
import project.backend.domain.version.entity.Version;
import project.backend.domain.version.repository.VersionRepository;
import project.backend.global.error.exception.BusinessException;
import project.backend.global.error.exception.ErrorCode;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class VersionService {
    private final VersionRepository versionRepository;

    public Version createVersion(VersionPostRequestDto versionPostRequestDto) {
        Version version = Version.builder().version(versionPostRequestDto.getVersion()).build();
        versionRepository.save(version);
        return version;
    }

    public Version getNewVersion() {
        return versionRepository.findFirstByOrderByCreatedDateDesc().orElseThrow(() -> new BusinessException(ErrorCode.VERSION_NOT_FOUND));
    }

}
