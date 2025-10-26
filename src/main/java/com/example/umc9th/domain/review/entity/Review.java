package com.example.umc9th.domain.review.entity;

import com.example.umc9th.domain.inqury.entity.InquiryImage;
import com.example.umc9th.domain.store.entity.Store;
import com.example.umc9th.domain.user.entity.User;
import com.example.umc9th.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.ArrayList;
import java.util.List;

@Table(name = "review")
@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@EntityListeners(AuditingEntityListener.class)
public class Review extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @Column(nullable = false)
    private String content;

    @OneToMany(mappedBy = "review", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<ReviewImage> reviewImages = new ArrayList<>();

    @Builder.Default
    @OneToOne(mappedBy = "review", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private ReviewReply reviewReply = null;
    /*
    ==== 처음 작성했던 코드 ====
    @Column(nullable = false)
    @Builder.Default
    private Boolean hasReply = false;

   -> 1. 리뷰 삭제시 리뷰 답글은 어떻게?
      2. 'select * from review_reply where review_id = ?' vs 'select hasReply from review where review_id = ?'
         (실제 쿼리 비슷할거 같은데 굳이 필드 추가..?)
     */

}
