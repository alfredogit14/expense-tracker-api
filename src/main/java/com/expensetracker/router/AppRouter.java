package com.expensetracker.router;

import com.expensetracker.handler.AuthHandler;
import com.expensetracker.handler.TransactionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class AppRouter {

    @Bean
    public RouterFunction<ServerResponse> routes(TransactionHandler transactionHandler,
                                                  AuthHandler authHandler) {
        return RouterFunctions.route()
                .nest(RequestPredicates.path("/api"), builder -> builder
                        .POST("/auth/login", authHandler::login)
                        .GET("/transactions", transactionHandler::getAll)
                        .POST("/transactions", transactionHandler::create)
                        .DELETE("/transactions/{id}", transactionHandler::delete)
                )
                .build();
    }
}
