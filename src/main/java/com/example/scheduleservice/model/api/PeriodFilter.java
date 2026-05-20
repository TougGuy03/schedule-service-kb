package com.example.scheduleservice.model.api;

import com.example.scheduleservice.model.enums.SlotType;

import java.time.LocalDate;

public record PeriodFilter(
        String id,
        String slotId,
        String scheduleId,
        SlotType slotType,
        String administratorId,
        String executorId,
        LocalDate from,
        LocalDate to
) {
}
