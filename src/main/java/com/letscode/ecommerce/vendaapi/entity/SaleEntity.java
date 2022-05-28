package com.letscode.ecommerce.vendaapi.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
public class SaleEntity {

    @Id
    private String id;

    private Long clientId;
    private String cartId;
    private LocalDateTime dataCompra = LocalDateTime.now();


}
