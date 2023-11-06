package project.backend.domain.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.backend.domain.member.entity.LogoutToken;
import project.backend.domain.member.entity.Member;
import project.backend.domain.member.repository.LogoutTokenRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class LogoutTokenService {

    private final LogoutTokenRepository logoutTokenRepository;

    public void memberLogout(String token) {
        logoutTokenRepository.save(LogoutToken.builder().token(token).build());
    }
}
