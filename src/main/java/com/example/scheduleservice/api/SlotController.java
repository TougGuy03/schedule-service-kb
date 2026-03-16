package com.example.scheduleservice.api;

import com.example.scheduleservice.model.api.CreateSlotRequest;
import com.example.scheduleservice.model.api.SlotGetById;
import com.example.scheduleservice.service.SlotService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/slot")
public class SlotController {

    private final SlotService slotService;
    public SlotController(SlotService slotService) {
        this.slotService = slotService;
    }

    @PostMapping
    public ResponseEntity<Void> createSlot(@RequestBody CreateSlotRequest request) {
        slotService.createSlot(request);
        return ResponseEntity.status(201).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SlotGetById> getSlotById(@PathVariable("id") String id) {
        var response = slotService.getById(id);
        return ResponseEntity.ok(response);
    }
}
