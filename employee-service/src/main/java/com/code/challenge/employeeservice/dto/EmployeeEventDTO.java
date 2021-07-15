package com.code.challenge.employeeservice.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author Ragab Belal
 */
@Data
@AllArgsConstructor
public class EmployeeEventDTO implements Serializable {

    private UUID employeeUUID;
    private String actionEvent;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime eventDateTime;
}
