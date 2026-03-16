package com.example.scheduleservice.service.implemetation;

import com.example.scheduleservice.model.api.CreateEmployeeRequest;
import com.example.scheduleservice.model.api.EmployeeGetById;

public interface IEmployeeService {
    void createEmployee(CreateEmployeeRequest schedule);
    EmployeeGetById getById(String id);
}
