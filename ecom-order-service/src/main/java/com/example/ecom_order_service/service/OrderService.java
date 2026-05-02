package com.example.ecom_order_service.service;

import com.example.ecom_order_service.client.InventoryClient;
import com.example.ecom_order_service.dto.Inventory;
import com.example.ecom_order_service.exceptions.MyCustomRuntimeException;
import org.jspecify.annotations.Nullable;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

import java.io.InputStream;

@Service
public class OrderService {

    private final RestTemplate restTemplate;
    private final RestClient restClient;
    private final InventoryClient inventoryClient;

    public OrderService(RestTemplate restTemplate, RestClient restClient, InventoryClient inventoryClient) {
        this.restTemplate = restTemplate;
        this.restClient = restClient;
        this.inventoryClient = inventoryClient;
    }

    public String placeOrder(Long productId) {

        // Using RestTemplate
        /*
        String response = restTemplate.getForObject(
                "http://localhost:8081/inventory/" + productId,
                String.class
        );
        */

        // Using RestClient
        /*
        ResponseEntity<Inventory> entity = restClient.get()
                .uri("http://localhost:8081/inventory/{productId}", productId)
                .retrieve()
                .toEntity(Inventory.class);
        */

        /*
        ResponseEntity<Inventory> entity = restClient.get()
                .uri("http://localhost:8081/inventory/{productId}", productId)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, ((request, response) -> {
                    throw new MyCustomRuntimeException(response.getStatusCode(), response.getHeaders());
                } ))
                .toEntity(Inventory.class);
        */

        /*
        // here we need to convert Object to Entity
        Object exchange = restClient.get()
                .uri("http://localhost:8081/inventory/{productId}", productId)
                .exchange(((clientRequest, clientResponse) -> {
                    if (clientResponse.getStatusCode().is4xxClientError()) {
                        throw new MyCustomRuntimeException(clientResponse.getStatusCode(), clientResponse.getHeaders());
                    } else {
                        return clientResponse.getBody();
                    }
                }));
         */

        Inventory inventory = inventoryClient.getInventory(productId);

        int quantity = inventory.getQuantity();
        updateInventory(inventory);

        return quantity > 0
                ? "Order Placed Successfully"
                : "Out of Stock!!";
    }

    private void updateInventory(@Nullable Inventory inventory) {

        assert inventory != null;
        inventory.setQuantity(inventory.getQuantity() - 1);

        /*restClient.post()
                .uri("http://localhost:8081/inventory")
                .body(inventory)
                .retrieve()
                .toBodilessEntity();*/

        inventoryClient.updateInventory(inventory);
    }
}
