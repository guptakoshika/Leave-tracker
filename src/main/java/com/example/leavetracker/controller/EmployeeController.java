package com.example.leavetracker.controller;

import com.example.leavetracker.models.request.EmployeeRequestModel;
import com.example.leavetracker.services.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/employee")
public class EmployeeController {

    @Autowired
    private EmployeeServiceImpl employeeService;

    @GetMapping(value = "")
    public ResponseEntity getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping(value = "/{empId}")
    public ResponseEntity fetchEmployee(@PathVariable int empId) {
        return employeeService.getAllEmployees();
    }

    @PostMapping(value = "")
    public HttpStatus employee(@RequestBody EmployeeRequestModel employeeRequestModel) {
        employeeService.saveEmployee(employeeRequestModel);
        return HttpStatus.ACCEPTED;
    }

    @DeleteMapping(value = "/{id}")
    public HttpStatus deleteEmployee(@PathVariable int id) {
        return employeeService.deleteEmployee(id);
    }

}
