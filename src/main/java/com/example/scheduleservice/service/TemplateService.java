package com.example.scheduleservice.service;

import com.example.scheduleservice.exeptions.NotFoundException;
import com.example.scheduleservice.model.api.CreateTemplateRequest;
import com.example.scheduleservice.model.api.TemplateGetById;
import com.example.scheduleservice.model.domain.Template;
import com.example.scheduleservice.repository.TemplateRepository;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.UUID;

@Service
public class TemplateService {

    private final TemplateRepository templateRepository;

    public TemplateService(TemplateRepository templateRepository) {
        this.templateRepository = templateRepository;
    }


    public void createTemplate(CreateTemplateRequest templateRequest) {
        Template entity = new Template(
                UUID.randomUUID().toString().replace("-", ""),
                OffsetDateTime.now(),
                templateRequest.templateType()
        );
        templateRepository.save(entity);
    }

    public TemplateGetById getById(String id) {
        Template entity = templateRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Template not found"));

        TemplateGetById templateGetById = new TemplateGetById(
                entity.getId(),
                entity.getCreationDate(),
                entity.getTemplateType()
        );

        return templateGetById;
    }
}
