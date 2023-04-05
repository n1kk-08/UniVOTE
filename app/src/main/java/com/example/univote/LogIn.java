package com.example.univote;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.HashMap;
import java.util.Map;


public class LogIn extends AppCompatActivity {
    Button signupbtn, loginbtn;
    private TextInputLayout userEmail, userPassword;
    private TextView forgettxt;
    private FirebaseAuth mAuth;

    public static final String PREFERENCES = "prefKey";
    public static final String Name = "nameKey";
    public static final String Email = "emailKey";
    public static final String Password = "passwordKey";
    public static final String Batch = "batchKey";

    SharedPreferences sharedPreferences;
    StorageReference reference;
    FirebaseFirestore firebaseFirestore;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //For removing status bar from the page
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.log_in);


        sharedPreferences = getApplicationContext().getSharedPreferences(PREFERENCES,MODE_PRIVATE);
        reference = FirebaseStorage.getInstance().getReference();
        firebaseFirestore = firebaseFirestore.getInstance();

        userEmail = findViewById(R.id.username_text_field_design);
        userPassword = findViewById(R.id.password_input_field);
        loginbtn = findViewById(R.id.Login);

        mAuth = FirebaseAuth.getInstance();

        forgettxt = findViewById(R.id.forgetTxt);

        forgettxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LogIn.this, ForgetPasssword.class));
            }
        });
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = userEmail.getEditText().getText().toString().trim();
                String password = userPassword.getEditText().getText().toString().trim();

                mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            verifyEmail();

                        }else {
                            Toast.makeText(LogIn.this, "User not found", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });



        signupbtn = findViewById(R.id.dont_have_acc);
        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LogIn.this , SignUp.class));
                finish();
            }
        });


    }

    private void verifyEmail() {
        FirebaseUser user = mAuth.getCurrentUser();
        assert user!= null;
        if (user.isEmailVerified()){

            String name = sharedPreferences.getString(Name, null);
            String email = sharedPreferences.getString(Password, null);
            String password = sharedPreferences.getString(Email, null);
            String batch = sharedPreferences.getString(Batch, null);

            // Here firstly we are sending email for verification
            //Now store data in shared preferences if user verify email


            startActivity(new Intent(LogIn.this,Dashboard.class));
            finish();

        }else{
            mAuth.signOut();
            Toast.makeText(this, "Please verify your Email", Toast.LENGTH_SHORT).show();
        }
    }
}