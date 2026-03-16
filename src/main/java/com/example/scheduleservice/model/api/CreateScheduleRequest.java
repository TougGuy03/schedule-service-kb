package com.example.scheduleservice.model.api;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record CreateScheduleRequest(
        @NotNull
        String scheduleName,

        List<String> scheduleTags
) {
}
