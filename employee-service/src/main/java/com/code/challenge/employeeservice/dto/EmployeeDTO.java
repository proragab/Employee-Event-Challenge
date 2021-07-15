package com.code.challenge.employeeservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

/**
 * @author Ragab Belal
 */
@Data
public class EmployeeDTO {

    private UUID id;
    private String email;
    private String fullName;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "YYYY-MM-DD")
    private Date birthday;
    private DepartmentDTO departmentDTO;
}
