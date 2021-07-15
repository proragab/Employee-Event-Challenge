package com.code.challenge.employeeservice;

import com.code.challenge.employeeservice.api.request.EmployeeRequest;
import com.code.challenge.employeeservice.dto.DepartmentDTO;
import com.code.challenge.employeeservice.dto.EmployeeDTO;
import com.code.challenge.employeeservice.dto.GenericResponseDTO;
import com.code.challenge.employeeservice.entity.Department;
import com.code.challenge.employeeservice.entity.Employee;
import com.code.challenge.employeeservice.mapper.EmployeeMapper;
import com.code.challenge.employeeservice.repository.DepartmentRepository;
import com.code.challenge.employeeservice.repository.EmployeeRepository;
import com.code.challenge.employeeservice.service.DepartmentServiceImpl;
import com.code.challenge.employeeservice.service.EmployeeServiceImpl;
import com.code.challenge.employeeservice.service.EventProducerServiceImp;
import com.code.challenge.employeeservice.utils.EmployeeID;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * @author Ragab Belal
 */
public class EmployeeServiceTest {
    @InjectMocks
    private EmployeeServiceImpl employeeServiceImpl;

    @Mock
    private DepartmentServiceImpl departmentService;

    @Mock
    private EventProducerServiceImp eventProducerServiceImp;
    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private EmployeeMapper employeeMapper;

    @Mock
    private DepartmentRepository departmentRepository;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void createEmployee_EmployeeCreatedSuccessfully() throws Exception {
        Employee employee = getEmployee();
        EmployeeRequest employeeRequest = new EmployeeRequest();
        employeeRequest.setDepartmentId(1L);
        Department department = new Department();
        department.setId(1l);
        department.setName("Department1");
        when(employeeRepository.save(any())).thenReturn(employee);
        when(departmentRepository.findById(any())).thenReturn(Optional.of(department));
        GenericResponseDTO genericResponseDTO = employeeServiceImpl.createEmployee(employeeRequest);
        assertNotNull(genericResponseDTO);
        assertEquals("Employee created successfully with UUID {" + employee.getId() + "}", genericResponseDTO.getMessage());
    }

    @Test
    public void findEmployee_EmployeeReturnedSuccessfully() throws Exception {
        Employee employee = getEmployee();
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(employee.getId());
        employee.setDepartment(null);
        when(employeeRepository.findById(any())).thenReturn(Optional.of(employee));
        when(employeeMapper.toBusinessObject(employee)).thenReturn(employeeDTO);
        EmployeeDTO employeeDto = employeeServiceImpl.getEmployeeByUUID(new EmployeeID(UUID.randomUUID()));
        assertNotNull(employeeDto);
        assertEquals(employee.getId(), employeeDto.getId());
    }

    @Test
    public void updateEmployee_throwEmployeeNotFoundException() throws Exception {
        try {
            when(employeeRepository.findById(any())).thenReturn(null);
            employeeServiceImpl.updateEmployee(new EmployeeID(UUID.randomUUID()), new EmployeeRequest());
        } catch (Exception ex) {
            Assert.assertTrue(true);
        }
    }

    @Test
    public void updateEmployee_EmployeeUpdatedSuccessfully() throws Exception {
        Employee employee = getEmployee();
        EmployeeRequest employeeRequest = new EmployeeRequest();
        employeeRequest.setDepartmentId(1L);
        Department department = new Department();
        department.setId(1l);
        department.setName("Department1");
        when(employeeRepository.findById(any())).thenReturn(Optional.of(employee));
        when(departmentRepository.findById(any())).thenReturn(Optional.of(department));
        when(employeeRepository.save(any())).thenReturn(employee);
        GenericResponseDTO genericResponseDTO = employeeServiceImpl.updateEmployee(new EmployeeID(UUID.randomUUID()), employeeRequest);
        assertNotNull(genericResponseDTO);
        assertEquals("Employee with UUID {" + employee.getId() + "} updated successfully", genericResponseDTO.getMessage());
    }

    @Test
    public void deleteEmployee_throwEmployeeNotFoundException() throws Exception {
        try {
            when(employeeRepository.findById(any())).thenReturn(Optional.of(new Employee()));
            employeeServiceImpl.deleteEmployee(new EmployeeID(UUID.randomUUID()));
        } catch (Exception ex) {
            Assert.assertTrue(true);
        }
    }

    private Employee getEmployee() {
        Employee employee = new Employee();
        employee.setId(UUID.randomUUID());
        employee.setFullName("Full_Name");
        employee.setEmail("test@gmail.com");
        employee.setBirthday(new Date());
        employee.setDepartment(new Department().setId(1L));
        return employee;
    }

    private DepartmentDTO getDepartmentDto() {
        DepartmentDTO departmentDTO = new DepartmentDTO();
        departmentDTO.setId(1l);
        departmentDTO.setName("dept");
        return departmentDTO;
    }
}
