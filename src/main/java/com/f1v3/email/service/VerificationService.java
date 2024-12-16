package com.f1v3.email.service;

import com.github.benmanes.caffeine.cache.Cache;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

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
    private final Cache<String, String> verificationCache;

    public void sendMail(String toEmail) {
        String subject = "이메일 인증 코드";
        String code = generateCode();

        emailService.sendMail(toEmail, subject, code);

        // 캐시 저장
        verificationCache.put(toEmail, code);
    }

    public void verify(String email, String code) {
        String codeInCache = verificationCache.getIfPresent(email);

        if (codeInCache == null) {
            throw new RuntimeException("인증 코드가 만료되었습니다.");
        }

        if (!codeInCache.equals(code)) {
            throw new RuntimeException("인증 코드가 일치하지 않습니다.");
        }

        // 캐시 삭제
        verificationCache.invalidate(email);
    }

    private String generateCode() {
        int length = 6;

        try {
            Random random = SecureRandom.getInstanceStrong();
            StringBuffer buffer = new StringBuffer();
            for (int i = 0; i < length; i++) {
                int index = random.nextInt(10);
                buffer.append(index);
            }

            return buffer.toString();
        } catch (NoSuchAlgorithmException e) {

            log.info("암호화 알고리즘을 찾을 수 없습니다.");
            throw new RuntimeException(e);
        }
    }
}
