package com.rs.consumes.controller;

import com.rs.consumes.entity.Debt;
import com.rs.consumes.service.DebtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/payment")
public class DebtController {

    @Autowired
    private DebtService debtService;

    @PostMapping("/debt")
    public Mono<Debt> paymentDebtOfCreditByAccount(@RequestBody Debt debt){
        return debtService.paymentDebtOfCreditByAccount(debt);
    }
}
