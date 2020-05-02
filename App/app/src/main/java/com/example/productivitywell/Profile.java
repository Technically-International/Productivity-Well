package com.example.productivitywell;

import com.parse.ParseClassName;
import com.parse.ParseObject;
@ParseClassName("User")
public class Profile extends ParseObject {
    public static final String USER_NAME = "username";
    public static final String EMAIL = "email";

    public String getUserName(){

        return getString(USER_NAME);
    }


}
