package com.example.scheduleservice.api;

import com.example.scheduleservice.model.api.CreatePeriodRequest;
import com.example.scheduleservice.model.api.PeriodGetById;
import com.example.scheduleservice.model.api.PeriodResponse;
import com.example.scheduleservice.model.api.PeriodSearchRequest;
import com.example.scheduleservice.service.PeriodService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/period")
public class PeriodController {
    private final PeriodService periodService;
    public PeriodController(PeriodService periodService) {
        this.periodService = periodService;
    }

    @PostMapping
    public ResponseEntity<Void> createPeriod(@RequestBody CreatePeriodRequest request, @RequestHeader("x-current-user") String userId) {

        periodService.createPeriod(request, userId);
        return ResponseEntity.status(201).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PeriodGetById> getPeriodById(@PathVariable("id") String id) {
        var response = periodService.getById(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/search")
    public ResponseEntity<Page<PeriodResponse>> searchPeriods(
            @RequestBody PeriodSearchRequest request
    ) {
        var response = periodService.searchPeriods(request);
        return ResponseEntity.ok(response);
    }

}
