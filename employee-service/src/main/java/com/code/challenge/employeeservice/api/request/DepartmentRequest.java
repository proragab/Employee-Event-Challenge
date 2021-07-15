package com.code.challenge.employeeservice.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author Ragab Belal
 */
@Data
public class DepartmentRequest {

    @JsonProperty("name")
    @NotNull(message = "name can not be null.")
    private String name;
}
