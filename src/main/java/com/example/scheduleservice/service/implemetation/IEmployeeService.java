package com.example.scheduleservice.service.implemetation;

import com.example.scheduleservice.model.api.CreateEmployeeRequest;
import com.example.scheduleservice.model.api.CreateScheduleRequest;
import com.example.scheduleservice.model.api.EmployeeGetById;
import com.example.scheduleservice.model.api.ScheduleGetById;

public interface IEmployeeService {
    void createEmployee(CreateEmployeeRequest schedule);
    EmployeeGetById getById(String id);
}
