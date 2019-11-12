package com.example.leavetracker.entities;

import com.example.leavetracker.enums.LeaveStatus;
import com.example.leavetracker.enums.LeaveType;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity(name = "leaves")
public class Leave implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String reason;

    @Enumerated(EnumType.STRING)
    @NotNull
    private LeaveType type;

    @NotNull
    private Date startDate;

    @NotNull
    private Date endDate;

    @Enumerated(EnumType.STRING)
    private LeaveStatus status;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_id")
    private Employee employee;
}
