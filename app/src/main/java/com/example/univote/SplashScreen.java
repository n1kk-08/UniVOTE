package com.example.univote;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import java.util.Timer;
import java.util.TimerTask;

public class SplashScreen extends AppCompatActivity {

    public static final String PREFERENCES = "prefkey";
    SharedPreferences sharedPreferences;
    public static final String IsLogIn = "islogin";
//    Timer changeScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

//        changeScreen = new Timer();
//        changeScreen.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                startActivity(new Intent(SplashScreen.this, LogIn.class));
//                finish();
//            }
//        },500);
    }
    protected void onStart(){
        super.onStart();

        //
        sharedPreferences = getApplicationContext().getSharedPreferences(PREFERENCES,MODE_PRIVATE);
        boolean bol = sharedPreferences.getBoolean(IsLogIn,false);

        new Handler().postDelayed(()->{
            if (bol){
                startActivity(new Intent(SplashScreen.this, Dashboard.class));
            }else{
                startActivity(new Intent(SplashScreen.this,LogIn.class));
                finish();
            }
        },500);
    }

}