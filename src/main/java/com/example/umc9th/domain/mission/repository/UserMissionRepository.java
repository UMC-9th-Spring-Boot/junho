package com.example.umc9th.domain.mission.repository;

import com.example.umc9th.domain.mission.dto.UserMissionDTO;
import com.example.umc9th.domain.mission.entity.UserMission;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserMissionRepository extends JpaRepository<UserMission, Long> {
    @Modifying
    @Query("DELETE FROM UserMission um WHERE um.user.id = :userId")
    void deleteByUserId(@Param("userId") Long userId);


    @Query("SELECT new com.example.umc9th.domain.mission.dto.UserMissionDTO(um.id, m.point, s.name, m.content, um.isCompleted) " +
            "FROM UserMission um " +
            "JOIN um.mission m " +
            "JOIN m.store s " +
            "WHERE um.user = '1' AND um.isCompleted = :isCompleted AND um.id < :lastMissionId " +
            "ORDER BY um.id DESC")
    Slice<UserMissionDTO> findUserMissionsByIsCompleted(@Param("isCompleted") Boolean isCompleted, @Param("lastMissionId") Long lastMissionId, Pageable pageable);
}
