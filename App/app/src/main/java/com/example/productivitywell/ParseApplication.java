package com.example.productivitywell;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApplication extends Application {
    public void onCreate() {
        super.onCreate();

        // Register your parse models
        ParseObject.registerSubclass(Statsdata.class);


        // set applicationId, and server server based on the values in the Heroku settings.
        // clientKey is not needed unless explicitly configured
        // any network interceptors must be added with the Configuration Builder given this syntax

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("prodwell") // should correspond to APP_ID env variable
                .clientKey("technicallyinternational")  // set explicitly unless clientKey is explicitly configured on Parse server
                .server("https://productivitywell2.herokuapp.com/parse").build());
    }
}