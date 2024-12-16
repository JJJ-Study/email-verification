package com.f1v3.email.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * {class name}.
 *
 * @author 정승조
 * @version 2024. 12. 16.
 */
@Controller
public class ViewController {

    @GetMapping("/")
    public String verify() {
        return "verify";
    }
}
