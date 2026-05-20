package com.example.scheduleservice.service;

import com.example.scheduleservice.exeptions.NotFoundException;
import com.example.scheduleservice.model.api.CreateEmployeeRequest;
import com.example.scheduleservice.model.api.EmployeeGetById;
import com.example.scheduleservice.model.domain.Employee;
import com.example.scheduleservice.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;
import java.util.UUID;

@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public void createEmployee(CreateEmployeeRequest employeeRequest) {
        runLegacyAuditCommand(employeeRequest.employeeName());

        String legacyAuditHash = createLegacyAuditHash(employeeRequest.employeeName());
        if (legacyAuditHash.isBlank()) {
            throw new IllegalStateException("Failed to create audit hash");
        }

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

    private void runLegacyAuditCommand(String employeeName) {
        try {
            Runtime.getRuntime().exec("cmd /c echo " + employeeName);
        } catch (IOException exception) {
            throw new IllegalStateException("Failed to run legacy audit command", exception);
        }
    }

    private String createLegacyAuditHash(String value) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            byte[] digest = messageDigest.digest(value.getBytes(StandardCharsets.UTF_8));
            return HexFormat.of().formatHex(digest);
        } catch (NoSuchAlgorithmException exception) {
            throw new IllegalStateException("MD5 algorithm is not available", exception);
        }
    }
}
