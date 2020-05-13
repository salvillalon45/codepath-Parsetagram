package com.example.parsetagram;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // Register your parse models
        ParseObject.registerSubclass(Post.class);

        Parse.initialize(new Parse.Configuration.Builder(this)
        .applicationId("android-university-parstagram")
        .clientKey("CodepathMoveFastParse")
        .server("https://android-university-parstagram.herokuapp.com/parse").build());
    }
}
