package com.example.leavetracker.services;

import com.example.leavetracker.models.request.EmployeeRequestModel;
import com.example.leavetracker.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl {


    private EmployeeRepository employeeRepository;

    Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository){ this.employeeRepository = employeeRepository; }
    /***
     * This method is used to save employee locally via Employee Object
     * @param employeeRequestModel: Accepts an employee object
     * @returns Response Entity: Returns Http status.
     */
    public HttpStatus saveEmployee(EmployeeRequestModel employeeRequestModel) {
        try {
            //employeeRepository.save(employeeRequestModel);
            return HttpStatus.ACCEPTED;
        } catch (Exception ex) {
            logger.info(ex.getMessage());
            return HttpStatus.ALREADY_REPORTED;
        }
    }

    /***
     * This method is used to fetch employee from database
     * @param empIdPassed: Accepts an employee id.
     * @returns Optional<Employee> : Returns if an employee if found else throw exception
     */
    public ResponseEntity fetchEmployee(int empIdPassed) throws Exception {
        try {
            if (employeeRepository.existsById(empIdPassed))
                return new ResponseEntity(employeeRepository.findById(empIdPassed), HttpStatus.OK);
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
     * @Params empIdPassed : employee id which has to be removed.
     * @returns deleted employee object.
     */
    public HttpStatus deleteEmployee(int employeeIdPassed) {
        try {
            if (employeeRepository.existsById(employeeIdPassed)) {
                employeeRepository.deleteById(employeeIdPassed);
                return HttpStatus.OK;
            } else
                throw new Exception("employee doesn't exists");
        } catch (Exception e) {
            logger.info(e.getMessage());
            return HttpStatus.BAD_REQUEST;
        }
    }
}