package com.example.scheduleservice.model.api;

import com.example.scheduleservice.model.domain.Employee;
import com.example.scheduleservice.model.domain.Schedule;
import com.example.scheduleservice.model.domain.Slot;
import com.example.scheduleservice.model.enums.SlotType;
import jakarta.persistence.*;

public record PeriodGetById(
    String id,

    String slotId,

    String scheduleId,

    SlotType slotType,

    String administratorId,

    String executor
)
{}
