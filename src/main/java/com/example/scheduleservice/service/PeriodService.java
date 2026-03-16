package com.example.scheduleservice.service;

import com.example.scheduleservice.exeptions.BadRequestException;
import com.example.scheduleservice.exeptions.NotFoundException;
import com.example.scheduleservice.model.api.*;
import com.example.scheduleservice.model.domain.Employee;
import com.example.scheduleservice.model.domain.Period;
import com.example.scheduleservice.model.domain.Schedule;
import com.example.scheduleservice.model.domain.Slot;
import com.example.scheduleservice.repository.EmployeeRepository;
import com.example.scheduleservice.repository.PeriodRepository;
import com.example.scheduleservice.repository.ScheduleRepository;
import com.example.scheduleservice.repository.SlotRepository;
import com.example.scheduleservice.service.implemetation.IPeriodService;
import com.example.scheduleservice.service.implemetation.IScheduleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.ResourceTransactionManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class PeriodService implements IPeriodService {

    private final PeriodRepository periodRepository;
    private final ScheduleRepository scheduleRepository;
    private final EmployeeRepository employeeRepository;
    private final SlotRepository slotRepository;


    public PeriodService(PeriodRepository periodRepository, ScheduleRepository scheduleRepository, EmployeeRepository employeeRepository, SlotRepository slotRepository) {
        this.periodRepository = periodRepository;
        this.scheduleRepository = scheduleRepository;
        this.employeeRepository = employeeRepository;
        this.slotRepository = slotRepository;
    }

    @Override
    public void createPeriod(CreatePeriodRequest periodRequest, String administratorId) {
        Slot slot = slotRepository.findById(periodRequest.slotId())
                .orElseThrow(() -> new NotFoundException("Slot not found"));

        Schedule schedule = scheduleRepository.findById(periodRequest.scheduleId())
                .orElseThrow(() -> new NotFoundException("Schedule not found"));

        Employee administrator = employeeRepository.findById(administratorId)
                .orElseThrow(() -> new NotFoundException("Administrator not found"));

        String executorId = periodRequest.executorId();

        Employee executor = null;
        if (executorId != null && !executorId.equals(administratorId)) {
            executor = employeeRepository.findById(executorId)
                    .orElseThrow(() -> new NotFoundException("Executor not found"));
        }



        boolean hasOverlap = periodRepository.existsOverlappingPeriod(
                executor == null ? administratorId : executor.getId(),
                slot.getBeginDate(),
                slot.getEndDate()
        );

        if (hasOverlap) {
            throw new BadRequestException("Overlapping periods are not allowed");
        }

        Period entity = new Period(
                UUID.randomUUID().toString().replace("-", ""),
                slot,
                schedule,
                periodRequest.slotType(),
                administrator,
                executor
        );
        periodRepository.save(entity);
    }

    @Override
    public PeriodGetById getById(String id) {
        Period entity = periodRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Period not found"));

        PeriodGetById periodResponse = new PeriodGetById(
                entity.getId(),
                entity.getSlot().getId(),
                entity.getSchedule().getId(),
                entity.getSlotType(),
                entity.getAdministrator().getId(),
                entity.getExecutor() != null ? entity.getExecutor().getId() : null
        );
        return periodResponse;
    }

    @Override
    public Page<PeriodResponse> searchPeriods(PeriodSearchRequest periodSearchRequest) {
        Specification<Period> specification =
                buildSpecification(periodSearchRequest.filter());

        Pageable pageable = createPageable(periodSearchRequest);

        Page<Period> periods = periodRepository.findAll(specification, pageable);

        return periods.map(period -> new PeriodResponse(
                period.getId(),
                period.getSlot().getId(),
                period.getSchedule().getId(),
                period.getSlotType(),
                period.getAdministrator().getId(),
                period.getExecutor() != null ? period.getExecutor().getId() : null
        ));
    }

    private Specification<Period> buildSpecification(PeriodFilter filter) {
        var specificationPredicates = new ArrayList<Specification<Period>>();
            if(filter == null){
                return Specification.allOf();
            }
            if(filter.id() != null){
                specificationPredicates.add((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("id"), filter.id()));
            }
            if(filter.slotId() != null){
                specificationPredicates.add((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("slot").get("id"), filter.slotId()));
            }
            if(filter.scheduleId() != null){
                specificationPredicates.add((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("schedule").get("id"), filter.scheduleId()));
            }
            if(filter.slotType() != null){
                specificationPredicates.add(((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("slotType"), filter.slotType())));
            }
            if(filter.administratorId() != null){
                specificationPredicates.add(((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("administrator").get("id"), filter.administratorId())));
            }
            if(filter.executorId() != null){
                specificationPredicates.add(((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("executor").get("id"), filter.executorId())));
            }
            return Specification.allOf(specificationPredicates);
    }

    private Pageable createPageable(PeriodSearchRequest request) {

        int page = request.page() == null ? 0 : request.page();
        int size = 10;

        if (request.sort() == null || request.sort().field() == null) {
            return PageRequest.of(page, size);
        }

        Sort.Direction direction;

        if ("ASC".equalsIgnoreCase(request.sort().direction())) {
            direction = Sort.Direction.ASC;
        }
        else {
            direction = Sort.Direction.DESC;
        }

        Sort sort = Sort.by(direction, request.sort().field());

        return PageRequest.of(page, size, sort);
    }
}
