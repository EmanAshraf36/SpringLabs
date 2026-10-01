package com.example.service;

import java.util.HashMap;
import java.util.Map;

public interface InventoryService {

    int checkStock(String product);

    void reserveStock(String product, int qty);
}
