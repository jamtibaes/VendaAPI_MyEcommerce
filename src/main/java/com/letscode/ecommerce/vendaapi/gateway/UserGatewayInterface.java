package com.letscode.ecommerce.vendaapi.gateway;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Mono;

@ReactiveFeignClient(name = "user-service")
public interface UserGatewayInterface {

    @GetMapping("/v1/api/user/{userId}")
    Mono<String> getUser(@PathVariable("userId") Long userId);

}
