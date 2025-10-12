package com.example.umc9th.domain.user.repository;

import com.example.umc9th.domain.user.dto.MyPageDTO;
import com.example.umc9th.domain.user.dto.UserHomeInfoDTO;
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

    @Query("SELECT new com.example.umc9th.domain.user.dto.MyPageDTO(u.id, u.email, u.point, CASE WHEN u.isPhoneVerified = true THEN pv.phone ELSE '미인증' END) " +
           "FROM User u LEFT JOIN u.userPhoneVerification pv " +
           "WHERE u.id = :userId")
    MyPageDTO getMyPageInfo(@Param("userId") Long userId);

    @Query("SELECT new com.example.umc9th.domain.user.dto.UserHomeInfoDTO(u.point, (SELECT COUNT(um) FROM UserMission um WHERE um.user = u AND um.isCompleted = true)) " +
           "FROM User u WHERE u.id = :userId")
    UserHomeInfoDTO getHomeInfo(@Param("userId") Long userId);
}
