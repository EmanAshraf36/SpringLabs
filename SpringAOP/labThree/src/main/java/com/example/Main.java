package com.example;

import com.example.AppConfig;
import com.example.service.InventoryService;
import com.example.service.ProductService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        // ---------- Part A ----------
        InventoryService inventory = context.getBean(InventoryService.class);

        System.out.println("--- 1. checkStock ---");
        System.out.println("Main got: " + inventory.checkStock("XiaomiPad"));

        System.out.println("--- 2. reserveStock 5 ---");
        inventory.reserveStock("XiaomiPad", 5);

        System.out.println("--- 3. reserveStock 150 (error) ---");
        try {
            inventory.reserveStock("XiaomiPad", 150);
        } catch (IllegalStateException e) {
            System.out.println("Main caught: " + e.getMessage());
        }

        // ---------- Part B ----------
        ProductService products = context.getBean(ProductService.class);

        System.out.println("--- 4. getPrice, first time ---");
        System.out.println("Main got: " + products.getPrice("XiaomiPad"));

        System.out.println("--- 5. getPrice, second time ---");
        System.out.println("Main got: " + products.getPrice("XiaomiPad"));

        context.close();
    }
}