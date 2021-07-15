package com.code.challenge.employeeservice.mapper;

import com.code.challenge.employeeservice.config.MapStructConfig;
import com.code.challenge.employeeservice.dto.EmployeeDTO;
import com.code.challenge.employeeservice.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

/**
 * @author Ragab Belal
 */
@Mapper(config = MapStructConfig.class, unmappedTargetPolicy = ReportingPolicy.WARN)
public abstract class EmployeeMapper implements ReadEntityMapper<EmployeeDTO, Employee>{

    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "email", source = "email"),
            @Mapping(target = "fullName", source = "fullName"),
            @Mapping(target = "birthday", source = "birthday"),
    })
    @Override
    public abstract EmployeeDTO toBusinessObject(Employee entity);

}
