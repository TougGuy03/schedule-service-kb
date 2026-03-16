package com.example.scheduleservice.model.api;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateTemplateRequest(
        @NotNull @Size(max = 2)
        String templateType
){}
