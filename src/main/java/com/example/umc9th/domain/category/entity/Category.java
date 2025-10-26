package com.example.umc9th.domain.category.entity;

import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.user.entity.User;
import com.example.umc9th.domain.user.entity.UserCategory;
import com.example.umc9th.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Set;

@Table(name = "category")
@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@EntityListeners(AuditingEntityListener.class)
public class Category extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;

    @Column(nullable = false)
    private String categoryName;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UserCategory> userCategories;
}
