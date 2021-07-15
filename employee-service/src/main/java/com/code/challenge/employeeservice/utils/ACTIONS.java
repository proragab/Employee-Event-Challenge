package com.code.challenge.employeeservice.utils;

/**
 * @author Ragab Belal
 */
public enum ACTIONS {

    CREATED("CREATED"), UPDATED("UPDATED"), DELETED("DELETED");

    public final String value;

    ACTIONS(String value) {
        this.value = value;
    }
}
