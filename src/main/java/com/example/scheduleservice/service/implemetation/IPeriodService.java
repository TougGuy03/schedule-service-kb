package com.example.scheduleservice.service.implemetation;

import com.example.scheduleservice.model.api.CreatePeriodRequest;
import com.example.scheduleservice.model.api.PeriodGetById;
import com.example.scheduleservice.model.api.PeriodResponse;
import com.example.scheduleservice.model.api.PeriodSearchRequest;
import org.springframework.data.domain.Page;


public interface IPeriodService {
    void createPeriod(CreatePeriodRequest period, String userId);
    PeriodGetById getById(String id);
    Page<PeriodResponse> searchPeriods(PeriodSearchRequest periodSearchRequest);
}
