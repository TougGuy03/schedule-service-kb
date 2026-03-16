package com.example.scheduleservice.service;

import com.example.scheduleservice.exeptions.NotFoundException;
import com.example.scheduleservice.model.api.CreateSlotRequest;
import com.example.scheduleservice.model.api.SlotGetById;
import com.example.scheduleservice.model.domain.Slot;
import com.example.scheduleservice.model.domain.Template;
import com.example.scheduleservice.model.enums.Priority;
import com.example.scheduleservice.repository.SlotRepository;
import com.example.scheduleservice.repository.TemplateRepository;
import com.example.scheduleservice.service.implemetation.ISlotService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SlotService implements ISlotService {

    public final SlotRepository slotRepository;
    public final TemplateRepository templateRepository;

    public SlotService(SlotRepository slotRepository, TemplateRepository templateRepository) {
        this.slotRepository = slotRepository;
        this.templateRepository = templateRepository;
    }

    @Override
    public void createSlot(CreateSlotRequest slotRequest) {

        Template template = templateRepository.findById(slotRequest.templateId())
                .orElseThrow(() -> new NotFoundException("Template not found"));

        var priority = slotRequest.priority() == null
                ? Priority.NORMAL
                : slotRequest.priority();

        Slot entity = new Slot(
                UUID.randomUUID().toString().replace("-", ""),
                template,
                slotRequest.beginDate(),
                slotRequest.endDate(),
                priority
        );
        slotRepository.save(entity);
    }

    @Override
    public SlotGetById getById(String id) {
        Slot entity = slotRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Slot not found"));

        SlotGetById slotGetById = new SlotGetById(
                entity.getId(),
                entity.getScheduleTemplate().getId(),
                entity.getBeginDate(),
                entity.getEndDate(),
                entity.getPriority()
        );

        return slotGetById;
    }


}
