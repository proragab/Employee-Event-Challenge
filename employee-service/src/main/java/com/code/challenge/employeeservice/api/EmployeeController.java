package com.code.challenge.employeeservice.api;

import com.code.challenge.employeeservice.api.request.EmployeeRequest;
import com.code.challenge.employeeservice.dto.EmployeeDTO;
import com.code.challenge.employeeservice.dto.GenericResponseDTO;
import com.code.challenge.employeeservice.service.EmployeeService;
import com.code.challenge.employeeservice.utils.EmployeeID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author Ragab Belal
 */
@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @PostMapping
    public ResponseEntity<GenericResponseDTO> createEmployee( @Valid @RequestBody EmployeeRequest employeeRequest) throws Exception {
        GenericResponseDTO response = employeeService.createEmployee(employeeRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping(value = "/{employeeUUID}")
    public ResponseEntity<GenericResponseDTO> updateEmployee(@PathVariable EmployeeID employeeUUID, @Valid @RequestBody EmployeeRequest employeeRequest) throws Exception {
        GenericResponseDTO response = employeeService.updateEmployee(employeeUUID, employeeRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/{employeeUUID}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable EmployeeID employeeUUID) throws Exception {
        return new ResponseEntity<>(this.employeeService.getEmployeeByUUID(employeeUUID), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{employeeUUID}")
    public ResponseEntity<GenericResponseDTO> deleteEmployee(@PathVariable EmployeeID employeeUUID) throws Exception {
        GenericResponseDTO response = this.employeeService.deleteEmployee(employeeUUID);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
