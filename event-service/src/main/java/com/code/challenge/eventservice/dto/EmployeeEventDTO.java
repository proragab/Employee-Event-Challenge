package com.code.challenge.eventservice.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author Ragab Belal
 */
@Data
public class EmployeeEventDTO implements Serializable {
    private long id;
    private UUID employeeUUID;
    private String actionEvent;
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime eventDateTime;
}
