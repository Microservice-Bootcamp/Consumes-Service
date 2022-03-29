package com.rs.consumes.repository;

import com.rs.consumes.entity.Payment;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface PaymentRepository extends ReactiveMongoRepository<Payment, String> {
}
