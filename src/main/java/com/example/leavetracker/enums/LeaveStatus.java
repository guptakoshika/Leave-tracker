package com.example.leavetracker.enums;


public enum LeaveStatus {

    APPLIED("APPLIED"),
    CANCELLED("CANCELLED"),
    APPROVED("APPROVED"),
    IN_PROGRESS("IN PROGRESS");

    private final String value;

    private LeaveStatus(String value) {
        this.value = value;
    }

    public String toString() {
        return value;
    }
}
