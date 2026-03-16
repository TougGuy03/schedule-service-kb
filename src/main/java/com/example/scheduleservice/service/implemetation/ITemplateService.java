package com.example.scheduleservice.service.implemetation;

import com.example.scheduleservice.model.api.CreateScheduleRequest;
import com.example.scheduleservice.model.api.CreateTemplateRequest;
import com.example.scheduleservice.model.api.ScheduleGetById;
import com.example.scheduleservice.model.api.TemplateGetById;

public interface ITemplateService {
    void createTemplate(CreateTemplateRequest template);
    TemplateGetById getById(String id);
}
