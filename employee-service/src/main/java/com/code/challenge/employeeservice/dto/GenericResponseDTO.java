package com.code.challenge.employeeservice.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Ragab Belal
 */
@Data
@NoArgsConstructor
public class GenericResponseDTO {

    private String message;

    public GenericResponseDTO(String message) {
        this.message = message;
    }
}