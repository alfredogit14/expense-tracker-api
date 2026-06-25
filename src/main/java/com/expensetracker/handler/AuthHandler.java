package com.expensetracker.handler;

import com.expensetracker.model.LoginRequest;
import com.expensetracker.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class AuthHandler {

    private final JwtUtil jwtUtil;

    // Demo users — in production replace with DB + password encoding
    private static final Map<String, String> USERS = Map.of(
            "demo", "password123",
            "admin", "admin123"
    );

    public Mono<ServerResponse> login(ServerRequest request) {
        return request.bodyToMono(LoginRequest.class)
                .flatMap(req -> {
                    String stored = USERS.get(req.username());
                    if (stored != null && stored.equals(req.password())) {
                        String token = jwtUtil.generateToken(req.username());
                        return ServerResponse.ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(Map.of("token", token, "username", req.username()));
                    }
                    return ServerResponse.status(HttpStatus.UNAUTHORIZED)
                            .bodyValue(Map.of("error", "Invalid credentials"));
                });
    }
}
