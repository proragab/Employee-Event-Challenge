package com.code.challenge.employeeservice.mapper;

import com.code.challenge.employeeservice.config.MapStructConfig;
import com.code.challenge.employeeservice.dto.DepartmentDTO;
import com.code.challenge.employeeservice.entity.Department;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

/**
 * @author Ragab Belal
 */
@Mapper(config = MapStructConfig.class, unmappedTargetPolicy = ReportingPolicy.WARN)
public abstract class DepartmentMapper implements ReadEntityMapper<DepartmentDTO, Department> {

    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "name", source = "name")
    })
    @Override
    public abstract DepartmentDTO toBusinessObject(Department entity);

}
