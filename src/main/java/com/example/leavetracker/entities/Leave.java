package com.example.leavetracker.entities;

import com.example.leavetracker.enums.LeaveStatus;
import com.example.leavetracker.enums.LeaveType;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Enumerated;
import javax.persistence.EnumType;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Data
public class Leave implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id")
    private Integer id;

    @Column(name = "Reason")
    private String reason;

    @Column(name = "leave_type")
    @Enumerated(EnumType.STRING)
    @NotNull
    private LeaveType leaveType;

    @Column(name = "leave_start_date")
    @NotNull
    private LocalDate leaveStartDate;

    @Column(name = "Leave_end_date")
    @NotNull
    private LocalDate leaveEndDate;

    @Column(name = "leave_status")
    private LeaveStatus leaveStatus;

    @ManyToOne
    private Employee employee;
}
