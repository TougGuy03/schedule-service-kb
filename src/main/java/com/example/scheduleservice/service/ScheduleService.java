package com.example.scheduleservice.service;

import com.example.scheduleservice.exeptions.BadRequestException;
import com.example.scheduleservice.exeptions.NotFoundException;
import com.example.scheduleservice.model.api.CreateScheduleRequest;
import com.example.scheduleservice.model.api.PeriodGetById;
import com.example.scheduleservice.model.api.ScheduleFull;
import com.example.scheduleservice.model.api.ScheduleGetById;
import com.example.scheduleservice.model.domain.Period;
import com.example.scheduleservice.model.domain.Schedule;
import com.example.scheduleservice.model.domain.Template;
import com.example.scheduleservice.repository.PeriodRepository;
import com.example.scheduleservice.repository.ScheduleRepository;
import com.example.scheduleservice.service.implemetation.IScheduleService;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class ScheduleService implements IScheduleService {

    private final PeriodRepository periodRepository;
    private ScheduleRepository scheduleRepository;

    public ScheduleService(ScheduleRepository scheduleRepository, PeriodRepository periodRepository) {
        this.scheduleRepository = scheduleRepository;
        this.periodRepository = periodRepository;
    }

    @Override
    public void createSchedule(CreateScheduleRequest schedule) {
        String tags = schedule.scheduleTags() == null
                ? null
                : String.join(",", schedule.scheduleTags());

        Schedule entity = new Schedule(
                UUID.randomUUID().toString().replace("-", ""),
                schedule.scheduleName(),
                tags,
                OffsetDateTime.now()
        );
        scheduleRepository.save(entity);
    }

    @Override
    public ScheduleGetById getById(String id) {
        Schedule entity = scheduleRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Schedule not found"));

        ScheduleGetById scheduleResponse = new ScheduleGetById(
                entity.getId(),
                entity.getScheduleName(),
                Arrays.asList(entity.getScheduleTags().split(",")),
                entity.getCreationDate()
        );
        return scheduleResponse;
    }

    @Override
    public ScheduleFull getFull(String id, String scheduleName) {
        if(id == null && scheduleName == null) {
            throw new BadRequestException("Schedule not found");
        }

        Schedule schedule;

        if(id != null) {
            schedule = scheduleRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException("Schedule not found"));
        }
        else {
            schedule = scheduleRepository.findByScheduleName(scheduleName)
                    .orElseThrow(() -> new NotFoundException("Schedule not found"));
        }

        List<Period> periods = periodRepository.findByScheduleIdOrderBySlotBeginDate(schedule.getId());

        ScheduleGetById scheduleFull = new ScheduleGetById(
                schedule.getId(),
                schedule.getScheduleName(),
                Arrays.asList(schedule.getScheduleTags().split(",")),
                schedule.getCreationDate()
        );

        List<PeriodGetById> periodsResponse = periods.stream().map(
                period -> new PeriodGetById(
                        period.getId(),
                        period.getSlot().getId(),
                        period.getSchedule().getId(),
                        period.getSlotType(),
                        period.getAdministrator().getId(),
                        period.getExecutor().getId()
                )
        ).toList();

        return new ScheduleFull(scheduleFull, periodsResponse);
    }


}
