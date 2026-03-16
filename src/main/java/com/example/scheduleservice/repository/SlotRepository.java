package com.example.scheduleservice.repository;

import com.example.scheduleservice.model.domain.Slot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SlotRepository extends JpaRepository<Slot, String> {
}
