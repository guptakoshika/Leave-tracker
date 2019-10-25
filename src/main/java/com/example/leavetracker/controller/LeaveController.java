package com.example.leavetracker.controller;

import com.example.leavetracker.Constants;
import com.example.leavetracker.models.request.LeaveRequestModel;
import com.example.leavetracker.models.response.ResponseModel;
import com.example.leavetracker.services.LeaveService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/leave")
public class LeaveController {

    Logger logger = LoggerFactory.getLogger(Controller.class);

    @Autowired
    private LeaveService leaveService;

    @GetMapping(value = "/{empId}")
    public ResponseEntity<ResponseModel> getLeavesByEmpId(@PathVariable Long empId) {
        try {
            return new ResponseEntity<>(leaveService.getLeave(empId) , HttpStatus.OK);
        }catch (Exception ex)
        {
            return null;
        }
    }

    @GetMapping(value = "/{leaveId}")
    public ResponseEntity getLeaves(@PathVariable Long leaveId) {

        return leaveService.getLeave(leaveId);
    }

    @PostMapping(value = "/{empId}")
    public ResponseEntity<ResponseModel> applyLeave(@PathVariable Long empId, @Valid @RequestBody LeaveRequestModel leaveRequestModel) {
        return new ResponseEntity<>(new ResponseModel(Constants.STATUS_SUCCESS , null ,leaveService.applyLeave(leaveRequestModel), null),HttpStatus.OK);
    }
}
