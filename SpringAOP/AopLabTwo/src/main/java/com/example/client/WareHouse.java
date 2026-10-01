package com.example.client;

import com.example.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WareHouse {

    private final InventoryService inventoryService;

    @Autowired
    public WareHouse(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    public void processOrder(String sku, int qty) {
        int available = inventoryService.checkStock(sku);
        if (available >= qty) {
            inventoryService.reserveStock(sku, qty);
        } else {
            System.out.println("    >> [CLIENT] Not enough " + sku + " have " + available + ", need " + qty);
        }
    }

    public InventoryService getInventoryService() {
        return inventoryService;
    }
}
