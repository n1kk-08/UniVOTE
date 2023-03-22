package com.example.univote;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;


public class MainActivity extends AppCompatActivity {
    TextView forgetTxt;
    TextInputLayout EmailID,Pwd;

    Button SignUp, Login;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //For removing status bar from the page
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        SignUp = findViewById(R.id.SignUp);
        Login = findViewById(R.id.Login);
        forgetTxt = findViewById(R.id.forgetTxt);
        EmailID = findViewById(R.id.username_text_field_design);
        Pwd = findViewById(R.id.password_input_field);

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailid = EmailID.getEditText().getText().toString();
                String password = Pwd.getEditText().getText().toString();
                if (!emailid.isEmpty()){
                    EmailID.setError(null);
                    EmailID.setErrorEnabled(false);
                    if (!password.isEmpty()){
                        Pwd.setError(null);
                        Pwd.setErrorEnabled(false);
                    }else{
                        Pwd.setError("Enter Password");
                    }
                }else{
                    EmailID.setError("Enter Email ID");
                }
            }
        });
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
                finish();
            }
        });



    }
}