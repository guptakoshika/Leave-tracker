package com.example.leavetracker.services;

import com.example.leavetracker.models.request.LeaveRequestModel;
import com.example.leavetracker.models.response.ResponseModel;
import org.springframework.http.ResponseEntity;


public interface LeaveService {
    /**
     * this method is used to make a leave request and save
     * it to db
     *
     * @param leaveRequestModel: Leave class object.
     * @return HttpStatus : will return ok if done else
     * error will reported to logs.
     */
    ResponseModel applyLeave(LeaveRequestModel leaveRequestModel);

    /**
     * this method is used to get all the leaves applied by the employee.
     *
     * @param empId :id of the employee
     * @return List of all the leaves associated with the employee.
     */
    ResponseEntity<ResponseModel> getLeave(Long empId);

    /**
     * this method is used to get leave by id.
     *
     * @param leaveID : id associated with the leave.
     * @return ResponseEntity object containing leave and http response.
     */
    ResponseEntity<ResponseModel> getLeaveById(Long leaveID);
}
