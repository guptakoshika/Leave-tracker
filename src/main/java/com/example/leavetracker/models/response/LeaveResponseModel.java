package com.example.leavetracker.models.response;

import com.example.leavetracker.enums.LeaveStatus;
import lombok.Data;

@Data
public class LeaveResponseModel {

    private LeaveStatus status;

}
