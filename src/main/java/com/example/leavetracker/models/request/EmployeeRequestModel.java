package com.example.leavetracker.models.request;

import javax.validation.constraints.NotNull;

public class EmployeeRequestModel {

    @NotNull
    private String firstName;


    @NotNull
    private String lastName;

    @NotNull
    private String joiningDate;

    @NotNull
    private String gender;

}
