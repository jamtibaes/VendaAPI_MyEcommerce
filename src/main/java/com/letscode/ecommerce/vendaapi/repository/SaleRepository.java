package com.letscode.ecommerce.vendaapi.repository;

import com.letscode.ecommerce.vendaapi.entity.SaleEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleRepository  extends ReactiveMongoRepository<SaleEntity, String> {

}
