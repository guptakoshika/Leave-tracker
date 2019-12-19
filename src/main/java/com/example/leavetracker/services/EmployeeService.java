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
     * @param empId: Accepts an employee id.
     * @returns Optional<Employee> : Returns if an employee if found else throw exception
     */
    ResponseModel fetchEmployee(Long empId) throws Exception;

    /***
     * This method is used to fetch all the employes.
     * @returns list of the employess.
     */
    ResponseModel getAllEmployees();

    /***
     * This method is used to delete the employee from the db.
     * @param  empId : employee id which has to be removed.
     * @returns deleted employee object.
     */
    ResponseModel deleteEmployee(Long empId);
}
