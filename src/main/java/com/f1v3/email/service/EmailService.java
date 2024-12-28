package com.f1v3.email.service;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

/**
 * 메일 전송 서비스
 *
 * @author 정승조
 * @version 2024. 12. 16.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;

    @Async("taskExecutor")
    public void sendMail(String to, String subject, String code) {
        MimeMessage message = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            Context context = new Context();
            context.setVariable("code", code);

            String htmlContent = templateEngine.process("email-template", context);

            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(htmlContent, true);
            helper.setFrom("tmdwh5@gmail.com", "f1v3");

            mailSender.send(message);
        } catch (Exception e) {
            log.info("메일 전송 실패 (to: {}, subject: {}, text: {})", to, subject, code);
            throw new RuntimeException("메일 전송 실패");
        }
    }

}
