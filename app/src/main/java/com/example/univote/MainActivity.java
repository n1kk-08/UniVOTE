package com.example.univote;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    TextView forgetTxt;
    Button SignUp;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SignUp = findViewById(R.id.SignUp);
        forgetTxt = findViewById(R.id.forgetTxt);
        forgetTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "forget button", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this, ForgetPasssword.class));
            }

        });
        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Sign UP", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this, SignUp.class));
            }
        });



    }
}