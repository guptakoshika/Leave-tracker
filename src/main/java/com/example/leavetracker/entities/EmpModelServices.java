package com.example.leavetracker.entities;

import com.example.leavetracker.enums.Gender;

public class EmpModelServices {

    public boolean isValidGender(String gender){
        if(gender.equals(Gender.FEMALE) || gender.equals(Gender.MALE))
            return true;
        return false;
    }
}
