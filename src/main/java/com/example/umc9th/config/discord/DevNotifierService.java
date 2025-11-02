package com.example.umc9th.config.discord;


import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("!prod & !staging")
public class DevNotifierService implements Notifier{
    @Override
    public void sendNotification(Exception e, String requestUri) {
        System.out.println("디스코드 알림 전송(로컬 확인용)");
    }
}
