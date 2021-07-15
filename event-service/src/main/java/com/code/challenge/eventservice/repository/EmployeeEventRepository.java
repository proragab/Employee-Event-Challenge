package com.code.challenge.eventservice.repository;

import com.code.challenge.eventservice.entity.EmployeeEevnt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

/**
 * @author Ragab Belal
 */
public interface EmployeeEventRepository extends JpaRepository<EmployeeEevnt, Long> {

    List<EmployeeEevnt> findAllByEmployeeUUIDOrderByEventDateTimeAsc(UUID employeeUuid);
}
