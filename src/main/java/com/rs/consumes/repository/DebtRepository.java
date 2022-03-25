package com.rs.consumes.repository;

import com.rs.consumes.entity.Debt;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface DebtRepository extends ReactiveMongoRepository<Debt, String> {
}
