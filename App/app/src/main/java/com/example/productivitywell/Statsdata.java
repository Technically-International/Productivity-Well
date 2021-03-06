package com.example.productivitywell;

import android.util.Log;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseUser;

import java.util.Date;

@ParseClassName("Stats")
public class Statsdata extends ParseObject {
    public  final String FOCUS_TIME = "focus";
    public  final String  STUDY_TIME = "study";
    public  final  String SLEEP_TIME = "sleep";
    public  final String WORK_TIME = "work";
    public  final String OTHER_TIME = "other";
    public  static final String KEY_USER = "user";
    public final String DATE_USED = "dateUsed";

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
    public Date gettDate(){
        return getDate(DATE_USED);
    }

    public void setFocusTime(int focuseTime){
        put(FOCUS_TIME, focuseTime);
    }
    public void setStudyTime(int studyTime){
        put(STUDY_TIME,studyTime);
    }
    public void setSleepTime(int sleepTime){
        put(SLEEP_TIME,sleepTime);
    }
    public void setWorkTime(int workTime){
        put(WORK_TIME,workTime);
    }
    public void setOtherTime(int otherTime){
        put(OTHER_TIME,otherTime);
    }

    public void setUser(ParseUser user){
        put(KEY_USER,user);
    }

    public void settDate(Date data){
        put(DATE_USED,data);
    }




}
