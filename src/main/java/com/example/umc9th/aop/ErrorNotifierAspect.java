package com.example.umc9th.aop;

import com.example.umc9th.global.notification.NotificationService;
import com.example.umc9th.global.notification.NotificationType;
import com.example.umc9th.global.notification.Notifier;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
@Profile({"prod","staging"})
@RequiredArgsConstructor
public class ErrorNotifierAspect extends CommonPointCut {

    private final NotificationService notifier;

    @AfterThrowing(pointcut = "controllerPointcut()", throwing = "e")
    public void sendDiscordAlert(JoinPoint joinPoint, Exception e) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String requestURI = request.getRequestURI();
        notifier.send(NotificationType.DISCORD,e, requestURI);
    }
}
