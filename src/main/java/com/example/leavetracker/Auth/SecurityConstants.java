package com.example.leavetracker.Auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

public class SecurityConstants {

    public final static Long EXCEPTION_TIME = 864000000L;
    public static final String HEADER_STRING = "authorization";
    public static final String ADD_EMP_URL = "/api/employee";
    public static final String TOKEN_SECRET = "jf9i4jgu83nflo";

    @Autowired
    private Environment env ;

    public String getAuthToken() {
        return env.getProperty("TOKEN_SECRET");
    }
}
