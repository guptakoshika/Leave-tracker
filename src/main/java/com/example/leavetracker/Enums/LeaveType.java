package com.example.leavetracker.Enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonGetter;

@JsonFormat
public enum LeaveType {
    MATERNITY, PATERNITY, SABBATICAL;

    @JsonGetter
    public String toString() {
        return this.name();
    }
}
