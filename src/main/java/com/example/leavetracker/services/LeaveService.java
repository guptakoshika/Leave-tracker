package com.example.leavetracker.services;

import com.example.leavetracker.enums.LeaveStatus;
import com.example.leavetracker.entities.Leave;
import com.example.leavetracker.repository.LeaveRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class LeaveService {

    Logger logger = LoggerFactory.getLogger(LeaveService.class);


    private LeaveRepository leaveRepository;

    @Autowired
    public LeaveService (LeaveRepository leaveRepository){
        this.leaveRepository = leaveRepository;
    }

    /**
     *this method is used to make a leave request and save
     * it to db
     * @param leave: Leave class object.
     * @return HttpStatus : will return ok if done else
     * error will reported to logs.
     */
    public HttpStatus applyLeave(Leave leave) {
        try {
            leaveRepository.save(leave);
            leave.getEmployee().setLeaveBalance(leave.getEmployee().getLeaveBalance() - 1);
            leave.setLeaveStatus(LeaveStatus.APPLIED);
            return HttpStatus.OK;
        } catch (Exception e) {
            logger.info(e.getMessage());
            return HttpStatus.URI_TOO_LONG;
        }
    }

    /**
     *this method is used to get all the leaves applied by the employee.
     * @param empIdPassed:id of the employee
     * @return List of all the leaves associated with the employee.
     */
    public ResponseEntity getLeave(int empIdPassed) {
        try {
            if (leaveRepository.existsById(empIdPassed)) {
                return new ResponseEntity(leaveRepository.findAllById(Collections.singleton(empIdPassed)), HttpStatus.OK);
            } else {
                throw new Exception("data not found");
            }
        } catch (Exception e) {
            logger.info(e.getMessage());
            return new ResponseEntity(e,HttpStatus.NOT_FOUND);
        }
    }

    /**
     *this method is used to get leave by id.
     * @param leaveID : id associated with the leave.
     * @return ResponseEntity object containing leave and http response.
     */
    public ResponseEntity getLeaveById(int leaveID) {
        try{
            if(leaveRepository.existsById(leaveID))
            {
                return new ResponseEntity(leaveRepository.findById(leaveID),HttpStatus.OK);
            }
            else
            {
                throw new Exception("leave not found");
            }
        }
        catch(Exception e){
            logger.info(e.getMessage());
            return new ResponseEntity(e , HttpStatus.NOT_FOUND);
        }
    }
}