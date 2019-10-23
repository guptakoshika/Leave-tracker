package com.example.leavetracker.models.response;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ResponseModel {

    private HttpStatus httpStatus;

    private String messgae;

    private String data;

    private String error;

}
