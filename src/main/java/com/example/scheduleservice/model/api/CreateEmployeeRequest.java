package com.example.scheduleservice.model.api;

import com.example.scheduleservice.model.enums.EmployeeStatus;
import com.example.scheduleservice.model.enums.Position;
import jakarta.validation.constraints.NotNull;

public record CreateEmployeeRequest(
        @NotNull
        String employeeName,

        @NotNull
        EmployeeStatus status,

        @NotNull
        Position position
) {
}
