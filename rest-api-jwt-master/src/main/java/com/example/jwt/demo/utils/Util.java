package com.example.jwt.demo.utils;

public class Util {
    public static Integer tryParseInt(Object o) {
        if (o == null)
            return null;

        int retVal = 0;
        try {
            retVal = Integer.parseInt(o.toString().trim());
        } catch (NumberFormatException nfe) {
        }

        return retVal;
    }
}
