package com.example.umc9th.config.discord;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.awt.*;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.*;
import java.util.List;

@Service
@RequiredArgsConstructor
@Profile({"prod","staging"})
public class DiscordNotifierService implements Notifier{
    private final RestTemplate restTemplate;

    @Value( "${discord.webhook-url}")
    private String discordWebhookUrl;

    @Async
    @Override
    public void sendNotification(Exception e, String requestUri) {
        try {
            /*
            RestTemplate은 HttpMessageConverter를 이용해 직렬화
            -> MappingJackson2HttpMessageConverter

            spring-boot-starter-web : Jackson 포함
            */
            Map<String, Object> payload = createDiscordPayload(e, requestUri);
            restTemplate.postForEntity(discordWebhookUrl, payload, String.class);
        } catch (Exception ex) {
            // 알림 전송 실패 시, 원본 예외 처리에 영향을 주지 않도록 내부에서 처리
            System.err.println("Discord 알림 전송 실패: " + ex.getMessage());
        }
    }

    // Discord Embed 메시지 페이로드 생성
    private Map<String, Object> createDiscordPayload(Exception e, String requestUri) {
        // Embed 객체 생성
        Map<String, Object> embed = new HashMap<>();
        embed.put("title", "🚨 서버 에러 발생!");
        embed.put("color", Color.RED.getRGB() & 0xFFFFFF); // 16진수 Red 컬러

        // 스택 트레이스를 문자열로 변환 (간결하게)
        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw));
        String stackTrace = sw.toString().substring(0, Math.min(1000, sw.toString().length())) + "..."; // 1000자로 제한

        // Embed에 필드 추가
        embed.put("fields", List.of(
                Map.of("name", "Request URI", "value", "`" + requestUri + "`", "inline", false),
                Map.of("name", "Exception Type", "value", "`" + e.getClass().getSimpleName() + "`", "inline", false),
                Map.of("name", "Message", "value", e.getMessage(), "inline", false),
                Map.of("name", "Stack Trace (Short)", "value", "```\n" + stackTrace + "\n```", "inline", false)
        ));

        // 최상위 페이로드
        Map<String, Object> payload = new HashMap<>();
        payload.put("username", "500에러 발생!"); // 웹훅 이름
        payload.put("embeds", List.of(embed));

        return payload;
    }
}
