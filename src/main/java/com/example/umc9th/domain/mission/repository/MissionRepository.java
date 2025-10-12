package com.example.umc9th.domain.mission.repository;

import com.example.umc9th.domain.mission.dto.MissionInAreaDTO;
import com.example.umc9th.domain.mission.entity.Mission;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    @Query("SELECT new com.example.umc9th.domain.mission.dto.MissionInAreaDTO(s.name, m.createdAt, m.content, m.point) " +
           "FROM Mission m JOIN m.store s " +
           "WHERE s.address LIKE CONCAT(:regionName, '%') AND m.createdAt < :lastCreatedAt " +
           "ORDER BY m.createdAt DESC")
    Slice<MissionInAreaDTO> findMissionsInArea(@Param("regionName") String regionName, @Param("lastCreatedAt") LocalDateTime lastCreatedAt, Pageable pageable);
}
