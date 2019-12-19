package com.example.leavetracker.models.response;

import com.example.leavetracker.entities.Employee;
import com.example.leavetracker.entities.Leave;
import lombok.Data;

@Data
public class CommonErrorResonse {
    boolean valid;
    Employee employee;
    Leave Leave;
}
