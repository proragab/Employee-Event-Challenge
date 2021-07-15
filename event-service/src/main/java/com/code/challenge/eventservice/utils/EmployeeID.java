package com.code.challenge.eventservice.utils;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.StringJoiner;
import java.util.UUID;

/**
 * @author Ragab Belal
 */
public class EmployeeID {

    private final UUID id;

    public EmployeeID(UUID id) {
        this.id = id;
    }

    @JsonValue
    public UUID getId() {
        return id;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", EmployeeID.class.getSimpleName() + "[", "]")
                .add(String.format("id=%s", id))
                .toString();
    }
}
