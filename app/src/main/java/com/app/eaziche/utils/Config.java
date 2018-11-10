package com.app.eaziche.utils;

/**
 * Created by hardik on 03-10-2016.
*/
public class Config {

    //URLs to register.php and confirm.php file
    public static final String REGISTER_URL = "http://eaziche.tk/android/register.php";
    public static final String CONFIRM_URL = "http://eaziche.tk/android/confirm.php";
    public static final String CITY = "http://eaziche.tk/android/city.php";

    //Keys to send username, password, phone and otp
    public static final String KEY_USERNAME = "user_name";
    public static final String KEY_PHONE = "phone";
    public static final String KEY_OTP = "otp";
    public static Boolean VERIFIED = true;
    public static Boolean REGISTED = true;
    public static String USERNAME="";
    public static String PHONE ="";


    //JSON Tag from response from server
    public static final String TAG_RESPONSE= "Status=0";
}
