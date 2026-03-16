package com.example.scheduleservice.api;


import com.example.scheduleservice.model.api.CreateScheduleRequest;
import com.example.scheduleservice.model.api.CreateTemplateRequest;
import com.example.scheduleservice.model.api.ScheduleGetById;
import com.example.scheduleservice.model.api.TemplateGetById;
import com.example.scheduleservice.service.ScheduleService;
import com.example.scheduleservice.service.TemplateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/template")
public class TemplateController {
    private final TemplateService templateService;

    public TemplateController(TemplateService templateService) {
        this.templateService = templateService;
    }

    @PostMapping
    public ResponseEntity<Void> createTemplate(@RequestBody CreateTemplateRequest request) {
        templateService.createTemplate(request);
        return ResponseEntity.status(201).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TemplateGetById> getTemplateById(@PathVariable("id") String id) {
        var response = templateService.getById(id);
        return ResponseEntity.ok(response);
    }
}
