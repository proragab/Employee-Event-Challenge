package com.code.challenge.employeeservice.utils;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @author Ragab Belal
 */
@Component
public class StringToEmployeeIdConverter implements Converter<String, EmployeeID> {

    @Override
    public EmployeeID convert(@NonNull String uuid) {
        return new EmployeeID(UUID.fromString(uuid));
    }
}
