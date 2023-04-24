package com.example.univote.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.example.univote.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashScreen extends AppCompatActivity {

    public static final String PREFERENCES = "prefkey";
    SharedPreferences sharedPreferences;
    public static final String IsLogIn = "islogin";
//    Timer changeScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

    }
    protected void onStart(){
        super.onStart();

        //
        sharedPreferences = getApplicationContext().getSharedPreferences(PREFERENCES,MODE_PRIVATE);
        boolean bol = sharedPreferences.getBoolean(IsLogIn,false);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        new Handler().postDelayed(()->{
            if (user != null){
                startActivity(new Intent(SplashScreen.this, Dashboard.class));
                finish();
            }else{
                startActivity(new Intent(SplashScreen.this,LogIn.class));
                finish();
            }
        },500);
    }

}