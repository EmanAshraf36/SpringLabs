package com.example.advice;

import org.springframework.aop.ThrowsAdvice;

import java.lang.reflect.Method;
import java.util.Arrays;

public class LoggingThrowsAdvice implements ThrowsAdvice {

    public void afterThrowing(Method method, Object[] args, Object target, Exception ex) throws Throwable {

        System.out.println("  [THROWS] " + method.getName() + Arrays.toString(args)
                + " failed " + ex.getClass().getSimpleName() + ": " + ex.getMessage());
    }
}
