package com.example.leavetracker.services;

import com.example.leavetracker.Constants;
import com.example.leavetracker.entities.Employee;
import com.example.leavetracker.enums.Gender;
import com.example.leavetracker.models.CreateEmployee;
import com.example.leavetracker.models.request.EmployeeRequestModel;
import com.example.leavetracker.models.response.ResponseModel;
import com.example.leavetracker.repository.EmployeeRepository;
import com.example.leavetracker.util.Util;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {


    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    /***
     * This method is used to save employee locally via Employee Object
     * @param employeeRequestModel : Accepts an employee object
     * @returns Response Entity: Returns Http status.
     */
    public ResponseModel saveEmployee(EmployeeRequestModel employeeRequestModel) {
        try {
            Employee newEmployee = new Employee();
            newEmployee = getNewEmployeeObj(employeeRequestModel);
            if (newEmployee != null) {
                employeeRepository.save(newEmployee);
                return new ResponseModel(Constants.STATUS_SUCCESS, Constants.EMP_ADD_SUCCESS, newEmployee, null);
            } else {
                return new ResponseModel(Constants.STATUS_FAILED, Constants.EMP_ADD_FAILED, null, null);
            }
        } catch (Exception ex) {
            log.info(ex.getMessage());
            return new ResponseModel(Constants.STATUS_FAILED, Constants.EMP_ADD_FAILED, null, null);
        }
    }

    /***
     * This method is used to fetch employee from database
     * @param empId: Accepts an employee id.
     * @returns Optional<Employee> : Returns if an employee if found else throw exception
     */
    public ResponseEntity fetchEmployee(Long empId) throws Exception {
        try {
            if (employeeRepository.existsById(empId))
                return new ResponseEntity(employeeRepository.findById(empId), HttpStatus.OK);
            else
                throw new Exception("employee not found");

        } catch (Exception e) {
            log.info(e.getMessage());
            return new ResponseEntity(new ResponseModel(), HttpStatus.NOT_FOUND);
        }
    }

    /***
     * This method is used to fetch all the employes.
     * @returns list of the employess.
     */
    public ResponseEntity getAllEmployees() {
        try {
            return new ResponseEntity(employeeRepository.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            log.info(e.getMessage());
            return new ResponseEntity(HttpStatus.REQUEST_TIMEOUT);
        }
    }

    /***
     * This method is used to delete the employee from the db.
     * @param  empId : employee id which has to be removed.
     * @returns deleted employee object.
     */
    public HttpStatus deleteEmployee(Long empId) {
        try {
            if (employeeRepository.existsById(empId)) {
                employeeRepository.deleteById(empId);
                return HttpStatus.OK;
            } else
                throw new Exception("employee doesn't exists");
        } catch (Exception e) {
            log.info(e.getMessage());
            return HttpStatus.BAD_REQUEST;
        }
    }

    private Employee getNewEmployeeObj(EmployeeRequestModel employeeRequestModel) {
        CreateEmployee createEmployee = new CreateEmployee();
        createEmployee = isValidReuest(createEmployee, employeeRequestModel);
        if (createEmployee.getIsValid()) {
            return createEmployee.getEmployee();
        }
        return null;
    }

    private CreateEmployee isValidReuest(CreateEmployee createEmployee, EmployeeRequestModel employeeRequestModel) {
        Employee employee = new Employee();
        if (employeeRequestModel != null) {
            if (employeeRequestModel.getName() != null && employeeRequestModel.getName() != "") {
                employee.setName(employeeRequestModel.getName());
                if (employeeRequestModel.getJoiningDate() != null && employeeRequestModel.getJoiningDate() != "") {
                    Date date = Util.gateDateFromString(employeeRequestModel.getJoiningDate());
                    if (Util.isValidDate(date)) {
                        employee.setJoiningDate(date);
                    } else {
                        createEmployee.setIsValid(false);
                        createEmployee.setEmployee(null);
                        return createEmployee;
                    }
                    if (employeeRequestModel.getGender() != null && employeeRequestModel.getGender() != "") {
                        employee.setGender(Gender.FEMALE);
                        createEmployee.setIsValid(true);
                    } else {
                        createEmployee.setIsValid(false);
                        createEmployee.setEmployee(null);
                        return createEmployee;
                    }
                }
            }
        }
        return createEmployee;
    }
}