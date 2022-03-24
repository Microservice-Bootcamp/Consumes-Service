package com.rs.consumes.util.validation;

import com.rs.consumes.webclient.BusinessAccountWebClient;
import com.rs.consumes.webclient.PersonalAccountWebClient;
import com.rs.consumes.webclient.dto.BankAccountDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class AccountValidationService {

    @Autowired
    private PersonalAccountWebClient personalAccountWebClient;

    @Autowired
    private BusinessAccountWebClient businessAccountWebClient;

    public Mono<Boolean> existsPersonalAccount(String accountNumber) {
        return personalAccountWebClient.findByAccountNumber(accountNumber).switchIfEmpty(Mono.error(new Exception("Not found")))
                .flatMap(x -> Mono.just(true));
    }

    public Mono<Boolean> existsBusinessAccount(String accountNumber) {
        return businessAccountWebClient.findByAccountNumber(accountNumber).switchIfEmpty(Mono.error(new Exception("Not found")))
                .flatMap(x -> Mono.just(true));
    }

}
