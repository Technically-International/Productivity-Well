package com.example.productivitywell;


import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

@ParseClassName("Post")
public class Post extends ParseObject {
    public static final String FOCUS_TIME = "focus";
    public static final String  STUDY_TIME = "study";
    public static final  String SLEEP_TIME = "sleep";
    public static final String WORK_TIME = "work";
    public static final String OTHER_TIME = "other";

    public String getFocusTime(){
        return getString(FOCUS_TIME);
    }
    public String getStudyTime(){
        return getString(STUDY_TIME);
    }
    public String getSleepTime(){
        return getString(SLEEP_TIME);
    }
    public String getWorkTime(){
        return getString(WORK_TIME);
    }
    public String getOtherTime(){
        return getString(OTHER_TIME);
    }

//    public int setDescription(String description){
//        put(KEY_DESCRIPTION,description);
//    }

//    public ParseFile getImage(){
//        return getParseFile(KEY_IMAGE);
//    }

//    public void setImage(ParseFile parseFile){
//        put(KEY_IMAGE,parseFile);
//    }

//    public ParseUser getUser(){
//        return getParseUser(KEY_USER);
//    }

//    public void setUser(ParseUser user){
//        put(KEY_USER,user);
//    }



}
