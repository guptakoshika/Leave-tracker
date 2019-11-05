package com.example.leavetracker.util;

import com.example.leavetracker.Constants;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {

    /**
     * this method is used to convert Strign to a date object
     * @param date is of type String
     * @return date is object of type date.
     */
    public static Date gateDateFromString(String date) throws ParseException {
        Date resDate = new SimpleDateFormat(Constants.DATE_FORMAT).parse(date);
        return resDate;
    }

    /**
     * this method is used to check if date is valid r not
     * @param date is object of type date
     * @return boolean object based on result
     */
    public static boolean isValidDate(Date date){
        return true;
    }
}
