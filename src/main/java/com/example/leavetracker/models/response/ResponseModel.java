package com.example.leavetracker.models.response;

import lombok.Data;

@Data
public class ResponseModel {

    private String Status;

    private String message;

    private Object data;

    private Object error;

}
