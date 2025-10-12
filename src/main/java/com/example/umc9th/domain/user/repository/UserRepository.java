package com.example.umc9th.domain.user.repository;

import com.example.umc9th.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {
    /*
    @Modifying : 영속성 컨텍스트와 상관없이 DB에 직접 실행 -> 데이터 불일치 가능성(옵션 설정)

    clearAutomatically : clear()
    flushAutomatically : flush()
    => 둘 다 default는 false
     */
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("UPDATE User u SET u.isDeleted = true WHERE u.id = :userId")
    void softDeleteUser(@Param("userId") Long userId);
}
