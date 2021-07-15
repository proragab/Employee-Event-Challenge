package com.code.challenge.eventservice.mapper;

import com.code.challenge.eventservice.config.MapStructConfig;
import com.code.challenge.eventservice.dto.EmployeeEventDTO;
import com.code.challenge.eventservice.entity.EmployeeEevnt;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

import java.util.List;

/**
 * @author Ragab Belal
 */
@Mapper(config = MapStructConfig.class, unmappedTargetPolicy = ReportingPolicy.WARN)
public abstract class EmployeeEventMapper implements ReadEntityMapper<EmployeeEventDTO, EmployeeEevnt> {

    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "actionEvent", source = "actionEvent"),
            @Mapping(target = "employeeUUID", source = "employeeUUID"),
            @Mapping(target = "eventDateTime", source = "eventDateTime")
    })
    @Override
    public abstract List<EmployeeEventDTO> toBusinessObjects(List<EmployeeEevnt> entityList);
}
