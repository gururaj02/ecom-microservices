package com.example.ecom_order_service.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {

    private final RestTemplate restTemplate;

    public OrderService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String placeOrder(Long productId) {

        String response = restTemplate.getForObject(
                "http://localhost:8081/inventory/" + productId,
                String.class
        );

        return "In Stock".equals(response) ? "Order Placed Successfully" : "Out of Stock!!";
    }
}
