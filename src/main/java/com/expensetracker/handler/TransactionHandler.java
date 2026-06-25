package com.expensetracker.handler;

import com.expensetracker.model.Transaction;
import com.expensetracker.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class TransactionHandler {

    private final TransactionRepository repository;

    public Mono<ServerResponse> getAll(ServerRequest request) {
        return currentUser()
                .flatMap(userId -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(repository.findByUserId(userId), Transaction.class));
    }

    public Mono<ServerResponse> create(ServerRequest request) {
        return currentUser()
                .flatMap(userId -> request.bodyToMono(Transaction.class)
                        .map(t -> Transaction.builder()
                                .description(t.getDescription())
                                .amount(t.getAmount())
                                .category(t.getCategory())
                                .userId(userId)
                                .createdAt(LocalDateTime.now())
                                .build())
                        .flatMap(repository::save)
                        .flatMap(saved -> ServerResponse.status(HttpStatus.CREATED)
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(saved)));
    }

    public Mono<ServerResponse> delete(ServerRequest request) {
        Long id = Long.parseLong(request.pathVariable("id"));
        return currentUser()
                .flatMap(userId -> repository.deleteByIdAndUserId(id, userId))
                .then(ServerResponse.noContent().build());
    }

    private Mono<String> currentUser() {
        return ReactiveSecurityContextHolder.getContext()
                .map(ctx -> ctx.getAuthentication().getName());
    }
}
