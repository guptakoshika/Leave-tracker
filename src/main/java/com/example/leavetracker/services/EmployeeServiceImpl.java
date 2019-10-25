package com.example.leavetracker.services;

import com.example.leavetracker.Constants;
import com.example.leavetracker.entities.Employee;
import com.example.leavetracker.models.request.EmployeeRequestModel;
import com.example.leavetracker.models.response.ResponseModel;
import com.example.leavetracker.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {


    private EmployeeRepository employeeRepository;

    Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

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
            employeeRepository.save(newEmployee);
            return new ResponseModel(Constants.STATUS_SUCCESS, Constants.EMP_ADDED_SUCCESS, newEmployee, null);
        } catch (Exception ex) {
            logger.info(ex.getMessage());
            return new ResponseModel(Constants.STATUS_FAILED, Constants.EMP_ADDED_SUCCESS, null, ex);
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
            logger.info(e.getMessage());
            return new ResponseEntity(HttpStatus.NOT_FOUND);
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
            logger.info(e.getMessage());
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
            logger.info(e.getMessage());
            return HttpStatus.BAD_REQUEST;
        }
    }

    private Employee getNewEmployeeObj(EmployeeRequestModel employeeRequestModel) {
        Employee emp = new Employee();
        if (employeeRequestModel.getName() != null && employeeRequestModel.getName() != "") {
            emp.setEmployeeName(employeeRequestModel.getName());
            return emp;
        }
        return null;
    }
}