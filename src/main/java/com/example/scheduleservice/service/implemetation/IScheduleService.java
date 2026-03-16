package com.example.scheduleservice.service.implemetation;

import com.example.scheduleservice.model.api.CreateScheduleRequest;
import com.example.scheduleservice.model.api.ScheduleFull;
import com.example.scheduleservice.model.api.ScheduleGetById;

public interface IScheduleService {
    void createSchedule(CreateScheduleRequest schedule);
    ScheduleGetById getById(String id);
    ScheduleFull getFull(String id, String scheduleName);
}
