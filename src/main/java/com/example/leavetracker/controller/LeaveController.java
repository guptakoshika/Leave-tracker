package com.example.leavetracker.controller;

import com.example.leavetracker.Constants;
import com.example.leavetracker.models.request.LeaveRequestModel;
import com.example.leavetracker.models.response.ResponseModel;
import com.example.leavetracker.services.LeaveService;
import lombok.extern.slf4j.Slf4j;
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

    @Autowired
    private LeaveService leaveService;

    @GetMapping(value = "/{empId}")
    public ResponseEntity<ResponseModel> getLeavesByEmpId(@PathVariable Long empId) {
        try {
            return new ResponseEntity<>((new ResponseModel(Constants.STATUS_SUCCESS, null, leaveService.getLeave(empId), null)), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>((new ResponseModel(Constants.STATUS_FAILED, null, null, null)), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/{leaveId}")
    public ResponseEntity<ResponseModel> getLeaves(@PathVariable Long leaveId) {
        try {
            return new ResponseEntity<>((new ResponseModel(Constants.STATUS_SUCCESS, null, leaveService.getLeave(leaveId), null)), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>((new ResponseModel(Constants.STATUS_FAILED, null, null, null)), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/{empId}")
    public ResponseEntity<ResponseModel> applyLeave(@PathVariable Long empId, @Valid @RequestBody LeaveRequestModel leaveRequestModel) {
        try {
            return new ResponseEntity<>((new ResponseModel(Constants.STATUS_SUCCESS, null,leaveService.applyLeave(leaveRequestModel), null)), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>((new ResponseModel(Constants.STATUS_FAILED, null, null, null)), HttpStatus.NOT_FOUND);
        }
    }
}
