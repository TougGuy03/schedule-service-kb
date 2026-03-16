package com.example.scheduleservice.model.api;

import java.time.OffsetDateTime;

public record TemplateGetById(
    String id,

    OffsetDateTime creationDate,

    String templateType
){}
