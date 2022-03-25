package com.rs.consumes.service;

import com.rs.consumes.dto.Credit;
import com.rs.consumes.entity.Debt;
import com.rs.consumes.repository.DebtRepository;
import com.rs.consumes.util.WebClientTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class DebtService {

    @Autowired
    private DebtRepository debtRepository;

    @Autowired
    private WebClientTemplate webClientTemplate;

    public Mono<Debt> paymentDebtOfCreditByAccount(Debt debt){

        return getCredit(debt.getAccountNumber())
                .switchIfEmpty(Mono.empty())
                .flatMap(credit ->{
                    if (debt.getAmount()<= credit.getDebt()){
                        credit.setDebt(credit.getDebt()-debt.getAmount());
                        return updateChargeInCredit(credit);
                    }
                    debt.setAmount(0);
                    return Mono.empty();
                }).then(debtRepository.save(debt));

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
}
