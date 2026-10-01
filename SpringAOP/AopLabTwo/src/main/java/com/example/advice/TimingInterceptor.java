package com.example.advice;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class TimingInterceptor implements MethodInterceptor { //Around

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("  [BEFORE-INVOKE] " + methodInvocation.getMethod().getName());

        try {
            return methodInvocation.proceed(); //call next advice

        } finally {
            long end = System.currentTimeMillis();
            System.out.println("Duration of: "+methodInvocation.getMethod().getName()+ " was "  + (end - start) + " ms ");
        }
    }
}
