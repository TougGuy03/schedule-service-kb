package com.example.scheduleservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.scheduleservice.model.domain.Period;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.OffsetTime;
import java.util.List;

public interface PeriodRepository extends JpaRepository<Period, String>, JpaSpecificationExecutor<Period> {
    @Query("""
        select case when count(p) > 0 then true else false end
           from Period p
           where (p.administrator.id = :userId or p.executor.id = :userId)
             and p.slot.beginDate < :endDate
             and p.slot.endDate > :beginDate
    """)
    boolean existsOverlappingPeriod(
            @Param("userId") String userId,
            @Param("beginDate") OffsetTime beginDate,
            @Param("endDate") OffsetTime endDate
    );

    List<Period> findByScheduleIdOrderBySlotBeginDate(String scheduleId);
}
