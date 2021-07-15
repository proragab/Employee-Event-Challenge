package com.code.challenge.eventservice.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author Ragab Belal
 */
@Data
@Entity
@DynamicUpdate
@Table(name = "employeeEvnt")
@NoArgsConstructor
@Accessors(chain = true)
public class EmployeeEevnt implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "actEvnt", nullable = false)
    private String actionEvent;

    @Type(type = "org.hibernate.type.UUIDCharType")
    @Column(name = "employeeUuid", nullable = false)
    private UUID employeeUUID;

    @Column(name = "evntDateTime", nullable = false)
    private LocalDateTime eventDateTime;
}
