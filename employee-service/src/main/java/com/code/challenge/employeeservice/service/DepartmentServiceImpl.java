package com.code.challenge.employeeservice.service;

import com.code.challenge.employeeservice.api.request.DepartmentRequest;
import com.code.challenge.employeeservice.dto.GenericResponseDTO;
import com.code.challenge.employeeservice.entity.Department;
import com.code.challenge.employeeservice.exception.EntityNotFoundException;
import com.code.challenge.employeeservice.repository.DepartmentRepository;
import com.savoirtech.logging.slf4j.json.LoggerFactory;
import com.savoirtech.logging.slf4j.json.logger.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Ragab Belal
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final static Logger logger = LoggerFactory.getLogger(DepartmentServiceImpl.class);

    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    /**
     * create new department
     * @param departmentRequest
     * @return
     */
    @Override
    public GenericResponseDTO createDepartment(DepartmentRequest departmentRequest) {
        logger.info().message("Start create new department.").log();
        var department = new Department();
        department.setName(departmentRequest.getName());
        department = departmentRepository.save(department);
        logger.info().message("End create new department.").log();
        return new GenericResponseDTO("Department created successfully with ID {" + department.getId() + "}");
    }

    /**
     * get specific department
     * @param departmentId
     * @return
     * @throws EntityNotFoundException
     */
    @Override
    public Department getDepartmentById(Long departmentId) throws EntityNotFoundException {
        logger.info().message("Start find specific department.").log();
        Optional<Department> optionalDepartment = departmentRepository.findById(departmentId);
        return optionalDepartment.orElseThrow(() -> new EntityNotFoundException("passed department with Id {" + departmentId + "} not exist"));
    }
}
