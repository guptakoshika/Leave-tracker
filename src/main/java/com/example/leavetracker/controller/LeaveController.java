package com.example.leavetracker.controller;

import com.example.leavetracker.Constants;
import com.example.leavetracker.models.request.LeaveRequestModel;
import com.example.leavetracker.models.response.ResponseModel;
import com.example.leavetracker.services.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/leave")
public class LeaveController {

    @Autowired
    private LeaveService leaveService;

    @GetMapping(value = "/{empId}")
    public ResponseEntity<ResponseModel> getLeavesByEmpId(@PathVariable Long empId) {
        try {
            return new ResponseEntity<>(leaveService.getLeave(empId), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/{leaveId}")
    public ResponseEntity<ResponseModel> getLeaves(@PathVariable Long leaveId) {
        try {
            return new ResponseEntity<>(leaveService.getLeave(leaveId), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/{empId}")
    public ResponseEntity<ResponseModel> applyLeave(@PathVariable Long empId, @Valid @RequestBody LeaveRequestModel leaveRequestModel) {
        try {
            return new ResponseEntity<ResponseModel>(leaveService.applyLeave(leaveRequestModel), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
