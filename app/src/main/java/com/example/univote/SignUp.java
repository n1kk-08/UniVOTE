package com.example.univote;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class SignUp extends AppCompatActivity {

    Button login,signup;
    TextInputLayout EmailID, Fullname, Pwd, ConfirmPwd,Batchno;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        login = findViewById(R.id.Login);
        signup = findViewById(R.id.Signup);
        Fullname = findViewById(R.id.fullname);
        EmailID = findViewById((R.id.enrollment_no));
        Pwd = findViewById(R.id.new_signup_password);
        ConfirmPwd =findViewById(R.id.new_signup_confirm_password);
        Batchno = findViewById(R.id.batchno);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailid = EmailID.getEditText().getText().toString();
                String password = Pwd.getEditText().getText().toString();
                String confirmpassword = ConfirmPwd.getEditText().getText().toString();
                String batch = Batchno.getEditText().getText().toString();
                String fullname = Fullname.getEditText().getText().toString();
                if(!fullname.isEmpty()){
                    Fullname.setError(null);
                    Fullname.setErrorEnabled(false);
                    if (!emailid.isEmpty()){
                        EmailID.setError(null);
                        EmailID.setErrorEnabled(false);
                        if (!batch.isEmpty()){
                            Batchno.setError(null);
                            Batchno.setErrorEnabled(false);
                            if (!password.isEmpty()){
                                Pwd.setError(null);
                                Pwd.setErrorEnabled(false);
                                if (!confirmpassword.isEmpty()){
                                    ConfirmPwd.setError(null);
                                    ConfirmPwd.setErrorEnabled(false);
                                }else{
                                    ConfirmPwd.setError("Enter Password Again");
                                }
                            }else{
                                Pwd.setError("Enter Strong Password");
                            }
                        }else{
                            Batchno.setError("Enter Batch no.");
                        }
                    }else{
                        EmailID.setError("Enter email");
                    }
                }else{
                    Fullname.setError("Enter Full Name");
                }
            }
        });
    login.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(SignUp.this, "Log In", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(SignUp.this, MainActivity.class));
            finish();
        }
    });
    }
}