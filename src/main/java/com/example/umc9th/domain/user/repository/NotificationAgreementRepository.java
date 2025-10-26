package com.example.umc9th.domain.user.repository;

import com.example.umc9th.domain.user.entity.NotificationAgreement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface NotificationAgreementRepository extends JpaRepository<NotificationAgreement, Long> {
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("DELETE FROM NotificationAgreement na WHERE na.user.id = :userId")
    void deleteByUserId(@Param("userId") Long userId);
}
