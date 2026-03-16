package com.example.scheduleservice.model.domain;


import com.example.scheduleservice.model.enums.Priority;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetTime;

@Entity
@Table(name = "slot")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Slot {

    @Id
    @Column(length = 32,  nullable = false)
    private String id;

    @ManyToOne
    @JoinColumn(name = "schedule_template_id", nullable = false)
    private Template scheduleTemplate;

    @Column(name= "begin_time", nullable = false)
    private OffsetTime beginDate;

    @Column(name= "end_time", nullable = false)
    private OffsetTime endDate;

    @Enumerated(EnumType.STRING)
    @Column(name= "priority", nullable = false)
    private Priority priority = Priority.NORMAL;
}
