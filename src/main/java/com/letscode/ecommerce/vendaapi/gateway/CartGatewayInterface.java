package com.letscode.ecommerce.vendaapi.gateway;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Mono;

@ReactiveFeignClient(name = "cart-service")
public interface CartGatewayInterface {

    @GetMapping("/v1/api/cart/{cartId}")
    Mono<String> getCart(@PathVariable("cartId") String cartId);

}
