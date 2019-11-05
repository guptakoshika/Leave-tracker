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
import java.util.Date;

@Data
@Entity(name = "leaves")
public class Leave implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String reason;

    @Enumerated(EnumType.STRING)
    @NotNull
    private LeaveType Type;

    @NotNull
    private Date startDate;

    @NotNull
    private Date EndDate;

    private LeaveStatus status;

    @ManyToOne
    private Employee employee;
}
