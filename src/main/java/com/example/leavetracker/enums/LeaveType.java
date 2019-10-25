package com.example.leavetracker.enums;


public enum LeaveType {

    MATERNITY("MATERNITY"),
    PATERNITY("PATERNITY"),
    SABBATICAL("SABATICAL");

    private final String value;

    private LeaveType(String value) {
        this.value = value;
    }

    public String toString() {
        return value;
    }
}
