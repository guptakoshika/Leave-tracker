package com.example.leavetracker.services;

import com.example.leavetracker.models.request.LeaveRequestModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


public interface LeaveService {
        /**
         *this method is used to make a leave request and save
         * it to db
         * @param leaveRequestModel: Leave class object.
         * @return HttpStatus : will return ok if done else
         * error will reported to logs.
         */
        public HttpStatus applyLeave(LeaveRequestModel leaveRequestModel);

        /**
         *this method is used to get all the leaves applied by the employee.
         * @param empIdPassed:id of the employee
         * @return List of all the leaves associated with the employee.
         */
        public ResponseEntity getLeave(int empIdPassed);

        /**
         *this method is used to get leave by id.
         * @param leaveID : id associated with the leave.
         * @return ResponseEntity object containing leave and http response.
         */
        public ResponseEntity getLeaveById(int leaveID);
}
