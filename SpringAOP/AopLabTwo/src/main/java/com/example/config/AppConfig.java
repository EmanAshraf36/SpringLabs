package com.example.config;

import com.example.advice.LoggingAfterReturningAdvice;
import com.example.advice.LoggingBeforeAdvice;
import com.example.advice.LoggingThrowsAdvice;
import com.example.service.InventoryServiceImpl;
import com.example.advice.TimingInterceptor;
import org.aopalliance.intercept.MethodInterceptor;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.ThrowsAdvice;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@ComponentScan("com.example.client")
public class AppConfig {

    @Bean
    public InventoryServiceImpl inventoryServiceTarget() {
        return new InventoryServiceImpl();
    }

    @Bean
    public MethodInterceptor timingInterceptor() {
        return new TimingInterceptor();
    }

    @Bean
    public MethodBeforeAdvice loggingBeforeAdvice() {
        return new LoggingBeforeAdvice();
    }

    @Bean
    public AfterReturningAdvice loggingAfterReturningAdvice() {
        return new LoggingAfterReturningAdvice();
    }

    @Bean
    public ThrowsAdvice loggingThrowsAdvice() {
        return new LoggingThrowsAdvice();
    }

    @Bean
    @Primary
    public ProxyFactoryBean inventoryService() {
        ProxyFactoryBean factory = new ProxyFactoryBean();
        factory.setTarget(inventoryServiceTarget());
        factory.setInterceptorNames(
                "timingInterceptor",
                "loggingBeforeAdvice",
                "loggingAfterReturningAdvice",
                "loggingThrowsAdvice");
        return factory;
    }
}
