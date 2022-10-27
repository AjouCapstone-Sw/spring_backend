package com.the_ajou.service;

import com.the_ajou.domain.payment_history.PaymentHistory;
import com.the_ajou.domain.payment_history.PaymentHistoryRepository;
import com.the_ajou.domain.user.User;
import com.the_ajou.domain.user.UserRepository;
import com.the_ajou.web.dto.payment.PaymentCreateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RequiredArgsConstructor
@Service
public class PaymentService {
    private final PaymentHistoryRepository paymentHistoryRepository;
    private final UserRepository userRepository;

    @Transactional
    public void createHistory(PaymentCreateDTO paymentCreateDTO){
        User user = userRepository.findById(paymentCreateDTO.getUserId())
                .orElseThrow();

        PaymentHistory paymentHistory = PaymentHistory.builder()
                .user(user)
                .payment(paymentCreateDTO.getPayment())
                .date(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .build();
        paymentHistoryRepository.save(paymentHistory);
    }
}
