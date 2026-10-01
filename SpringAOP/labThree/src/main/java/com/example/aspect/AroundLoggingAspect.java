package com.example.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class AroundLoggingAspect {

    @Around("execution(* com.training.aop2.service.InventoryService.*(..))")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        String name = pjp.getSignature().getName();

        System.out.println("[BEFORE] " + name + " " + Arrays.toString(pjp.getArgs()));
        try {
            Object result = pjp.proceed();
            System.out.println("[AFTER-RETURNING] " + name + " returned " + result);
            return result;
        } catch (Exception ex) {
            System.out.println("[AFTER-THROWING] " + name + " failed: " + ex.getMessage());
            throw ex;
        } finally {
            System.out.println("[AFTER] " + name);
        }
    }
}
