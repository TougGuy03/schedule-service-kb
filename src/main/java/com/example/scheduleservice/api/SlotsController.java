package com.example.scheduleservice.api;

import com.example.scheduleservice.service.PeriodService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.List;

@RestController
public class SlotsController {

    private final PeriodService periodService;

    public SlotsController(PeriodService periodService) {
        this.periodService = periodService;
    }

    @GetMapping("/slots")
    public ResponseEntity<List<String>> getSlots(
            @RequestParam("executorId") String executorId,
            @RequestParam("from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Instant from,
            @RequestParam("to") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Instant to
    ) {
        return ResponseEntity.ok(periodService.getSlots(executorId, from, to));
    }
}
