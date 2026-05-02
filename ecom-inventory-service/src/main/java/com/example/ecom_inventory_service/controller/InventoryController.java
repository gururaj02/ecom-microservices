package com.example.ecom_inventory_service.controller;

import com.example.ecom_inventory_service.model.Inventory;
import com.example.ecom_inventory_service.service.InventoryService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping("/{productId}")
    public Inventory checkInventory(@PathVariable Long productId) {
//        Thread.sleep(15000);
        return inventoryService.checkInventory(productId);
    }

    @PostMapping()
    public String addProduct(@RequestBody Inventory inventory) {
        return inventoryService.addProduct(inventory);
    }

    @PutMapping()
    public String updateProduct(@RequestBody Inventory inventory) {
        return inventoryService.updateProduct(inventory);
    }
}
