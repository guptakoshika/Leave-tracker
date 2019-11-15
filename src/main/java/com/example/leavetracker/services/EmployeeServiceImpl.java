package com.example.leavetracker.services;

import com.example.leavetracker.Constants;
import com.example.leavetracker.entities.Employee;
import com.example.leavetracker.enums.Gender;
import com.example.leavetracker.models.request.EmployeeRequestModel;
import com.example.leavetracker.models.response.CommonErrorResonse;
import com.example.leavetracker.models.response.EmployeeResponseModel;
import com.example.leavetracker.models.response.ResponseModel;
import com.example.leavetracker.repository.EmployeeRepository;
import com.example.leavetracker.util.Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.text.ParseException;
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
            EmployeeResponseModel empData;
            if (newEmployee != null) {
                log.info("all validations passed! asving emp into db");
                employeeRepository.save(newEmployee);
                empData = new EmployeeResponseModel(newEmployee.getEmployeeId(), newEmployee.getName());
                return new ResponseEntity<ResponseModel>(new ResponseModel(Constants.STATUS_SUCCESS, Constants.EMP_ADD_SUCCESS, empData, null), HttpStatus.OK);
            } else {
                log.info("validation failed ! ");
                return new ResponseEntity<ResponseModel>(new ResponseModel(Constants.STATUS_FAILED, Constants.EMP_ADD_FAILED, null, null), HttpStatus.BAD_REQUEST);
            }
        } catch (Exception ex) {
            log.info("Exception in saving Employee" + ex.getMessage());
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

    private Employee getNewEmployeeObj(EmployeeRequestModel employeeRequestModel) throws ParseException {
        CommonErrorResonse resp = isValidReuest(new CommonErrorResonse(), employeeRequestModel);
        if (resp.isValid()) {
            resp.getEmployee().setLeaveBalance(12L);
            return resp.getEmployee();
        }
        return null;
    }

    private CommonErrorResonse isValidReuest(CommonErrorResonse createEmployee, EmployeeRequestModel employeeRequestModel) throws ParseException {
        log.info("is valid request is called");
        Employee employee = new Employee();
        if (employeeRequestModel != null) {
            if (employeeRequestModel.getName() == null || employeeRequestModel.getName().isEmpty()) {
                createEmployee.setValid(false);
                createEmployee.setEmployee(null);
                return createEmployee;
            }
            if (employeeRequestModel.getEmail() == null || !employeeRequestModel.getEmail().isEmpty()) {
                createEmployee.setValid(false);
                createEmployee.setEmployee(null);
                return createEmployee;
            }
            if (employeeRequestModel.getGender() == null || employeeRequestModel.getGender().isEmpty()) {
                createEmployee.setEmployee(null);
                createEmployee.setValid(true);
                return createEmployee;
            }
            if(employeeRequestModel.getJoiningDate() == null || employeeRequestModel.getJoiningDate().isEmpty()){
                createEmployee.setValid(false);
                createEmployee.setEmployee(null);
                return createEmployee;
            }else{
                Date date = Util.gateDateFromString(employeeRequestModel.getJoiningDate());
                if(!Util.isValidDate(date));
                createEmployee.setEmployee(null);
                createEmployee.setValid(false);
                return createEmployee;
            }
        }
        return createEmployee;
    }
}