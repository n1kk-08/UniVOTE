package com.example.univote;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

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
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.log_in);


        sharedPreferences = getApplicationContext().getSharedPreferences(PREFERENCES,MODE_PRIVATE);
        reference = FirebaseStorage.getInstance().getReference();
        firebaseFirestore = FirebaseFirestore.getInstance();

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
                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)){
                    Toast.makeText(LogIn.this, "Please fill all credentials", Toast.LENGTH_SHORT).show();
                }else{
                    mAuth.signInWithEmailAndPassword(email,password)
                            .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                @Override
                                public void onSuccess(AuthResult authResult) {
                                    Toast.makeText(LogIn.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(LogIn.this, Dashboard.class));
                                    finish();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(LogIn.this, "Email and password is wrong", Toast.LENGTH_SHORT).show();
                                }
                            });
                }
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

//    private void verifyEmail() {
//        FirebaseUser user = mAuth.getCurrentUser();
//        assert user!= null;
//        String name = sharedPreferences.getString(Name, null);
//        String email = sharedPreferences.getString(Password, null);
//        String password = sharedPreferences.getString(Email, null);
//        String batch = sharedPreferences.getString(Batch, null);
//
//        if (name != null && email != null && password != null && batch!= null) {
//            String uid = mAuth.getUid();
//            Map<String, String> map = new HashMap<>();
//            map.put("name", name);
//            map.put("email", email);
//            map.put("password", password);
//            map.put("batch", batch);
//            map.put("uid", uid);
//            firebaseFirestore.collection("Users")
//                    .document(uid)
//                    .set(map)
//                    .addOnCompleteListener(new OnCompleteListener<Void>() {
//                        @Override
//                        public void onComplete(@NonNull Task<Void> task) {
//                            if (task.isSuccessful()) {
//
//                            } else {
//                                mAuth.signOut();
//                                Toast.makeText(LogIn.this, "Data not stored", Toast.LENGTH_SHORT).show();
//                            }
//                        }
//                    });
////            StorageReference imagePath = reference.child("Storage_reference").child(uid);
////            imagePath.putFile(Uri.parse(name)).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
////                @Override
////                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
////                    if (task.isSuccessful()) {
////                        imagePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
////                            @Override
////                            public void onSuccess(Uri uri) {
//
//                            }
////                        });
////                    } else {
////                        Toast.makeText(LogIn.this, "" + task.getException(), Toast.LENGTH_SHORT).show();
////                    }
////                }
////            });
////        }
////        }else{
////            Toast.makeText(LogIn.this, "User data not found", Toast.LENGTH_SHORT).show();
////        }
//    }
}