package com.letscode.ecommerce.vendaapi.handler;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class SaleHandler {
    public Mono<ServerResponse> create(ServerRequest request) {
        return ServerResponse.ok().build();
    }

    public Mono<ServerResponse> list(ServerRequest request) {
        return ServerResponse.ok().build();
    }

    public Mono<ServerResponse> get(ServerRequest request) {
        return ServerResponse.ok().build();
    }
}
