package com.the_ajou.web.controller;

import com.the_ajou.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class EmailController {
    private final EmailService emailService;

    @CrossOrigin
    @GetMapping("/email")
    public String sendEmail(String email){
        return emailService.sendMail(email);
    }
}
