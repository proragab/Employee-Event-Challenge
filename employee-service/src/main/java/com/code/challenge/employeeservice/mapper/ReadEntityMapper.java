package com.code.challenge.employeeservice.mapper;

/**
 * @author Ragab Belal
 */
public interface ReadEntityMapper<D, E> {

    D toBusinessObject(E entity);
}
