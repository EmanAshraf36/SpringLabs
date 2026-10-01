package com.example.service;

import java.util.HashMap;
import java.util.Map;

public class InventoryServiceImpl implements InventoryService {

    private static final int MAX_RESERVATION = 100;
    private final Map<String, Integer> stock = new HashMap<>();

    public InventoryServiceImpl() {
        stock.put("XiaomiPad", 200);
        stock.put("focusPen", 40);
    }


    @Override
    public int checkStock(String sku) {
        return stock.getOrDefault(sku, 0);
    }

    @Override
    public void reserveStock(String sku, int qty) {
        if(qty > MAX_RESERVATION) {
            throw new IllegalStateException("This amount is above the max quantity, please enter a no. less than 100");
        }
        stock.put(sku, stock.getOrDefault(sku, 0) - qty);
        System.out.println(" >> [REAL] Reserved " + qty + " x " + sku);

    }
}
