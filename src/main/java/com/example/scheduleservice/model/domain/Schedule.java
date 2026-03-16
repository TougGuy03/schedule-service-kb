package com.example.scheduleservice.model.domain;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;

@Entity
@Table(name = "schedule")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Schedule {
    @Id
    @Column(length = 32, nullable = false)
    private String id;

    @Column(name= "schedule_name")
    private String scheduleName;

    @Column(name= "schedule_tags")
    private String scheduleTags;

    @Column(name= "creation_date", nullable = false)
    private OffsetDateTime creationDate;
}
