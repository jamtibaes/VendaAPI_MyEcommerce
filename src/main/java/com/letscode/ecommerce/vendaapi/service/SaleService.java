package com.letscode.ecommerce.vendaapi.service;

import com.letscode.ecommerce.vendaapi.entity.CartEntity;
import com.letscode.ecommerce.vendaapi.entity.SaleEntity;
import com.letscode.ecommerce.vendaapi.gateway.CartGateway;
import com.letscode.ecommerce.vendaapi.gateway.CartGatewayOld;
import com.letscode.ecommerce.vendaapi.gateway.UserGateway;
import com.letscode.ecommerce.vendaapi.repository.SaleRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
public class SaleService {

    private final SaleRepository saleRepository;
    private final CartGateway cartGateway;
    private final UserGateway userGateway;
    private final CartGatewayOld cartGatewayOld;

    public SaleService(SaleRepository saleRepository, CartGateway cartGateway, UserGateway userGateway, CartGatewayOld cartGatewayOld) {
        this.saleRepository = saleRepository;
        this.cartGateway = cartGateway;
        this.userGateway = userGateway;
        this.cartGatewayOld = cartGatewayOld;
    }

    public Flux<SaleEntity> findAll() {
        return saleRepository.findAll();
    }

    public Mono<SaleEntity> create(SaleEntity sale) {
        return saleRepository.save(sale);
    }

    public Mono<SaleEntity> findById(String id) {
        return saleRepository.findById(id);
    }

    public Mono<SaleEntity> verifyUserCart(SaleEntity saleEntity) {
        return Mono.zip(
                Mono.just(saleEntity)
                        .flatMap(sale -> cartGateway.getCart(sale.getCartId())),
                Mono.just(saleEntity)
                        .flatMap(sale -> userGateway.getUser(sale.getUserId()))
        ).map(t -> saleEntity);
    }

    public Mono<CartEntity> verifyCart(CartEntity cartEntity) {
        return Mono.just(cartEntity)
                        .flatMap(sale -> cartGatewayOld.getCartObj(cartEntity.getId()));
    }


}
