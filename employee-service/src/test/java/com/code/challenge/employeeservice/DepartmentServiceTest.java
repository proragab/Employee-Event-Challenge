package com.code.challenge.employeeservice;

import com.code.challenge.employeeservice.api.request.DepartmentRequest;
import com.code.challenge.employeeservice.dto.GenericResponseDTO;
import com.code.challenge.employeeservice.entity.Department;
import com.code.challenge.employeeservice.repository.DepartmentRepository;
import com.code.challenge.employeeservice.service.DepartmentServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * @author Ragab Belal
 */
public class DepartmentServiceTest {

    @InjectMocks
    private DepartmentServiceImpl departmentServiceImpl;

    @Mock
    private DepartmentRepository departmentRepository;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void createDepartment_DepartmentCreatedSuccessfully() throws Exception {
        Department department = new Department();
        department.setId(1l);
        department.setName("Department1");
        when(departmentRepository.save(any())).thenReturn(department);
        GenericResponseDTO genericResponseDTO = departmentServiceImpl.createDepartment(new DepartmentRequest());
        assertNotNull(genericResponseDTO);
        assertEquals("Department created successfully with ID {" + department.getId() + "}", genericResponseDTO.getMessage());
    }

}
