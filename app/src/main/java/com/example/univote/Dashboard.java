package com.example.univote;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class Dashboard extends AppCompatActivity {
    Button Logout;

    BottomNavigationView bnView;
    public static final String PREFERENCES = "prefkey";
    SharedPreferences sharedPreferences;
    public static final String IsLogIn = "islogin";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);

        sharedPreferences = getApplicationContext().getSharedPreferences(PREFERENCES,MODE_PRIVATE);
        SharedPreferences.Editor pref = sharedPreferences.edit();
        pref.putBoolean(IsLogIn,true);
        pref.commit();

        bnView = findViewById(R.id.navbar);
        Logout = findViewById(R.id.Logout);
        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(Dashboard.this,LogIn.class));
                finish();
            }
        });
        bnView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                if(id == R.id.nav_home){
                    loadfragment(new HomeFragment(),false);

                }else if (id == R.id.nav_profile){
                    loadfragment(new ProfileFragment(),false);

                }else if (id == R.id.nav_register){
                    loadfragment(new RegisterFragment(), false);

                }
                return true;

            }
        });
        bnView.setSelectedItemId(R.id.nav_home);



    }
    public void loadfragment(Fragment fragment, boolean flag){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        if(flag){
            ft.add(R.id.framelayout,fragment);
        }else {
            ft.replace(R.id.framelayout,fragment);
        }

        ft.commit();
    }

}