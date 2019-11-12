package com.example.leavetracker.enums;

public enum Gender {

    MALE("MALE"),
    FEMALE("FEMALE"),
    NOT_DEFINED("NOT DEFINED");

    private final String value;

    Gender(String value) {
        this.value = value;
    }

    public String toString() {
        return value;
    }
}
