package com.example.leavetracker.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonGetter;

@JsonFormat
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
