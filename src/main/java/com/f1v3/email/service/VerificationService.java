package com.f1v3.email.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * {class name}.
 *
 * @author 정승조
 * @version 2024. 12. 16.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class VerificationService {

    private final EmailService emailService;

    public void sendMail(String email) {
        String subject = "이메일 인증";
        String text = "인증번호 : 1234";

        emailService.sendMail(email, subject, text);
    }

    public void verify(String code) {
        if (!code.equals("1234")) {
            throw new IllegalArgumentException("인증번호가 일치하지 않습니다.");
        }


    }
}
