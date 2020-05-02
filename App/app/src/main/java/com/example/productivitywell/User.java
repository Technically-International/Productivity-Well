package com.example.productivitywell;


import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

@ParseClassName("User")
public class User extends ParseObject {
    public final String KEY_USERNAME = "username";
    public final String KEY_PASSWORD = "password";
    public final String KEY_EMAIL = "email";
    public final String KEY_PROFILEPIC = "profilepic";
    public final String KEY_MONEY = "money";
    public static final String KEY_USER = "objectId";


    //username
    public String getUsername(){
        return getString(KEY_USERNAME);
    }

    public String getMoney(){ return getString(KEY_MONEY);}

    public void setUsername( String username){
        put(KEY_USERNAME,username);
    }


    //password
    public String getPassword(){

        return getString(KEY_PASSWORD);
    }

    public void setPassword( String password){
        put(KEY_PASSWORD,password);
    }

    //email

    //profilepic
    public String getEmail(){

        return getString(KEY_EMAIL);
    }

    public void setMoney(int money){put(KEY_MONEY,money);}
    public void setEmail( String email){
        put(KEY_EMAIL,email);
    }

    public ParseFile getProfilepic(){
        return getParseFile(KEY_PROFILEPIC);
    }

    public void setProfilepic(ParseFile parseFile){
        put(KEY_PROFILEPIC,parseFile);
    }

    public ParseUser getUser(){
        return getParseUser(KEY_USER);
    }

    public void setUser(ParseUser user){
        put(KEY_USER,user);
    }



}
