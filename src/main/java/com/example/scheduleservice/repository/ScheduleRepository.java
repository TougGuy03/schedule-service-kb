package com.example.scheduleservice.repository;

import com.example.scheduleservice.model.domain.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ScheduleRepository extends JpaRepository<Schedule, String> {
    Optional<Schedule> findByScheduleName(String scheduleName);
}
