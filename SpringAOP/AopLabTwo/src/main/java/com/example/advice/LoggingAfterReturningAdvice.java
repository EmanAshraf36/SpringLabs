package com.example.advice;

import org.springframework.aop.AfterReturningAdvice;

import java.lang.reflect.Method;

public class LoggingAfterReturningAdvice implements AfterReturningAdvice {

    @Override
    public void afterReturning(Object returnValue, Method method,Object[] args, Object target) throws Throwable {
        if(method.getReturnType() == void.class){
            System.out.println("  [AFTER-RETURNING] " + method.getName() + " completed (void)");
        }
        else{
            System.out.println("  [AFTER-RETURNING] " + method.getName() + " returned " + returnValue);
        }
    }
}
