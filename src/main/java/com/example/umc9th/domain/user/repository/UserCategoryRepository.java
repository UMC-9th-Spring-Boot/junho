package com.example.umc9th.domain.user.repository;

import com.example.umc9th.domain.user.entity.UserCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserCategoryRepository extends JpaRepository<UserCategory, Long> {
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("DELETE FROM UserCategory uc WHERE uc.user.id = :userId")
    void deleteByUserId(@Param("userId") Long userId);
}
