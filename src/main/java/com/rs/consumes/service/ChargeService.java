package com.rs.consumes.service;

import com.rs.consumes.dto.Credit;
import com.rs.consumes.entity.Charge;
import com.rs.consumes.repository.ChargeRepository;
import com.rs.consumes.util.WebClientTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ChargeService {
    private LocalDate date = LocalDate.now();

    @Autowired
    private ChargeRepository chargeRepository;

    @Autowired
    private WebClientTemplate webClientTemplate;

    /*public Mono<Charge> chargeBDebt(Charge charge){
        var creditLimit =  getCredit(charge.getAccountNumber())
                .switchIfEmpty(Mono.empty())
                .flatMap(credit ->Mono.just(credit.getCreditLimit()));

        return chargeByAccount(charge.getAccountNumber())
                .collectList()
                .flatMap(charges -> {
                    var prestamoTotal =  charges.stream().mapToDouble(Charge::getAmount).sum();
                    var localDates = charges.stream().map(Charge::getDueDate).collect(Collectors.toList());
                    var minDate = Collections.min(localDates);
                    return creditLimit.flatMap(limit ->{
                        if(limit >= prestamoTotal && !minDate.isBefore(LocalDate.now())){
                            return chargeRepository.save(charge);
                        }
                        return Mono.empty();
                    });
                });

    }*/
    public Mono<Charge> chargeBDebt(Charge charge){
        return getCredit(charge.getAccountNumber())
                .switchIfEmpty(Mono.empty())
                .flatMap(credit ->{
                    if((credit.getCreditLimit()-credit.getDebt())>= charge.getAmount()){
                        credit.setDebt((int) (credit.getDebt()+ charge.getAmount()));
                        return updateChargeInCredit(credit)
                                .then(chargeRepository.save(charge));
                    }
                    return Mono.empty();
                });
    }


    private Mono<Credit> getCredit(Integer accountNumber){
        return webClientTemplate.templateWebClient("http://localhost:8093")
                .get()
                .uri("/credit/"+accountNumber)
                .retrieve()
                .bodyToMono(Credit.class);
    }
    private Mono<Credit> updateChargeInCredit(Credit credit){
        return webClientTemplate.templateWebClient("http://localhost:8093")
                .put()
                .uri("/credit/update")
                .body( Mono.just(credit), Credit.class)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Credit.class);
    }



    private Flux<Charge> chargeByAccount(Integer accountNumber){
        return chargeRepository.findAllByAccountNumberAndIsActive(accountNumber, true);
    }
}
