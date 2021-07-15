package com.code.challenge.eventservice.mapper;

import java.util.List;

/**
 * @author Ragab Belal
 */
public interface ReadEntityMapper<D, E>  {


    List<D> toBusinessObjects(List<E> entityList);
}
