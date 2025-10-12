package com.example.umc9th.domain.user.repository;

import com.example.umc9th.domain.user.entity.TermAgreement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TermAgreementRepository extends JpaRepository<TermAgreement, Long> {
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("DELETE FROM TermAgreement ta WHERE ta.user.id = :userId")
    void deleteByUserId(@Param("userId") Long userId);
}
