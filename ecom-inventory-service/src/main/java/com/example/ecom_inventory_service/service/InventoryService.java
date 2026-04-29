package com.example.ecom_inventory_service.service;

import com.example.ecom_inventory_service.model.Inventory;
import com.example.ecom_inventory_service.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InventoryService {

    @Autowired
    InventoryRepository inventoryRepository;

    public Inventory checkInventory(Long productId) {

        Optional<Inventory> inv = inventoryRepository.findById(productId);

        System.out.println("Checking inventory for productId " + productId);

        return inv.get();}

    public String addProduct(Inventory inventory) {
        inventoryRepository.save(inventory);
        return "Product Added";
    }

    public String updateProduct(Inventory inventory) {
        inventoryRepository.save(inventory);
        return "Product Updated";
    }
}
