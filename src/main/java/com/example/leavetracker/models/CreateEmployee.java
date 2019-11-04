package com.example.leavetracker.models;

import com.example.leavetracker.entities.Employee;
import lombok.Data;

@Data
public class CreateEmployee {

    private Boolean isValid;

    private Employee employee;
}
