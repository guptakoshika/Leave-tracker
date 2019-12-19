package com.example.leavetracker.services;

import com.example.leavetracker.Constants;
import com.example.leavetracker.entities.Employee;
import com.example.leavetracker.entities.Leave;
import com.example.leavetracker.enums.LeaveStatus;
import com.example.leavetracker.enums.LeaveType;
import com.example.leavetracker.models.request.LeaveRequestModel;
import com.example.leavetracker.models.response.LeaveResponseModel;
import com.example.leavetracker.models.response.ResponseModel;
import com.example.leavetracker.repository.EmployeeRepository;
import com.example.leavetracker.repository.LeaveRepository;
import com.example.leavetracker.util.Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
            leaveResponseModel = new LeaveResponseModel();
            leaveResponseModel.setStatus(leave.getStatus());
            log.debug("leave saved successfully!");
            return new ResponseModel(Constants.STATUS_SUCCESS, Constants.LEAVE_APPLY_SUCCESS, leaveResponseModel, null);
        } catch (Exception e) {
            log.error("exception occured in saving leave ");
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
        ResponseModel resp = new ResponseModel();
        try {
            if (empId != null) {
                List<Leave> leaves = leaveRepository.getLeaveByEmpId(empId);
                resp.setStatus(Constants.STATUS_SUCCESS);
                resp.setMessage(Constants.LEAVES_FOR_EMP_ID);
                resp.setData(leaves);
            } else {
                resp.setStatus(Constants.STATUS_FAILED);
                resp.setMessage(Constants.EMP_ID_MANDATORY);
            }
        } catch (Exception e) {
            log.info(e.getMessage());
            resp.setStatus(Constants.STATUS_FAILED);
            resp.setMessage(Constants.EMP_ID_MANDATORY);
        }
        return resp;
    }

    /**
     * this method is used to get leave by id.
     *
     * @param leaveID : id associated with the leave.
     * @return ResponseEntity object containing leave and http response.
     */
    public ResponseModel getLeaveById(Long leaveID) {
        ResponseModel resp = new ResponseModel();
        try {
            if (leaveRepository.existsById(leaveID)) {
                resp.setStatus(Constants.STATUS_SUCCESS);
                resp.setMessage(Constants.LEAVES_FOR_EMP_ID);
                resp.setData(leaveRepository.findById(leaveID));
            } else {
                resp.setStatus(Constants.STATUS_FAILED);
                resp.setMessage(Constants.LEAVE_NOT_FOUND);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            resp.setStatus(Constants.STATUS_FAILED);
            resp.setMessage(Constants.LEAVE_NOT_FOUND);
        }
        return resp;
    }

    private Leave getLeave(LeaveRequestModel leaveRequestModel) throws Exception {
        Leave leave = getValidatedLeave(leaveRequestModel);
        if (leave != null) {
            log.info("all validations passed adding leave status to the model");
            leave.setStatus(LeaveStatus.APPLIED);
            Optional<Employee> emp = employeeRepository.findById(leaveRequestModel.getEmployeeId());
            if (emp != null) {
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