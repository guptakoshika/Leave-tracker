package com.example.leavetracker.Models;

import com.example.leavetracker.Enums.Gender;

public class EmpModelServices {

    public boolean isValidGender(String gender){
        if(gender.equals(Gender.FEMALE) || gender.equals(Gender.MALE))
            return true;
        return false;
    }
}
