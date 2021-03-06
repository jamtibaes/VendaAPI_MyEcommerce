package com.letscode.ecommerce.vendaapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import reactivefeign.spring.config.EnableReactiveFeignClients;

@SpringBootApplication
@EnableReactiveFeignClients
public class EcommerceVendaApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(EcommerceVendaApiApplication.class, args);
    }

}
