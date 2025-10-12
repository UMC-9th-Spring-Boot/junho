package com.example.umc9th.domain.inqury.repository;

import com.example.umc9th.domain.inqury.entity.Inquiry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface InquiryRepository extends JpaRepository<Inquiry, Long> {
    @Modifying
    @Query("DELETE FROM Inquiry i WHERE i.user.id = :userId")
    void deleteByUserId(@Param("userId") Long userId);
}
