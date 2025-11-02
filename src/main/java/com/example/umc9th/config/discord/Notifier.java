package com.example.umc9th.config.discord;

public interface Notifier {
    void sendNotification(Exception e, String requestUri);
}
