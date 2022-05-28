package com.letscode.ecommerce.vendaapi.config;

import com.letscode.ecommerce.vendaapi.handler.SaleHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.http.MediaType.*;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class RouterConfig {

    @Bean
    public RouterFunction<ServerResponse> route(SaleHandler saleHandler) {
        return RouterFunctions
                .route(GET("/v1/api/sale").and(contentType(APPLICATION_JSON)), saleHandler::list)
                .andRoute(POST("v1//api/sale").and(contentType(APPLICATION_JSON)), saleHandler::create);
    }
}
