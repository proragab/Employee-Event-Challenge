package com.code.challenge.employeeservice.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 * @author Ragab Belal
 */
@Data
@Entity
@DynamicUpdate
@Table(name = "employee")
@NoArgsConstructor
public class Employee implements Serializable {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type = "org.hibernate.type.UUIDCharType")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "fullName", nullable = false)
    private String fullName;

    @Column(name = "birthday", nullable = false)
    private Date birthday;

    @ManyToOne
    @JoinColumn(name = "departmentId")
    private Department department;
}
