package com.example.scheduleservice.model.api;

import com.example.scheduleservice.model.enums.EmployeeStatus;
import com.example.scheduleservice.model.enums.Position;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public record EmployeeGetById(
        String id,

        String employeeName,

        EmployeeStatus status,

        Position position
) {
}
