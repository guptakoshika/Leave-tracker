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

@Data
@Entity(name = "leaves")
public class Leave implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id")
    private Long id;

    @Column(name = "reason")
    private String reason;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    @NotNull
    private LeaveType Type;

    @Column(name = "start_date")
    @NotNull
    private LocalDate startDate;

    @Column(name = "end_date")
    @NotNull
    private LocalDate EndDate;

    @Column(name = "status")
    private LeaveStatus status;

    @ManyToOne
    private Employee employee;
}
