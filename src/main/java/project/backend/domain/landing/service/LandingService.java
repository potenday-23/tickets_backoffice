package project.backend.domain.landing.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.backend.domain.landing.dto.LandingPostRequestDto;
import project.backend.domain.landing.entity.Landing;
import project.backend.domain.landing.repository.LandingRepository;
import project.backend.global.error.exception.BusinessException;
import project.backend.global.error.exception.ErrorCode;

import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.nio.file.Path;

@Service
@RequiredArgsConstructor
@Transactional
public class LandingService {
    private final LandingRepository landingRepository;
    private final JavaMailSender mailSender;

    public Landing createLanding(LandingPostRequestDto landingPostRequestDto) {
        Landing landing = Landing.builder().email(landingPostRequestDto.getEmail()).build();
        landingRepository.save(landing);
        String emailTo = landing.getEmail();
        String subject = "티캣츠 사전예약을 진심으로 감사드립니다.";
        String text = "<img style=\"width: 200px; margin-bottom: 50px\" src=\"https://potenday-23.github.io/ticats-landing/images/ticats-TWk.png\"><br>안녕하세요 티캣츠입니다<br>티캣츠 서비스에 관심을 가져주셔서 감사합니다.<br>서비스가 출시되면 메일로 알려드리겠습니다.<br>항상 노력하는 티캣츠가 되겠습니다. 감사합니다.<br></div>";
        try {
            sendEmailWithAttachment(emailTo, subject, text);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        return landing;
    }


    public void sendEmailWithAttachment(String to, String subject, String text) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(text, true);

        // 파일 첨부
//        helper.addAttachment(attachmentPath.getFileName().toString(), attachmentPath.toFile());

        mailSender.send(message);
    }

    public Landing getNewLanding() {
        return landingRepository.findFirstByOrderByCreatedDateDesc().orElseThrow(() -> new BusinessException(ErrorCode.VERSION_NOT_FOUND));
    }
}


