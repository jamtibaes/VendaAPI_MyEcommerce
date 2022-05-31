package com.letscode.ecommerce.vendaapi.gateway;

import feign.FeignException;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class UserGateway {

    private final UserGatewayInterface userGatewayInterface;

    public UserGateway(UserGatewayInterface userGatewayInterface) {
        this.userGatewayInterface = userGatewayInterface;
    }

    public Mono<String> getUser(Long userId) {
        return userGatewayInterface.getUser(userId).onErrorResume(
                e -> Mono.just(""));
                //FeignException.NotFound.class, e -> Mono.empty());

    }

}
