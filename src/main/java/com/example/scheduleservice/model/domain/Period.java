package com.example.scheduleservice.model.domain;

import com.example.scheduleservice.model.enums.SlotType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "period")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Period {
        @Id
        @Column(length = 32, nullable = false)
        private String id;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "slot_id", nullable = false)
        private Slot slot;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "schedule_id",nullable = false)
        private Schedule schedule;

        @Enumerated(EnumType.STRING)
        @Column(name = "slot_type", nullable = false)
        private SlotType slotType = SlotType.UNDEFINED;


        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "administrator_id", nullable = false)
        private Employee administrator;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "executor_id")
        private Employee executor;
}
