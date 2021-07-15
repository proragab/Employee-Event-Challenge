package com.code.challenge.eventservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Ragab Belal
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidEntityException extends Exception {

    public InvalidEntityException(String message) {
        super(message);
    }
}
