package com.example.umc9th.domain.user.entity;

import com.example.umc9th.domain.category.entity.Category;
import com.example.umc9th.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Table(name = "user_category", uniqueConstraints = {
        @UniqueConstraint(
                name = "user_category_unique",
                columnNames = {"user_id", "category_id"}
        )
})
/*
JPA에서 복합키 사용할 경우 별도의 클래스 필요
-> 단일 키(대리키?)를 사용하고 유니크 제약 조건 추가
 */
@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EntityListeners(AuditingEntityListener.class)
public class UserCategory extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_category_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

}
