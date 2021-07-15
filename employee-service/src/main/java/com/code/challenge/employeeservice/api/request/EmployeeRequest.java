package com.code.challenge.employeeservice.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author Ragab Belal
 */
@Data
public class EmployeeRequest {

    @JsonProperty("email")
    @NotNull(message = "email can not be null.")
    private String email;

    @JsonProperty("fullName")
    @NotNull(message = "fullName can not be null.")
    private String fullName;

    @JsonProperty("birthday")
    @NotNull(message = "birthday can not be null.")
    private Date birthday;

    @JsonProperty("departmentId")
    @NotNull(message = "departmentId can not be null.")
    private Long departmentId;

}
