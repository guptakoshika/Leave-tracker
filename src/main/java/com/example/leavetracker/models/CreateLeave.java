package com.example.leavetracker.models;

import com.example.leavetracker.entities.Leave;
import lombok.Data;

@Data
public class CreateLeave {
    private Boolean isValid;

    private Leave leave;
}
