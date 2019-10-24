package com.example.leavetracker.models.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseModel {

    private String Status;

    private String message;

    private Object data;

    private Object error;

}
