package com.code.challenge.eventservice.service;

import com.code.challenge.eventservice.dto.EmployeeEventDTO;
import com.code.challenge.eventservice.entity.EmployeeEevnt;
import com.code.challenge.eventservice.exception.InvalidEntityException;
import com.code.challenge.eventservice.mapper.EmployeeEventMapper;
import com.code.challenge.eventservice.repository.EmployeeEventRepository;
import com.code.challenge.eventservice.utils.EmployeeID;
import com.savoirtech.logging.slf4j.json.LoggerFactory;
import com.savoirtech.logging.slf4j.json.logger.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @author Ragab Belal
 */
@Service
public class EmployeeEventServiceImpl implements EmployeeEventService {

    private final static Logger logger = LoggerFactory.getLogger(EmployeeEventServiceImpl.class);
    private final EmployeeEventRepository employeeEventRepository;
    private final EmployeeEventMapper eventMapper;

    @Autowired
    public EmployeeEventServiceImpl(EmployeeEventRepository employeeEventRepository, EmployeeEventMapper eventMapper) {
        this.employeeEventRepository = employeeEventRepository;
        this.eventMapper = eventMapper;
    }

    /**
     * save employee event
     * @param employeeEventDTO
     * @throws Exception
     */
    @Override
    public void saveEmployeeEvent(EmployeeEventDTO employeeEventDTO) throws Exception {
        if (Objects.isNull(employeeEventDTO)) {
            throw new InvalidEntityException("Invalid Event Action");
        }
        logger.info().message("Start create employee event.").log();
        var employeeِEvent = new EmployeeEevnt()
                .setActionEvent(employeeEventDTO.getActionEvent())
                .setEmployeeUUID(employeeEventDTO.getEmployeeUUID())
                .setEventDateTime(employeeEventDTO.getEventDateTime());
        employeeEventRepository.save(employeeِEvent);
    }

    @Override
    public List<EmployeeEventDTO> getEmployeeEvents(EmployeeID employeeID) throws Exception {
        logger.info().message("Start retrieve employee event.").log();
        List<EmployeeEevnt> byEmployeeUUID = employeeEventRepository.findAllByEmployeeUUIDOrderByEventDateTimeAsc(employeeID.getId());
        if (Objects.isNull(byEmployeeUUID) || byEmployeeUUID.isEmpty()) {
            throw new InvalidEntityException("Invalid employee UUID");
        }

        return eventMapper.toBusinessObjects(byEmployeeUUID);
    }
}
