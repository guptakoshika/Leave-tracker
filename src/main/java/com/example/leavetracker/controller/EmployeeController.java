package com.example.leavetracker.controller;

import com.example.leavetracker.Constants;
import com.example.leavetracker.models.request.EmployeeRequestModel;
import com.example.leavetracker.models.response.ResponseModel;
import com.example.leavetracker.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping(value = "")
    public ResponseEntity<ResponseModel> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping(value = "/{empId}", produces = "application/JSON")
    public ResponseEntity<ResponseModel> fetchEmployee(@PathVariable Long empId) {
        try {
            return new ResponseEntity<>((new ResponseModel(Constants.STATUS_SUCCESS, null, employeeService.getAllEmployees(), null)), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>((new ResponseModel(Constants.STATUS_FAILED, null, null, null)), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "")
    public ResponseEntity<ResponseModel> employee(@Valid @RequestBody EmployeeRequestModel employeeRequestModel) {
        try {
            return employeeService.saveEmployee(employeeRequestModel);
        } catch (Exception ex) {
            return new ResponseEntity<>((new ResponseModel(Constants.STATUS_FAILED, null, null, null)), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ResponseModel> deleteEmployee(@PathVariable Long id) {
        try {
            return new ResponseEntity<>((new ResponseModel(Constants.STATUS_SUCCESS, null, employeeService.deleteEmployee(id), null)), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>((new ResponseModel(Constants.STATUS_FAILED, null, null, null)), HttpStatus.NOT_FOUND);
        }
    }

}
