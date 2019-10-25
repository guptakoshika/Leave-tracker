package com.example.leavetracker.models.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class LeaveRequestModel {

    @NotNull
    private String leaveStartDate;

    @NotNull
    private String leaveEndDate;

    private String leaveReason;

    @NotNull
    private String leaveType;

}
