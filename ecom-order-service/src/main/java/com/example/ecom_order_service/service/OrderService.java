package com.example.ecom_order_service.service;

import com.example.ecom_order_service.dto.Inventory;
import org.jspecify.annotations.Nullable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {

    private final RestTemplate restTemplate;
    private final RestClient restClient;

    public OrderService(RestTemplate restTemplate, RestClient restClient) {
        this.restTemplate = restTemplate;
        this.restClient = restClient;
    }

    public String placeOrder(Long productId) {

        // Using RestTemplate
//        String response = restTemplate.getForObject(
//                "http://localhost:8081/inventory/" + productId,
//                String.class
//        );

        ResponseEntity<Inventory> entity = restClient.get()
                .uri("http://localhost:8081/inventory/{productId}", productId)
                .retrieve()
                .toEntity(Inventory.class);

        System.out.println(entity.getStatusCode());

        updateInventory(entity.getBody());

        return entity.getBody() != null && entity.getBody().getQuantity() > 0
                ? "Order Placed Successfully"
                : "Out of Stock!!";
    }

    private void updateInventory(@Nullable Inventory inventory) {

        assert inventory != null;
        inventory.setQuantity(inventory.getQuantity() - 1);

        restClient.post()
                .uri("http://localhost:8081/inventory")
                .body(inventory)
                .retrieve()
                .toBodilessEntity();
    }
}
