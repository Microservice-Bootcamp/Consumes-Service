package com.rs.consumes.repository;

import com.rs.consumes.entity.Consume;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@EnableReactiveMongoRepositories
public interface ConsumeRepository extends ReactiveMongoRepository<Consume, String> {
}
