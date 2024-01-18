package project.backend.domain.traffic.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.backend.domain.member.entity.Member;
import project.backend.domain.member.service.MemberService;
import project.backend.domain.traffic.dto.TrafficPostRequestDto;
import project.backend.domain.traffic.entity.Traffic;
import project.backend.domain.traffic.repository.TrafficRepository;
import project.backend.global.error.exception.BusinessException;
import project.backend.global.error.exception.ErrorCode;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@RequiredArgsConstructor
@Transactional
public class TrafficService {
    private final TrafficRepository trafficRepository;

    public Traffic createTraffic(TrafficPostRequestDto trafficPostRequestDto, Member member) {
        Traffic traffic = Traffic.builder().buttonName(trafficPostRequestDto.getButtonName()).build();
        trafficRepository.save(traffic);
        if (member != null) {
            traffic.setMember(member);
        }
        return traffic;
    }
}


