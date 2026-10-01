package com.example;

import com.example.advice.LoggingAfterReturningAdvice;
import com.example.advice.LoggingBeforeAdvice;
import com.example.advice.LoggingThrowsAdvice;
import com.example.advice.TimingInterceptor;
import com.example.service.InventoryService;
import com.example.service.InventoryServiceImpl;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.AopUtils;

public class ProxyFactoryMain {

    public static void main(String[] args) {

        InventoryService target = new InventoryServiceImpl();
        ProxyFactory factory = new ProxyFactory(target);

        factory.addAdvice(new TimingInterceptor());
        factory.addAdvice(new LoggingBeforeAdvice());
        factory.addAdvice(new LoggingAfterReturningAdvice());
        factory.addAdvice(new LoggingThrowsAdvice());

        InventoryService proxy = (InventoryService) factory.getProxy();

        //to make sure the dynamic proxy is being generated
        System.out.println("Proxy class: " + proxy.getClass().getName()
                + " | JDK proxy? " + AopUtils.isJdkDynamicProxy(proxy));

        System.out.println("-------- 1.Check stock ----------");
        int units = proxy.checkStock("XiaomiPad");
        System.out.println("got " + units + " units");

        System.out.println("-------- 2.Reserve stock of 66 --------");
        proxy.reserveStock("XiaomiPad", 66);
        System.out.println("4. After reserve stock " + proxy.checkStock("XiaomiPad"));


        //Throws exception
        System.out.println("---------- 3.Reserve stock of 120 ---------");
        try {
            proxy.reserveStock("XiaomiPad", 120);
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }

        System.out.println("4. After reserve stock " + proxy.checkStock("XiaomiPad"));




    }
}
