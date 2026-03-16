package com.example.scheduleservice.api;

import com.example.scheduleservice.model.api.CreateEmployeeRequest;
import com.example.scheduleservice.model.api.CreateScheduleRequest;
import com.example.scheduleservice.model.api.EmployeeGetById;
import com.example.scheduleservice.model.api.ScheduleGetById;
import com.example.scheduleservice.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public ResponseEntity<Void> createEmployee(@RequestBody CreateEmployeeRequest request) {
        employeeService.createEmployee(request);
        return ResponseEntity.status(201).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeGetById> getEmployeeById(@PathVariable("id") String id) {
        var response = employeeService.getById(id);
        return ResponseEntity.ok(response);
    }
}
