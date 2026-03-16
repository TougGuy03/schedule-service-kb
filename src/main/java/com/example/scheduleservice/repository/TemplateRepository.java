package com.example.scheduleservice.repository;

import com.example.scheduleservice.model.domain.Template;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TemplateRepository extends JpaRepository<Template, String> {
}
