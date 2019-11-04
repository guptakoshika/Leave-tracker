package com.example.leavetracker.models.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class EmployeeRequestModel {

    @NotNull
    private String name;


    private String joiningDate;

    @NotNull
    private String gender;

}
