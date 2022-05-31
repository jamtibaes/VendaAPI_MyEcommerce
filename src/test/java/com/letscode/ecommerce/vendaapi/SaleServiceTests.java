package com.letscode.ecommerce.vendaapi;

import com.letscode.ecommerce.vendaapi.entity.CartEntity;
import com.letscode.ecommerce.vendaapi.entity.ItemsEntity;
import com.letscode.ecommerce.vendaapi.gateway.CartGateway;
import com.letscode.ecommerce.vendaapi.gateway.CartGatewayOld;
import com.letscode.ecommerce.vendaapi.gateway.UserGateway;
import com.letscode.ecommerce.vendaapi.service.SaleService;
import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.test.StepVerifier;

import java.io.IOException;

@WebFluxTest
@ExtendWith(SpringExtension.class)
@Import({SaleService.class, UserGateway.class, CartGateway.class, CartGatewayOld.class})
@Disabled
public class SaleServiceTests {

    static MockWebServer mockWebServer;

    @Autowired
    private SaleService saleService;

    static Dispatcher dispatcher = new Dispatcher() {

        @NotNull
        @Override
        public MockResponse dispatch(@NotNull RecordedRequest request) throws InterruptedException {
            return switch (request.getPath()){


                case "/v1/api/cart/6293aa3b9e2d8b45a212f275" -> new MockResponse()
                    .setResponseCode(200)
                    .setBody("{'id':'6293aa3b9e2d8b45a212f275','productList':[{'productId':'1','quantity':'20','price':'600.0'}]}");
                case "/v1/api/cart/6293aa3b9e2d8b45a212f276" -> new MockResponse()
                    .setResponseCode(500)
                    .setBody("Server Cart Error");
                case "/v1/api/cart/6293aa3b9e2d8b45a212f277"  -> new MockResponse()
                    .setResponseCode(404)
                    .setBody("Cart not found");

                case "/v1/api/user/1" -> new MockResponse()
                        .setResponseCode(200)
                        .setBody("{'id':1,'name':'teste','password':'teste'}");
                case "/v1/api/user/2" -> new MockResponse()
                        .setResponseCode(500)
                        .setBody("Server User Error");
                case "/v1/api/user/3"  -> new MockResponse()
                        .setResponseCode(404)
                        .setBody("User not found");
                default -> new MockResponse().setResponseCode(404);

            };
        }
    };



    @BeforeAll
    public static void setUp() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.setDispatcher(dispatcher);
        mockWebServer.start(9000);
    }

    @Test
    void testVerifyUserCart() {
        CartEntity cartEntity = new CartEntity();
        ItemsEntity itemsEntity = new ItemsEntity();

        itemsEntity.setProductId(1L);
        itemsEntity.setQuantity(20);
        itemsEntity.setPrice(600.0);

        cartEntity.setId("6293aa3b9e2d8b45a212f275");
        cartEntity.getProductList().add(itemsEntity);

        StepVerifier.create(saleService.verifyCart(cartEntity))
                .consumeNextWith(cartEntity1 -> {
                    assert cartEntity1.getId().equals("6293aa3b9e2d8b45a212f275");
                    assert cartEntity1.getProductList().size() == 1;
                    assert cartEntity1.getProductList().get(0).getProductId().equals(1L);
                    assert cartEntity1.getProductList().get(0).getQuantity().equals(20);
                    assert cartEntity1.getProductList().get(0).getPrice().equals(600.0);
                })
                .verifyComplete();
    }



}
