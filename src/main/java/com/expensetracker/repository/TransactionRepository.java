package com.expensetracker.repository;

import com.expensetracker.model.Transaction;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TransactionRepository extends ReactiveCrudRepository<Transaction, Long> {
    Flux<Transaction> findByUserId(String userId);
    Mono<Void> deleteByIdAndUserId(Long id, String userId);
}
