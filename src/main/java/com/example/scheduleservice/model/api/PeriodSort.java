package com.example.scheduleservice.model.api;

import org.hibernate.query.SortDirection;

public record PeriodSort(
        String field,
        String direction
) {
}
