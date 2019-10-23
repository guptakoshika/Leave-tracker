package com.example.leavetracker.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonGetter;

@JsonFormat
public enum LeaveStatus {
    APPLIED, CANCELED, APPROVED, IN_PROGRESS;

    @JsonGetter
    public String toString() {
        return this.name();
    }
}
