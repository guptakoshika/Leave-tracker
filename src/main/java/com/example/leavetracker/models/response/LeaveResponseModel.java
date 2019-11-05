package com.example.leavetracker.models.response;

import com.example.leavetracker.enums.LeaveStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LeaveResponseModel {

    private LeaveStatus status;

}
