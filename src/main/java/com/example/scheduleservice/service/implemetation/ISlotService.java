package com.example.scheduleservice.service.implemetation;

import com.example.scheduleservice.model.api.CreateSlotRequest;
import com.example.scheduleservice.model.api.SlotGetById;

public interface ISlotService {
    void createSlot(CreateSlotRequest slot);
    SlotGetById getById(String id);
}
