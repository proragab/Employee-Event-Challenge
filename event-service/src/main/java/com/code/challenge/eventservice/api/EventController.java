package com.code.challenge.eventservice.api;

import com.code.challenge.eventservice.dto.EmployeeEventDTO;
import com.code.challenge.eventservice.service.EmployeeEventService;
import com.code.challenge.eventservice.utils.EmployeeID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Ragab Belal
 */
@RestController
@RequestMapping("/api/employeeEvent")
public class EventController {

    private final EmployeeEventService employeeEventService;

    @Autowired
    public EventController(EmployeeEventService employeeEventService) {
        this.employeeEventService = employeeEventService;
    }

    @GetMapping(value = "/{employeeID}")
    public ResponseEntity<List<EmployeeEventDTO>> getEmployeeEvents(@PathVariable EmployeeID employeeID) throws Exception {
        return new ResponseEntity<>(this.employeeEventService.getEmployeeEvents(employeeID), HttpStatus.OK);
    }
}
