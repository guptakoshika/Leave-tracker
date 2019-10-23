package com.example.leavetracker.controller;

import com.example.leavetracker.entities.Employee;
import com.example.leavetracker.services.EmployeeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/employee")
public class EmployeeController {

    @Autowired
    private EmployeeServices employeeService;

    @GetMapping(value = "")
    public ResponseEntity getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping(value = "/{empId}")
    public ResponseEntity fetchEmployee(@PathVariable int empId) {
        return employeeService.getAllEmployees();
    }

    @PostMapping(value = "")
    public HttpStatus employee(@RequestBody Employee employee) {
        employeeService.saveEmployee(employee);
        return HttpStatus.ACCEPTED;
    }

    @DeleteMapping(value = "/{id}")
    public HttpStatus deleteEmployee(@PathVariable int id) {
        return employeeService.deleteEmployee(id);
    }

}
