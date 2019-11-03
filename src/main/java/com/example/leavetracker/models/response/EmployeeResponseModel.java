package com.example.leavetracker.models.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeResponseModel {

    private Long employeeId;

    private String employeeName;

}
