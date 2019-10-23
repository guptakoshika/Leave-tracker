package com.example.leavetracker.models.request;

import javax.validation.constraints.NotNull;

public class LeaveRequestModel {

    @NotNull
    private String leaveStartDate;

    @NotNull
    private String leaveEndDate;

    private String leaveReason;

}
