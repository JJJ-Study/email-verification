package com.f1v3.email.controller;

import com.f1v3.email.service.VerificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * {class name}.
 *
 * @author 정승조
 * @version 2024. 12. 16.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/verification")
public class VerificationController {

    private final VerificationService verificationService;

    @PostMapping("/send")
    public ResponseEntity<Void> sendMail(String email) {
        verificationService.sendMail(email);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/verify")
    public ResponseEntity<Void> verify(@RequestParam String code) {
        verificationService.verify(code);

        return ResponseEntity.ok().build();
    }

}
