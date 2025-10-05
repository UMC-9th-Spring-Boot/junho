package com.example.umc9th.domain.user.entity;

import com.example.umc9th.domain.inqury.entity.Inquiry;
import com.example.umc9th.domain.mission.entity.UserMission;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.user.enums.Gender;
import com.example.umc9th.domain.user.enums.SocialType;
import com.example.umc9th.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Set;

@Table(name = "user")
@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
/*
[PROTECTED]
ORM 기술은 객체를 먼저 생성 후 필드 값을 채워 넣음
-> 이때, 기본 생성자가 반드시 필요
-> 만약 public이라면 외부에서 의도치않은 값이 주입될 수 있음
-> 만약 private이라면 JPA조차 접근하기 어려움
=> 패키지 단위 or 상속 받은 자식 클래스에게만 접근 허용(protected)

[PRIVATE]
객체 생성 내부 로직을 숨김
-> 정적 팩토리 메서드 or 빌더 패턴
(객체 생성의 책임(유효성 검사 등)을 위임)
*/
@Getter
@EntityListeners(AuditingEntityListener.class)
public class User extends BaseEntity {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private Integer point;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private SocialType socialType;

    @Column(nullable = false)
    private Boolean isPhoneVerified;

    @Column(nullable = false)
    @Builder.Default
    private Boolean isDeleted = false;

    /*
    List<> : 중복 요소 허용 o, 순서 보장 o
    Set<> : 중복 요소 허용 x, 순서 보장 x, + 객체 equals(), hashCode() 오버라이딩 필수
     */
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UserMission> userMissions;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Inquiry> inquiries;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Review> userReviews;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UserCategory> userCategories;

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private PhoneVerification userPhoneVerification;

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private NotificationAgreement userNotificationAgreement;

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private TermAgreement userTermAgreement;

}
