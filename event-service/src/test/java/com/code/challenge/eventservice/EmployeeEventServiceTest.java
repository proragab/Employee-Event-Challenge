package com.code.challenge.eventservice;

import com.code.challenge.eventservice.dto.EmployeeEventDTO;
import com.code.challenge.eventservice.entity.EmployeeEevnt;
import com.code.challenge.eventservice.mapper.EmployeeEventMapper;
import com.code.challenge.eventservice.repository.EmployeeEventRepository;
import com.code.challenge.eventservice.service.EmployeeEventServiceImpl;
import com.code.challenge.eventservice.utils.EmployeeID;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class EmployeeEventServiceTest {

    @InjectMocks
    private EmployeeEventServiceImpl employeeEventServiceImpl;

    @Mock
    private EmployeeEventRepository employeeEventRepository;

    @Mock
    private EmployeeEventMapper employeeEventMapper;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void createEmployeeEvent_throwInvalidEntityException() throws Exception {
        try {
            employeeEventServiceImpl.saveEmployeeEvent(null);
        } catch (Exception ex) {
            Assert.assertEquals("Invalid Event Action",ex.getMessage());
        }
    }

    @Test
    public void getEmployeeEvents_throwInvalidEmployeeUUIDException() throws Exception {
        try {
            when(employeeEventRepository.findAllByEmployeeUUIDOrderByEventDateTimeAsc(any())).thenReturn(new ArrayList<>());
            employeeEventServiceImpl.getEmployeeEvents(new EmployeeID(UUID.randomUUID()));
        } catch (Exception ex) {
            Assert.assertEquals("Invalid employee UUID",ex.getMessage());
        }
    }

    @Test
    public void getEmployeeEvents_employeeEventsRetrievedSuccessfully() throws Exception {
        EmployeeEevnt employeeEevnt = new EmployeeEevnt();
        employeeEevnt.setId(1l);
        employeeEevnt.setEmployeeUUID(UUID.randomUUID());
        employeeEevnt.setEventDateTime(LocalDateTime.now());
        employeeEevnt.setActionEvent("created");
        List<EmployeeEevnt> employeeEevnts = new ArrayList<>();
        employeeEevnts.add(employeeEevnt);

        EmployeeEventDTO employeeEventDTO = new EmployeeEventDTO();
        employeeEventDTO.setId(1l);
        employeeEventDTO.setEmployeeUUID(UUID.randomUUID());
        employeeEventDTO.setEventDateTime(LocalDateTime.now());
        employeeEventDTO.setActionEvent("created");
        List<EmployeeEventDTO>  employeeEventDTOS = new ArrayList<>();
        employeeEventDTOS.add(employeeEventDTO);

        when(employeeEventRepository.findAllByEmployeeUUIDOrderByEventDateTimeAsc(any())).thenReturn(employeeEevnts);
        when(employeeEventMapper.toBusinessObjects(employeeEevnts)).thenReturn(employeeEventDTOS);

        List<EmployeeEventDTO> employeeEvents = employeeEventServiceImpl.getEmployeeEvents(new EmployeeID(UUID.randomUUID()));
        assertNotNull(employeeEvents);
        assertEquals(1,employeeEvents.size());
    }
}
