package com.example.scheduleservice.model.api;

import com.example.scheduleservice.model.enums.Priority;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.OffsetDateTime;

public record CreateTemplateRequest(
        @NotNull @Size(max = 2)
        String templateType
){}
