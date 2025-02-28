package org.dogra.stockflow.utils;

import org.apache.tomcat.util.file.Matcher;

import java.util.regex.Pattern;

public class Util {
    public static Boolean checkPasswordStregth(String password){

        String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,50}$";

        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);

        if(pattern.matcher(password).matches()){
            return true;
        }

        return false;
    }
}
