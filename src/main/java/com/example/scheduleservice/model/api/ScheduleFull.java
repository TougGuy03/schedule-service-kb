package com.example.scheduleservice.model.api;

import java.util.List;

public record ScheduleFull(
        ScheduleGetById schedule,
        List<PeriodGetById> periods
) {
}
