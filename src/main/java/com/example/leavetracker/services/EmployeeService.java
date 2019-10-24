package com.example.leavetracker.services;

import com.example.leavetracker.models.request.EmployeeRequestModel;
import com.example.leavetracker.models.response.ResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public interface EmployeeService {
        /***
         * This method is used to save employee locally via Employee Object
         * @param employeeRequestModel : Accepts an employee object
         * @returns Response Entity: Returns Http status.
         */
         ResponseModel saveEmployee(EmployeeRequestModel employeeRequestModel);

        /***
         * This method is used to fetch employee from database
         * @param empIdPassed: Accepts an employee id.
         * @returns Optional<Employee> : Returns if an employee if found else throw exception
         */
        ResponseEntity fetchEmployee(int empIdPassed) throws Exception ;

        /***
         * This method is used to fetch all the employes.
         * @returns list of the employess.
         */
        ResponseEntity getAllEmployees() ;

        /***
         * This method is used to delete the employee from the db.
         * @Params empIdPassed : employee id which has to be removed.
         * @returns deleted employee object.
         */
        HttpStatus deleteEmployee(int employeeIdPassed) ;
}
