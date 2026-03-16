package com.example.scheduleservice.repository;

import com.example.scheduleservice.model.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, String>{
}
