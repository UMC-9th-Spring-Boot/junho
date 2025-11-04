package com.example.umc9th.global.notification;

public interface Notifier {
    NotificationType getType();
    void sendNotification(Exception e, String requestUri);
}
