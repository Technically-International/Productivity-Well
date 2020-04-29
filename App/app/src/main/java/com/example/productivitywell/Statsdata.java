package com.example.productivitywell;

import android.util.Log;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseUser;

@ParseClassName("Stats")
public class Statsdata extends ParseObject {
    public  final String FOCUS_TIME = "focus";
    public  final String  STUDY_TIME = "study";
    public  final  String SLEEP_TIME = "sleep";
    public  final String WORK_TIME = "work";
    public  final String OTHER_TIME = "eat";
    public  final String KEY_USER = "user";

 // remember to set the setters for this class
    public int getFocusTime(){
        return getInt(FOCUS_TIME);
    }

    public int getStudyTime(){
        return getInt(STUDY_TIME);
    }
    public int getSleepTime(){
        return getInt(SLEEP_TIME);
    }
    public int getWorkTime(){
        return getInt(WORK_TIME);
    }

    public int getOtherTime(){
        return getInt(OTHER_TIME);
    }

    public ParseUser getUser(){
        return getParseUser(KEY_USER);
    }
}
