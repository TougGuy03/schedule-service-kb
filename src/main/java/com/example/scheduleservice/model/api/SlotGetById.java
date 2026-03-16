package com.example.scheduleservice.model.api;

import com.example.scheduleservice.model.enums.Priority;

import java.time.OffsetTime;

public record SlotGetById(
        String id,
        String templateId,
        OffsetTime beginDate,
        OffsetTime endDate,
        Priority priority
) {
}
