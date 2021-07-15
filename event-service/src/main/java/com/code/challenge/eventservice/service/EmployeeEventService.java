package com.code.challenge.eventservice.service;

import com.code.challenge.eventservice.dto.EmployeeEventDTO;
import com.code.challenge.eventservice.utils.EmployeeID;

import java.util.List;

/**
 * @author Ragab Belal
 */
public interface EmployeeEventService {

    void saveEmployeeEvent(EmployeeEventDTO employeeEventDTO) throws Exception;

    List<EmployeeEventDTO> getEmployeeEvents(EmployeeID employeeID) throws Exception;

}
