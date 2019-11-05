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
    public ResponseEntity<ResponseModel> saveEmployee(EmployeeRequestModel employeeRequestModel) {
        try {
            log.info("im in save employee method");
            Employee newEmployee;
            newEmployee = getNewEmployeeObj(employeeRequestModel);
            if (newEmployee != null) {
                log.info("all validations passed! asving emp into db");
                employeeRepository.save(newEmployee);
                return new ResponseEntity<ResponseModel>(new ResponseModel(Constants.STATUS_SUCCESS, Constants.EMP_ADD_SUCCESS, newEmployee, null), HttpStatus.OK);
            } else {
                log.info("validation failed ! ");
                return new ResponseEntity<ResponseModel>(new ResponseModel(Constants.STATUS_FAILED, Constants.EMP_ADD_FAILED, null, null) , HttpStatus.BAD_REQUEST);
            }
        } catch (Exception ex) {
            log.info(ex.getMessage());
            return new ResponseEntity<ResponseModel>(new ResponseModel(Constants.STATUS_FAILED, Constants.EMP_ADD_FAILED, null, null), HttpStatus.BAD_REQUEST);
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
            log.info("expection occured in gettign all employees" + e);
            return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
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
            createEmployee.getEmployee().setLeaveBalance(12L);
            return createEmployee.getEmployee();
        }
        return null;
    }

    private CreateEmployee isValidReuest(CreateEmployee createEmployee, EmployeeRequestModel employeeRequestModel) {
       log.info("is valid request is called");
        Employee employee = new Employee();
        if (employeeRequestModel != null) {
            log.info("request model is not null");
            if (employeeRequestModel.getName() != null && employeeRequestModel.getName() != "") {
                log.info("request model name validation passed");
                employee.setName(employeeRequestModel.getName());
                if (employeeRequestModel.getJoiningDate() != null && employeeRequestModel.getJoiningDate() != "") {
                    Date date = Util.gateDateFromString(employeeRequestModel.getJoiningDate());
                    if (Util.isValidDate(date)) {
                        employee.setJoiningDate(date);
                        log.info("date validation passed!");
                    } else {
                        log.info("date validation failed!");
                        createEmployee.setIsValid(false);
                        createEmployee.setEmployee(null);
                        return createEmployee;
                    }
                    if (employeeRequestModel.getGender() != null && employeeRequestModel.getGender() != "") {
                        log.info("gender validation passed!");
                        createEmployee.setIsValid(true);
                        if(employeeRequestModel.getGender().equalsIgnoreCase(Constants.EMP_GENDER_MALE)){
                            employee.setGender(Gender.MALE);
                        }else if(employeeRequestModel.getGender().equalsIgnoreCase(Constants.EMP_GENDER_FEMALE)){
                           employee.setGender(Gender.FEMALE);
                        }else{
                            employee.setGender(Gender.NOT_DEFINED);
                        }
                        createEmployee.setEmployee(employee);
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