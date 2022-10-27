package com.the_ajou.web.controller;

import com.the_ajou.domain.payment_history.PaymentHistoryRepository;
import com.the_ajou.service.PaymentService;
import com.the_ajou.web.dto.payment.PaymentCreateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping("/history/create")
    public void createPaymentHistory(@RequestBody PaymentCreateDTO paymentCreateDTO){
        paymentService.createHistory(paymentCreateDTO);
    }
}
