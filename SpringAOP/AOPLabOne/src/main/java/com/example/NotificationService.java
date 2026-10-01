package com.example;

public interface NotificationService {

    boolean sendEmail(String to, String message);

    boolean sendSms(String to, String message);
}
