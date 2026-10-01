package com.example;

import java.lang.reflect.Proxy;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        NotificationService real = new NotificationServiceImpl();

        //RunTime proxy
        NotificationService proxy = (NotificationService) Proxy.newProxyInstance(
                NotificationService.class.getClassLoader(),
                new Class<?>[]{NotificationService.class},
                new LoggingHandler(real)
        );

        System.out.println("----Calling the real method----");
        real.sendSms("instructor","Hello World");

        System.out.println("--------Calling proxy.sendEmail--------- ");
        boolean emailOk = proxy.sendEmail("Eman@email.com",   "Here's the proxy email");
        System.out.println("email Ok?: "+  emailOk );

         System.out.println("------Calling the proxy.sendSMS---------");
         boolean smsOk = proxy.sendSms("EmanAshraf", "Here's the proxy sms");
         System.out.println("SMS Ok?: "+  smsOk );


    }
}