package com.example.leavetracker.Enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonGetter;

@JsonFormat
public enum LeaveStatus {
    APPLIED, CANCELED, APPROVED, INPROGRESS;

    @JsonGetter
    public String toString() {
        return this.name();
    }
}
