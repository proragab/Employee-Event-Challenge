package com.code.challenge.employeeservice.service;

import com.code.challenge.employeeservice.api.request.EmployeeRequest;
import com.code.challenge.employeeservice.dto.EmployeeDTO;
import com.code.challenge.employeeservice.dto.EmployeeEventDTO;
import com.code.challenge.employeeservice.dto.GenericResponseDTO;
import com.code.challenge.employeeservice.entity.Department;
import com.code.challenge.employeeservice.entity.Employee;
import com.code.challenge.employeeservice.exception.EntityNotFoundException;
import com.code.challenge.employeeservice.mapper.DepartmentMapper;
import com.code.challenge.employeeservice.mapper.EmployeeMapper;
import com.code.challenge.employeeservice.repository.EmployeeRepository;
import com.code.challenge.employeeservice.utils.ACTIONS;
import com.code.challenge.employeeservice.utils.EmployeeID;
import com.savoirtech.logging.slf4j.json.LoggerFactory;
import com.savoirtech.logging.slf4j.json.logger.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

/**
 * @author Ragab Belal
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final static Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    private final EmployeeRepository employeeRepository;

    private final EmployeeMapper employeeMapper;
    private final DepartmentMapper departmentMapper;

    private final DepartmentService departmentService;
    private final EventProducerService eventProducerService;


    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper,
                               DepartmentMapper departmentMapper, DepartmentService departmentService,
                               EventProducerService eventProducerService, EntityManager entityManager) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
        this.departmentMapper = departmentMapper;
        this.departmentService = departmentService;
        this.eventProducerService = eventProducerService;
    }

    /**
     * create new employee and send create event
     * @param employeeRequest
     * @return
     * @throws Exception
     */
    @Override
    @Transactional
    public GenericResponseDTO createEmployee(EmployeeRequest employeeRequest) throws Exception {
        logger.info().message("Start create new employee.").log();
        var department = departmentService.getDepartmentById(employeeRequest.getDepartmentId());
        var employee = new Employee();
        setEmployeeData(employee, employeeRequest, department);
        employee = employeeRepository.save(employee);
        logger.info().message("Start send create event.").log();
        produceEmployeeEvent(employee.getId(), ACTIONS.CREATED.value);
        logger.info().message(String.format("End create new employee.", employee.getId())).log();
        return new GenericResponseDTO("Employee created successfully with UUID {" + employee.getId() + "}");
    }


    /**
     * update employee and send update event
     * @param employeeUUID
     * @param employeeRequest
     * @return
     * @throws Exception
     */
    @Override
    @Transactional
    public GenericResponseDTO updateEmployee(EmployeeID employeeUUID, EmployeeRequest employeeRequest) throws Exception {
        Optional<Employee> optionalEmployee = employeeRepository.findById(employeeUUID.getId());

        if (optionalEmployee.isEmpty()) {
            throw new EntityNotFoundException("Employee with UUID {" + employeeUUID + "} not found");
        }
        logger.info().message(String.format("Start update employee.", employeeUUID.getId())).log();
        var department = departmentService.getDepartmentById(employeeRequest.getDepartmentId());
        var employee = optionalEmployee.get();
        setEmployeeData(employee, employeeRequest, department);
        employeeRepository.save(employee);
        logger.info().message("Start send update event.").log();
        produceEmployeeEvent(employee.getId(), ACTIONS.UPDATED.value);
        logger.info().message(String.format("End update employee.", employee.getId())).log();
        return new GenericResponseDTO("Employee with UUID {" + employee.getId() + "} updated successfully");
    }

    /**
     * delete employee and send delete event
     * @param employeeUUID
     * @return
     * @throws Exception
     */
    @Override
    @Transactional
    public GenericResponseDTO deleteEmployee(EmployeeID employeeUUID) throws Exception {
        Optional<Employee> optionalEmployee = employeeRepository.findById(employeeUUID.getId());
        if (optionalEmployee.isEmpty()) {
            throw new EntityNotFoundException("Employee with UUID {" + employeeUUID + "} not found");
        }
        logger.info().message(String.format("Start delete employee.", employeeUUID.getId())).log();
        employeeRepository.deleteById(employeeUUID.getId());
        logger.info().message("Start send delete event.").log();
        produceEmployeeEvent(employeeUUID.getId(), ACTIONS.DELETED.value);
        logger.info().message(String.format("End delete employee.", employeeUUID.getId())).log();
        return new GenericResponseDTO("Employee with UUID {" + employeeUUID + "} deleted successfully");
    }


    /**
     * retrieve employee from db
     * @param employeeUUID
     * @return
     * @throws Exception
     */
    @Override
    public EmployeeDTO getEmployeeByUUID(EmployeeID employeeUUID) throws Exception {
        logger.info().message("Start retrieve employee.").log();
        Optional<Employee> optionalEmployee = employeeRepository.findById(employeeUUID.getId());

        if (optionalEmployee.isEmpty()) {
            throw new EntityNotFoundException("Employee with UUID {" + employeeUUID + "} not found");
        }

        var employee = optionalEmployee.get();
        var employeeDTO = employeeMapper.toBusinessObject(optionalEmployee.get());
        if (Objects.nonNull(employee.getDepartment())) {
            var departmentDTO = departmentMapper.toBusinessObject(employee.getDepartment());
            employeeDTO.setDepartmentDTO(departmentDTO);
        }
        return employeeDTO;
    }

    private void produceEmployeeEvent(UUID employeeUUID, String eventAction) {
        var employeeEvent = new EmployeeEventDTO(employeeUUID, eventAction, LocalDateTime.now());
        eventProducerService.produceEventAction(employeeEvent);
    }

    private void setEmployeeData(Employee employee, EmployeeRequest employeeRequest, Department department) {

        employee.setEmail(employeeRequest.getEmail());
        employee.setFullName(employeeRequest.getFullName());
        employee.setBirthday(employeeRequest.getBirthday());
        employee.setDepartment(department);
    }

}
