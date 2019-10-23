package com.example.leavetracker.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonGetter;

@JsonFormat
public enum Gender {
    MALE, FEMALE,NOT_DEFINED;

    @JsonGetter
    public String toString() {
        return this.name();
    }
}
