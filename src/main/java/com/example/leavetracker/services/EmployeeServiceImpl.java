package com.example.leavetracker.services;

import com.example.leavetracker.constants.Constants;
import com.example.leavetracker.entities.Employee;
import com.example.leavetracker.models.request.EmployeeRequestModel;
import com.example.leavetracker.models.response.CommonErrorResonse;
import com.example.leavetracker.models.response.ResponseModel;
import com.example.leavetracker.repository.EmployeeRepository;
import com.example.leavetracker.util.Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.Optional;

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
        ResponseModel resp = new ResponseModel();
        CommonErrorResonse validation = isValidEmployeeRequest(employeeRequestModel);
        if (validation.isValid()) {
            Employee emp = new Employee();
            emp.setName(employeeRequestModel.getName());
            emp.setEmail(employeeRequestModel.getEmail());
            emp.setJoiningDate(Util.gateDateFromString(employeeRequestModel.getJoiningDate()));
            emp.setLeaveBalance(12L);
            employeeRepository.save(emp);
            resp.setStatus(Constants.STATUS_SUCCESS);
            resp.setMessage(Constants.EMP_ADD_SUCCESS);
        } else {
            resp.setStatus(Constants.STATUS_FAILED);
            resp.setMessage(Constants.EMP_ADD_FAILED);
        }
        return resp;
    }

    /***
     * This method is used to fetch employee from database
     * @param empId: Accepts an employee id.
     * @returns Optional<Employee> : Returns if an employee if found else throw exception
     */
    public ResponseModel fetchEmployee(Long empId) throws Exception {
        ResponseModel resp = new ResponseModel();
        try {
            Optional<Employee> emp = employeeRepository.findById(empId);
            if (emp.isPresent()) {
                resp.setData(emp.get());
                resp.setStatus(Constants.STATUS_SUCCESS);
                resp.setMessage(Constants.EMP_ADD_FAILED);
            } else {
                resp.setStatus(Constants.STATUS_FAILED);
                resp.setMessage(Constants.EMP_ADD_FAILED);
            }
        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return resp;
    }

    /***
     * This method is used to fetch all the employes.
     * @returns list of the employess.
     */
    public ResponseModel getAllEmployees() {
        ResponseModel resp = new ResponseModel();
        try {
            return new ResponseModel(Constants.STATUS_SUCCESS, null, employeeRepository.findAll(), null);
        } catch (Exception e) {
            log.error("exception occurred in gettign all employees" + e);
            return new ResponseModel(Constants.STATUS_SUCCESS, null, null, null);
        }
    }

    /***
     * This method is used to delete the employee from the db.
     * @param  empId : employee id which has to be removed.
     * @returns deleted employee object.
     */
    public ResponseModel deleteEmployee(Long empId) {
        try {
            if (employeeRepository.existsById(empId)) {
                employeeRepository.deleteById(empId);
                return new ResponseModel();
            } else
                throw new Exception("employee doesn't exists");
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseModel();
        }
    }

    private CommonErrorResonse isValidEmployeeRequest(EmployeeRequestModel employeeRequestModel) {
        log.debug("is valid request is called");
        CommonErrorResonse validEmp = new CommonErrorResonse();
        validEmp.setValid(true);
        if (employeeRequestModel != null) {
            if (employeeRequestModel.getName() == null || employeeRequestModel.getName().isEmpty()) {
                validEmp.setValid(false);
                return validEmp;
            }
            if (employeeRequestModel.getEmail() == null || !employeeRequestModel.getEmail().isEmpty()) {
                validEmp.setValid(false);
                return validEmp;
            }
            if (employeeRequestModel.getGender() == null) {
                validEmp.setValid(false);
                return validEmp;
            }
            if (employeeRequestModel.getJoiningDate() == null || employeeRequestModel.getJoiningDate().isEmpty()) {
                validEmp.setValid(false);
                return validEmp;
            } else {
                Date date = Util.gateDateFromString(employeeRequestModel.getJoiningDate());
                if (!Util.isValidDate(date)) ;
                validEmp.setValid(false);
                return validEmp;
            }
        }
        return validEmp;
    }
}