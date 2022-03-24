package com.rs.consumes.service;

import com.rs.consumes.entity.Consume;
import com.rs.consumes.repository.ConsumeRepository;
import com.rs.consumes.util.validation.AccountValidationService;
import com.rs.consumes.webclient.PersonalAccountWebClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ConsumeService {

    @Autowired
    private ConsumeRepository consumeRepository;

    @Autowired
    private AccountValidationService accountValidationService;


    public Mono<Consume> save(Consume consume) {

        Mono<?> account = Mono.empty();

        switch (consume.getClientType()) {
            case PERSONAL:
                //account = accountValidationService.findPersonalAccount(consume.getCreditCardNumber());
                break;
            case BUSINESS:
                break;
            default:
                break;
        }
        return consumeRepository.save(consume);
    }

    public Flux<Consume> findAll() {
        return consumeRepository.findAll();
    }


}
