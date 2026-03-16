package com.example.scheduleservice.api;

import com.example.scheduleservice.model.api.CreateScheduleRequest;
import com.example.scheduleservice.model.api.ScheduleFull;
import com.example.scheduleservice.model.api.ScheduleGetById;
import com.example.scheduleservice.model.domain.Schedule;
import com.example.scheduleservice.service.ScheduleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @PostMapping
    public ResponseEntity<Void> createSchedule(@RequestBody CreateScheduleRequest request) {
        scheduleService.createSchedule(request);
        return ResponseEntity.status(201).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleGetById> getScheduleById(@PathVariable("id") String id) {
        var response = scheduleService.getById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/full")
    public ResponseEntity<ScheduleFull> getScheduleByFullName(
            @RequestParam(required = false) String id,
            @RequestParam(required = false) String scheduleName
    ) {

        var response = scheduleService.getFull(id, scheduleName);
        return ResponseEntity.ok(response);

    }


}
