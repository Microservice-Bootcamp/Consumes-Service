package com.rs.consumes.controller;

import com.rs.consumes.entity.Payment;
import com.rs.consumes.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/card")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/pay")
    public Mono<Payment> payWithCard(@RequestBody Payment payment){
        return paymentService.paymentWithCard(payment);
    }
}
