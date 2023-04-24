package com.example.univote.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.univote.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class Dashboard extends AppCompatActivity {


    BottomNavigationView bnView;
    public static final String PREFERENCES = "prefkey";
    public static final String IsLogIn = "islogin";
    SharedPreferences sharedPreferences;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);

        bnView = findViewById(R.id.navbar);
        sharedPreferences = getApplicationContext().getSharedPreferences(PREFERENCES,MODE_PRIVATE);
        SharedPreferences.Editor pref = sharedPreferences.edit();
        pref.putBoolean(IsLogIn,true);
        pref.commit();



        bnView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                if(id == R.id.nav_home){
                    loadfragment(new HomeFragment(),false);

                }else if (id == R.id.nav_profile){
                    loadfragment(new ProfileFragment(),false);

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        SharedPreferences.Editor pref = sharedPreferences.edit();
        switch (id) {
            case R.id.show_result:
                startActivity(new Intent(Dashboard.this,ResultActivity.class));
                return true;
            case R.id.log_out:
                FirebaseAuth.getInstance().signOut();
                pref.putBoolean(IsLogIn, false);
                pref.commit();
                Intent intent = new Intent(Dashboard.this, LogIn.class);
                startActivity(intent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}