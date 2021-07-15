package com.code.challenge.employeeservice.service;

import com.code.challenge.employeeservice.dto.EmployeeEventDTO;

/**
 * @author Ragab Belal
 */
public interface EventProducerService {

    void produceEventAction(EmployeeEventDTO employeeEventDTO);
}
