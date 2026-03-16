package com.example.scheduleservice.model.api;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record CreateScheduleRequest(
        @NotNull
        String scheduleName,

        List<String> scheduleTags
) {
}
