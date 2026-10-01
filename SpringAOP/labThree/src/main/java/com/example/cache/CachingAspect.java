package com.example.cache;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CachingAspect {
    private final Map<String, Object> cache = new HashMap<>();

    @Around("@annotation(com.example.cache.Cacheable)")
    public Object cache(ProceedingJoinPoint pjp) throws Throwable {
        String key = pjp.getSignature().getName() + Arrays.toString(pjp.getArgs());

        if (cache.containsKey(key)) {
            System.out.println("found " + key);
            return cache.get(key);
        }

        System.out.println("missed " + key);
        Object result = pjp.proceed();
        cache.put(key, result);
        return result;
    }
}
