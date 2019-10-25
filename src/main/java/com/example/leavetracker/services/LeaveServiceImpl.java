package com.example.leavetracker.services;

import com.example.leavetracker.Constants;
import com.example.leavetracker.models.request.LeaveRequestModel;
import com.example.leavetracker.models.response.ResponseModel;
import com.example.leavetracker.repository.LeaveRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class LeaveServiceImpl implements LeaveService {

    Logger logger = LoggerFactory.getLogger(LeaveServiceImpl.class);


    private LeaveRepository leaveRepository;

    @Autowired
    public LeaveServiceImpl(LeaveRepository leaveRepository) {
        this.leaveRepository = leaveRepository;
    }

    /**
     * this method is used to make a leave request and save
     * it to db
     *
     * @param leaveRequestModel: Leave class object.
     * @return HttpStatus : will return ok if done else
     * error will reported to logs.
     */
    public ResponseEntity<ResponseModel> applyLeave(LeaveRequestModel leaveRequestModel) {
        try {
//            leaveRepository.save(leave);
//            leave.getEmployee().setLeaveBalance(leave.getEmployee().getLeaveBalance() - 1);
//            leave.setLeaveStatus(LeaveStatus.APPLIED);
            return HttpStatus.OK;
        } catch (Exception e) {
            logger.info(e.getMessage());
            return HttpStatus.URI_TOO_LONG;
        }
    }

    /**
     * this method is used to get all the leaves applied by the employee.
     *
     * @param empId :id of the employee
     * @return List of all the leaves associated with the employee.
     */
    public ResponseEntity<ResponseModel> getLeave(Long empId) {
        try {
            if (leaveRepository.existsById(empId)) {
                return new ResponseEntity(leaveRepository.findAllById(Collections.singleton(empId)), HttpStatus.OK);
            } else {
                throw new Exception("data not found");
            }
        } catch (Exception e) {
            logger.info(e.getMessage());
            return new ResponseEntity(e, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * this method is used to get leave by id.
     *
     * @param leaveID : id associated with the leave.
     * @return ResponseEntity object containing leave and http response.
     */
    public ResponseEntity<ResponseModel> getLeaveById(Long leaveID) {
        try {
            if (leaveRepository.existsById(leaveID)) {
                return new ResponseEntity(leaveRepository.findById(leaveID), HttpStatus.OK);
            } else {
                throw new Exception(Constants.LEAVE_NOT_FOUND);
            }
        } catch (Exception e) {
            logger.info(e.getMessage());
            return new ResponseEntity(e, HttpStatus.NOT_FOUND);
        }
    }
}