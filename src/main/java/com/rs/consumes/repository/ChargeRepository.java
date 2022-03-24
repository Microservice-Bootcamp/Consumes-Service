package com.rs.consumes.repository;

import com.rs.consumes.dto.Credit;
import com.rs.consumes.entity.Charge;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface ChargeRepository extends ReactiveMongoRepository<Charge, String> {
    Flux<Charge> findAllByAccountNumberAndIsActive(Integer accountNumber, Boolean state);
}
