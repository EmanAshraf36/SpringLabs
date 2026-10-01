package com.example.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
//@Component
public class LoggingAspect {

    @Before("execution(* com.example.service.InventoryService.*(..))")
    public void before(JoinPoint jp) {
        System.out.println("    >> Before " + jp.getSignature().getName() + " " + Arrays.toString(jp.getArgs()));
    }

    @AfterReturning(pointcut = "execution(* com.example.service.InventoryService.*(..))",
            returning = "result")
    public void afterReturning(JoinPoint jp, Object result) {
        System.out.println("[AFTER-RETURNING] " + jp.getSignature().getName() + " returned " + result);
    }

    @AfterThrowing(pointcut = "execution(* com.example.service.InventoryService.*(..))",
            throwing = "ex")
    public void afterThrowing(JoinPoint jp, Exception ex) {
        System.out.println("[AFTER-THROWING] " + jp.getSignature().getName() + " failed: " + ex.getMessage());
    }

    @After("execution(* com.example.service.InventoryService.*(..))")
    public void after(JoinPoint jp) {
        System.out.println("[AFTER] " + jp.getSignature().getName());
    }
}
