package com.example.leavetracker.services;

import com.example.leavetracker.entities.Employee;
import com.example.leavetracker.repository.EmployeeRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServices {

    @Autowired
    private EmployeeRepo empRepo;

    Logger logger = LoggerFactory.getLogger(EmployeeServices.class);


    /***
     * This method is used to save employee locally via Employee Object
     * @param employee: Accepts an employee object
     * @returns Response Entity: Returns Http status.
     */
    public HttpStatus saveEmployee(Employee employee) {
        try {
            empRepo.save(employee);
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
            if (empRepo.existsById(empIdPassed))
                return new ResponseEntity(empRepo.findById(empIdPassed), HttpStatus.OK);
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
            return new ResponseEntity(empRepo.findAll(), HttpStatus.OK);
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
            if (empRepo.existsById(employeeIdPassed)) {
                empRepo.deleteById(employeeIdPassed);
                return HttpStatus.OK;
            } else
                throw new Exception("employee doesn't exists");
        } catch (Exception e) {
            logger.info(e.getMessage());
            return HttpStatus.BAD_REQUEST;
        }
    }
}