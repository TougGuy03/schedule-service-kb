package com.example.scheduleservice.model.api;

import java.time.OffsetDateTime;
import java.util.List;

public record ScheduleGetById(

        String id,

        String scheduleName,

        List<String> scheduleTags,

        OffsetDateTime creationDate
) {
}
