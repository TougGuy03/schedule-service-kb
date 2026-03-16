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
@Table(name = "template")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Template {

    @Id
    @Column(length = 32, nullable = false)
    private String id;

    @Column(name ="creation_date", nullable = false)
    private OffsetDateTime creationDate;

    @Column(name ="template_type", length = 2, nullable = false)
    private String templateType;

}
