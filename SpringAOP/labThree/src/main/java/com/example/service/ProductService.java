package com.example.service;

import com.example.cache.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Cacheable
    public double getPrice(String product) {
        System.out.println("    >> Looking up the price of " + product + " (slow...)");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return 32500.0;
    }
}
