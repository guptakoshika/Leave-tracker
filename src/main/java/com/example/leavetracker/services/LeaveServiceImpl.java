package com.example.leavetracker.services;

import com.example.leavetracker.Constants;
import com.example.leavetracker.entities.Employee;
import com.example.leavetracker.entities.Leave;
import com.example.leavetracker.enums.LeaveStatus;
import com.example.leavetracker.enums.LeaveType;
import com.example.leavetracker.models.request.LeaveRequestModel;
import com.example.leavetracker.models.response.CommonErrorResonse;
import com.example.leavetracker.models.response.LeaveResponseModel;
import com.example.leavetracker.models.response.ResponseModel;
import com.example.leavetracker.repository.EmployeeRepository;
import com.example.leavetracker.repository.LeaveRepository;
import com.example.leavetracker.util.Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class LeaveServiceImpl implements LeaveService {

    private LeaveRepository leaveRepository;
    private EmployeeRepository employeeRepository;

    @Autowired
    public LeaveServiceImpl(LeaveRepository leaveRepository, EmployeeRepository employeeRepository) {
        this.leaveRepository = leaveRepository;
        this.employeeRepository = employeeRepository;
    }

    /**
     * this method is used to make a leave request and save
     * it to db
     *
     * @param leaveRequestModel: Leave class object.
     * @return HttpStatus : will return ok if done else
     * error will reported to logs.
     */
    public ResponseModel applyLeave(LeaveRequestModel leaveRequestModel) {
        try {
            Leave leave = getLeave(leaveRequestModel);
            LeaveResponseModel leaveResponseModel;
            leaveRepository.save(leave);
            leaveResponseModel = new LeaveResponseModel(leave.getStatus());
            log.info("leave saved successfully!");
            return new ResponseModel(Constants.STATUS_SUCCESS, Constants.LEAVE_APPLY_SUCCESS, leaveResponseModel, null);
        } catch (Exception e) {
            log.info("exception occured in saving leave ");
            return new ResponseModel(Constants.STATUS_FAILED, Constants.LEAVE_APPLY_FAILED, null, e.getStackTrace());
        }
    }

    /**
     * this method is used to get all the leaves applied by the employee.
     *
     * @param empId :id of the employee
     * @return List of all the leaves associated with the employee.
     */
    public ResponseModel getLeave(Long empId) {
        try {
            ResponseModel resp = new ResponseModel();
            if(empId != null){

            }else{
                return new ResponseModel(Constants.STATUS_FAILED , Constants.EMP_ID_MANDATORY , null , null);
            }
        } catch (Exception e) {
            log.info(e.getMessage());
            return new ResponseModel(Constants.STATUS_FAILED , null , null , e);
        }
    }

    /**
     * this method is used to get leave by id.
     *
     * @param leaveID : id associated with the leave.
     * @return ResponseEntity object containing leave and http response.
     */
    public ResponseModel getLeaveById(Long leaveID) {
        try {
            if (leaveRepository.existsById(leaveID)) {
                return new ResponseEntity(leaveRepository.findById(leaveID), HttpStatus.OK);
            } else {
                throw new Exception(Constants.LEAVE_NOT_FOUND);
            }
        } catch (Exception e) {
            log.info(e.getMessage());
            return new ResponseEntity(e, HttpStatus.NOT_FOUND);
        }
    }

    private Leave getLeave(LeaveRequestModel leaveRequestModel) throws Exception {
        Leave leave = getValidatedLeave(leaveRequestModel);
        if (leave != null) {
            log.info("all validations passed adding leave status to the model");
            leave.setStatus(LeaveStatus.APPLIED);
            Optional<Employee> emp = employeeRepository.findById(leaveRequestModel.getEmployeeId());
            if(emp != null){
                leave.setEmployee(emp.get());
            }
        }
        return leave;
    }

    private Leave getValidatedLeave(LeaveRequestModel leaveRequestModel) throws Exception {
        Leave leave = new Leave();
        if (leaveRequestModel != null) {
            if (leaveRequestModel.getLeaveReason() != null && leaveRequestModel.getLeaveReason() != "") {
                leave.setReason(leaveRequestModel.getLeaveReason());
            }
            if (leaveRequestModel.getLeaveStartDate() != null && leaveRequestModel.getLeaveStartDate() != "") {
                Date startDate = Util.gateDateFromString(leaveRequestModel.getLeaveStartDate());
                if (Util.isValidDate(startDate)) {
                    leave.setStartDate(startDate);
                    Date endDate = Util.gateDateFromString(leaveRequestModel.getLeaveEndDate());
                    if (Util.isValidDate(endDate)) {
                        leave.setEndDate(endDate);
                    } else {
                        return null;
                    }
                } else {
                    return null;
                }
            } else {
                return null;
            }
            if (leaveRequestModel.getLeaveType() != null && leaveRequestModel.getLeaveType() != "") {
                if (leaveRequestModel.getLeaveType().equalsIgnoreCase(Constants.LEAVE_TYPE_SABBATICAL)) {
                    leave.setType(LeaveType.SABBATICAL);

                } else if (leaveRequestModel.getLeaveType().equalsIgnoreCase(Constants.LEAVE_TYPE_PATERNITY)) {
                    leave.setType(LeaveType.PATERNITY);
                } else
                    leave.setType(LeaveType.MATERNITY);
                return leave;
            }
        }
        return null;
    }
}