package com.example.umc9th.global.entity;


import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
/*
@MappedSuperclass가 선언되어 있는 클래스는 엔티티 X
<-> 자식 클래스에게 매핑 정보만 제공함
+ 조회, 검색 불가능
(JPA에서 @Entity 클래스는 @Entity, @MappedSuperclass로 지정된 클래스만 상속 가능)
 */
@EntityListeners(AuditingEntityListener.class)
@Getter
public abstract class BaseEntity {

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
}
