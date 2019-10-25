package com.example.leavetracker.controller;

import com.example.leavetracker.models.request.EmployeeRequestModel;
import com.example.leavetracker.models.response.ResponseModel;
import com.example.leavetracker.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(value = "/{empId}" , produces = "application/JSON")
    public ResponseEntity<ResponseModel> fetchEmployee(@PathVariable Long empId) {
        return employeeService.getAllEmployees();
    }

    @PostMapping(value = "")
    public ResponseEntity<ResponseModel> employee(@Valid @RequestBody EmployeeRequestModel employeeRequestModel) {
        employeeService.saveEmployee(employeeRequestModel);
        return HttpStatus.ACCEPTED;
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ResponseModel> deleteEmployee(@PathVariable Long id) {
        return employeeService.deleteEmployee(id);
    }

}
