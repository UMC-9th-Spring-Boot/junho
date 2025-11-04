package com.example.umc9th.global.notification;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class NotificationService {
    private final Map<NotificationType, Notifier> notifierMap;

    /**
     *
     * @param notifierList : 스프링이 NotificationService빈 등록시 Notifier 모든 구현체를 리스트로 주입함
     * 여러개의 Notifier구현체 중 개발자가 선택해서 사용하기 쉽도록 Map<타입,구현체>를 제공하는 서비스 클래스
     */
    public NotificationService(List<Notifier> notifierList) {
        this.notifierMap = notifierList.stream()
                .collect(Collectors.toMap(Notifier::getType, n -> n));
    }

    public void send(NotificationType type, Exception e, String requestUri) {
        notifierMap.get(type).sendNotification(e, requestUri);
    }
}
