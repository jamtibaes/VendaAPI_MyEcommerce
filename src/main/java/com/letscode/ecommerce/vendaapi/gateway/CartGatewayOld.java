package com.letscode.ecommerce.vendaapi.gateway;

import com.letscode.ecommerce.vendaapi.entity.CartEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;
import reactor.util.retry.RetrySpec;

import java.time.Duration;

@Component
public class CartGatewayOld {

    @Value("${cart.url}")
    private String cartUrl;


    public Mono<String> getCart(String cartId) {
        return WebClient
                .builder()
                .baseUrl(String.format(cartUrl, cartId))
                .build()
                .get()
                .retrieve()
                .bodyToMono(String.class)
                .onErrorResume(WebClientResponseException.class, e ->
                        e.getRawStatusCode() == 404 ? Mono.empty() : Mono.error(e))
                .retryWhen(RetrySpec.fixedDelay(5, Duration.ofSeconds(5, 0)));
    }

    public Mono<CartEntity> getCartObj(String cartId) {
        return WebClient
                .builder()
                .baseUrl(String.format("http://carrinho-mongo-app-instance:8082/v1/api/cart/%s", cartId))
                .build()
                .get()
                .retrieve()
                .bodyToMono(CartEntity.class)
                .onErrorResume(WebClientResponseException.class, e ->
                        e.getRawStatusCode() == 404 ? Mono.empty() : Mono.error(e))
                .retryWhen(RetrySpec.fixedDelay(5, Duration.ofSeconds(5, 0)));
    }



}
