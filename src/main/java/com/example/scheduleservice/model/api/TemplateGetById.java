package com.example.scheduleservice.model.api;

import com.example.scheduleservice.model.enums.Priority;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.time.OffsetDateTime;

public record TemplateGetById(
    String id,

    OffsetDateTime creationDate,

    String templateType
){}
