package com.example.scheduleservice.model.domain;


import com.example.scheduleservice.model.enums.EmployeeStatus;
import com.example.scheduleservice.model.enums.Position;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "employee")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    @Id
    @Column(length = 32, nullable = false)
    private String id;

    @Column(name = "employee_name", nullable = false, length = 255)
    private String employeeName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EmployeeStatus status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Position position;

}
