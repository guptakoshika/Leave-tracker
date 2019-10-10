package com.example.leavetracker.Enums;

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
