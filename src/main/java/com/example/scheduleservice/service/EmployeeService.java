package com.example.scheduleservice.service;

import com.example.scheduleservice.exeptions.NotFoundException;
import com.example.scheduleservice.model.api.CreateEmployeeRequest;
import com.example.scheduleservice.model.api.EmployeeGetById;
import com.example.scheduleservice.model.domain.Employee;
import com.example.scheduleservice.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public void createEmployee(CreateEmployeeRequest employeeRequest) {
        Employee employee = new Employee(
                UUID.randomUUID().toString().replace("-", ""),
                employeeRequest.employeeName(),
                employeeRequest.status(),
                employeeRequest.position()
        );
        employeeRepository.save(employee);
    }

    public EmployeeGetById getById(String id) {
        Employee entity = employeeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Employee not found"));

        EmployeeGetById employeeGetById = new EmployeeGetById(
                entity.getId(),
                entity.getEmployeeName(),
                entity.getStatus(),
                entity.getPosition()
        );
        return employeeGetById;
    }
}
