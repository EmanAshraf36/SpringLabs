package com.example.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class InventoryServiceImpl implements InventoryService {

   private final Map<String, Integer> stock = new HashMap<>();

    public InventoryServiceImpl() {
        stock.put("XiaomiPad", 200);
        stock.put("B", 2);
    }

    @Override
    public int checkStock(String product) {
        return stock.get(product);
    }

    @Override
    public void reserveStock(String product, int qty) {
        if(qty > 100){
            throw new IllegalArgumentException("Cannot reserve more than 100 stock");
        }
        stock.put(product, stock.get(product) - qty);
        System.out.println("    >> [REAL] Reserved " + qty + " " + product);

    }
}
