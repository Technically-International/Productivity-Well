package com.example.productivitywell;
import com.parse.ParseFile;
import com.parse.ParseUser;

public class User extends ParseUser {
    public static final String KEY_PROFILEPIC = "profilepic";
    public static final String KEY_MONEY = "money";


    //profilepic
    public ParseFile getProfilepic(){
        return getParseFile(KEY_PROFILEPIC);
    }
    public void setProfilepic(ParseFile parseFile){
        put(KEY_PROFILEPIC,parseFile);
    }


    //money
    public String getMoney(){
        return getString(KEY_MONEY);
    }
    public void setMoney(ParseFile parseFile){
        put(KEY_MONEY,parseFile);
    }
}
