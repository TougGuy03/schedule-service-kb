package com.example.scheduleservice.model.api;

import com.example.scheduleservice.model.domain.Employee;
import com.example.scheduleservice.model.domain.Schedule;
import com.example.scheduleservice.model.domain.Slot;
import com.example.scheduleservice.model.enums.SlotType;
import jakarta.validation.constraints.NotNull;

public record CreatePeriodRequest(
        @NotNull
        String slotId,

        @NotNull
        String scheduleId,

        @NotNull
        SlotType slotType,

        String executorId
) {
}
