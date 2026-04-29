package com.example.ecom_inventory_service.service;

import org.springframework.stereotype.Service;

@Service
public class InventoryService {

    public String checkInventory(String productId) {

        System.out.println("Checking inventory for productId " + productId);

        return productId.equals("1") ? "In Stock" : "Out of Stock";
    }
}
