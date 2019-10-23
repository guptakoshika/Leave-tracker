package com.example.leavetracker.Controller;

import com.example.leavetracker.Models.Employee;
import com.example.leavetracker.Models.Leave;
import com.example.leavetracker.Services.EmployeeServices;
import com.example.leavetracker.Services.LeaveService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

public class LeaveController {
    Logger logger = LoggerFactory.getLogger(Controller.class);
    @Autowired
    private EmployeeServices employeeService;
    @Autowired
    private LeaveService leaveService;

    @GetMapping(value = "/employee")
    public ResponseEntity getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping(value = "/employee/{empId}")
    public ResponseEntity fetchEmployee(@PathVariable int empId) {
        return employeeService.getAllEmployees();
    }

    @PostMapping(value = "/employee")
    public HttpStatus employee(@RequestBody Employee employee) {
        employeeService.saveEmployee(employee);
        return HttpStatus.ACCEPTED;
    }

    @DeleteMapping(value = "/employee/{id}")
    public HttpStatus deleteEmployee(@PathVariable int id) {
        return employeeService.deleteEmployee(id);
    }

    @GetMapping(value = "/leave/{empId}")
    public ResponseEntity getLeavesByEmpId(@PathVariable int empId) {
        return leaveService.getLeave(empId);
    }

    @GetMapping(value = "/leave/{leaveId}")
    public ResponseEntity getLeaves(@PathVariable int leaveId) {
        return leaveService.getLeave(leaveId);
    }

    @PostMapping(value = "/applyLeave/{empId}")
    public HttpStatus applyLeave(@RequestBody Leave leave) {
        return leaveService.applyLeave(leave);
    }
}
