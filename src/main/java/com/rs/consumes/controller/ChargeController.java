package com.rs.consumes.controller;

import com.rs.consumes.entity.Charge;
import com.rs.consumes.service.ChargeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/charge")
public class ChargeController {

    @Autowired
    private ChargeService chargeService;

    @PostMapping("/save")
    public Mono<Charge> chargeDebt(@RequestBody Charge charge){
        return chargeService.chargeBDebt(charge);
    }


}
