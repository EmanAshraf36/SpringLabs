package com.example;

public class NotificationServiceImpl implements NotificationService {

    @Override
    public boolean sendEmail(String to, String message) {
        if(to == null){
            throw new IllegalArgumentException();
        }
        System.out.println("Sending email to " + to + " with message " + message);
        return true;
    }

    @Override
    public boolean sendSms(String to, String message) {
        if(to == null){
            throw new IllegalArgumentException();
        }
        System.out.println("Sending Sms to " + to + " with message " + message);
        return true;
    }
}
