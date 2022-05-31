package com.letscode.ecommerce.vendaapi.handler;

import com.letscode.ecommerce.vendaapi.entity.SaleEntity;
import com.letscode.ecommerce.vendaapi.service.SaleService;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.net.URI;

@Component
public class SaleHandler {

    private final SaleService saleService;

    public SaleHandler(SaleService saleService) {
        this.saleService = saleService;
    }

    public Mono<ServerResponse> create(ServerRequest request) {
        return request.bodyToMono(SaleEntity.class)
                .flatMap(saleService::verifyUserCart)
                .flatMap(saleService::create)
                .flatMap(sale -> ServerResponse
                        .created(URI.create(String.format("/v1/api/sale/%s", sale.getId())))
                        .bodyValue(sale))
                .switchIfEmpty(ServerResponse.unprocessableEntity().bodyValue("Invalid Request"));
    }

    public Mono<ServerResponse> list(ServerRequest request) {
        return Mono.just(saleService.findAll())
                .flatMap(sales -> ServerResponse.ok().body(sales, SaleEntity.class));
    }

    public Mono<ServerResponse> get(ServerRequest request) {
        return Mono.just(saleService.findById(request.pathVariable("id")))
                .flatMap(sale -> ServerResponse.ok().body(sale, SaleEntity.class));
    }

}
