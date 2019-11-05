package com.example.leavetracker.entities;

import com.example.leavetracker.enums.Gender;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Enumerated;
import javax.persistence.EnumType;
import javax.persistence.GenerationType;
import javax.persistence.OneToMany;
import javax.persistence.JoinColumn;
import javax.persistence.CascadeType;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity(name = "employees")
public class Employee implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeId;

    @NotNull(message = "employee name cannot be null")
    private String name;

    @NotNull(message = "employee gender cannot be null")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @NotNull(message = "employee joining date cannot be null")
    private Date joiningDate;

    @Column(name = "email_address", nullable = false , unique = true)
    private String email;

    private Long leaveBalance;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_id")
    private Set<Leave> leaves = new HashSet<>();

}
