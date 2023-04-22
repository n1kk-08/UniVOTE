package com.example.univote;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;
import java.util.regex.Pattern;


public class SignUp extends AppCompatActivity {

    private TextInputLayout userName, userPass, userEmail, userBatch;
    private Button SignupBtn;
    Button loginbtn;
    private FirebaseAuth mAuth;
    FirebaseUser mUser;

    public static final String PREFERENCES = "prefKey";
    public static final String Name = "nameKey";
    public static final String Email = "emailKey";
    public static final String Password = "passwordKey";
    public static final String Batch = "batchKey";

    SharedPreferences sharedPreferences;
    String name, password, email, batch;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        sharedPreferences = getApplicationContext().getSharedPreferences(PREFERENCES,MODE_PRIVATE);

        loginbtn = findViewById(R.id.have_acc);
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUp.this,LogIn.class));
                finish();
            }
        });

        userName = findViewById(R.id.fullname);
        userPass =findViewById(R.id.new_signup_password);
        userEmail = findViewById(R.id.enrollment_no);
        userBatch = findViewById(R.id.batchno);
        SignupBtn = findViewById(R.id.signupbtn);
        mAuth = FirebaseAuth.getInstance();
        mUser = FirebaseAuth.getInstance().getCurrentUser();

        SignupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 name  = userName.getEditText().getText().toString().trim();
                 password  = userPass.getEditText().getText().toString().trim();
                 email  = userEmail.getEditText().getText().toString().trim();
                 batch  = userBatch.getEditText().getText().toString().trim();

                if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(password) && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(batch) && Patterns.EMAIL_ADDRESS.matcher(email).matches()){
//                    createUser(email,password);
                    newUser(email,password);
                }else {
                    Toast.makeText(SignUp.this, "Please fill all the credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private void newUser(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    mUser = mAuth.getCurrentUser();
                    UserProfileChangeRequest userProfileChangeRequest = new UserProfileChangeRequest.Builder()
                            .setDisplayName(String.valueOf(userName.getEditText().getText()))
                            .build();
                    mUser.updateProfile(userProfileChangeRequest).addOnCompleteListener(task1 -> {
                        if (task1.isSuccessful()){
                            startActivity(new Intent(SignUp.this, LogIn.class));
                            Toast.makeText(SignUp.this, "User created", Toast.LENGTH_SHORT).show();
                            finish();
                        }else{
                            Toast.makeText(SignUp.this, "Error"+ Objects.requireNonNull(task1.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

                }else{
                    Toast.makeText(SignUp.this, "Error"+ Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

//    private void createUser(String email, String password) {
//        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//            @Override
//            public void onComplete(@NonNull Task<AuthResult> task) {
//                if (task.isSuccessful()){
//                    Toast.makeText(SignUp.this, "User Created", Toast.LENGTH_SHORT).show();
//
//                    FirebaseUser user = mAuth.getCurrentUser();
//                    if (user!= null){
//                        SharedPreferences.Editor pref = sharedPreferences.edit();
//                        pref.putString(Name,name);
//                        pref.putString(Email,email);
//                        pref.putString(Password,password);
//                        pref.putString(Batch,batch);
//                        pref.commit();
//
//                        FirebaseAuth.getInstance().signOut();
//                        startActivity(new Intent(SignUp.this, LogIn.class));
//                        finish();
//                    }
//                }else{
//                    Toast.makeText(SignUp.this, "User not created", Toast.LENGTH_SHORT).show();
//                }
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                Toast.makeText(SignUp.this, "Something went wrong", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }


//    private void verifyEmail() {
//        FirebaseUser user = mAuth.getCurrentUser();
//        if (user != null){
//            SharedPreferences.Editor pref = sharedPreferences.edit();
//            pref.putString(Name,name);
//            pref.putString(Email,email);
//            pref.putString(Password,password);
//            pref.putString(Batch,batch);
//            pref.commit();
//            FirebaseAuth.getInstance().signOut();
//            startActivity(new Intent(SignUp.this, LogIn.class));
//            finish();
//        }
//
//    }
}