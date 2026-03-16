package com.example.scheduleservice.model.api;

public record PeriodSearchRequest(
        PeriodFilter filter,
        PeriodSort sort,
        Integer page
) {
}
