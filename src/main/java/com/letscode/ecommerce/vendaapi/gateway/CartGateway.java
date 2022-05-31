package com.letscode.ecommerce.vendaapi.gateway;

import feign.FeignException;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class CartGateway {

    private final CartGatewayInterface cartGatewayInterface;

    public CartGateway(CartGatewayInterface cartGatewayInterface) {
        this.cartGatewayInterface = cartGatewayInterface;
    }

    public Mono<String> getCart(String cartId) {
        return cartGatewayInterface.getCart(cartId).onErrorResume(
                e -> Mono.just(""));
                //FeignException.NotFound.class, e -> Mono.empty());
    }

}
