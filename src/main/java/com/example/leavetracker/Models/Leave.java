package com.example.leavetracker.Models;

import com.example.hashedin.leavetracker.hu16javaleavetracker.enums.LeaveStatus;
import com.example.hashedin.leavetracker.hu16javaleavetracker.enums.LeaveType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Enumerated;
import javax.persistence.EnumType;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
public class Leave implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id")
    private int id;

    @Column(name = "Reason")
    private String reason;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private LeaveType leaveType;

    @Column(name = "leaveStart")
    private LocalDate leaveStart;

    @Column(name = "LeaveEnd")
    private LocalDate leaveEnd;

    @Column(name = "leaveStatus")
    private LeaveStatus leaveStatus;

    @ManyToOne
    private Employee employee;

    /*** getters and setters */
    public int getId() {
        return id;
    }

    public String getReason() {
        return reason;
    }

    public LeaveType getLeaveType() {
        return leaveType;
    }

    public Employee getEmployee() {
        return employee;
    }

    public LocalDate getLeaveStart() {
        return leaveStart;
    }

    public LocalDate getLeaveEnd() {
        return leaveEnd;
    }


    public LeaveStatus getLeaveStatus() {
        return leaveStatus;
    }

    /*** setters */
    public void setId(int id) {
        this.id = id;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setLeaveStatus(LeaveStatus leaveStatus) {
        this.leaveStatus = leaveStatus;
    }

    public void setLeaveType(LeaveType leaveType) {
        this.leaveType = leaveType;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
