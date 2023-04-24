package com.example.univote.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.univote.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.HashMap;
import java.util.Map;

public class CreateCandidate extends AppCompatActivity {

    private EditText candidateName;
    private Spinner candidateSpinner;
    private String[] candPost = {"Batch-Representative","Class-Representative"};
    private Button submitbtn;
    private Uri mainUri = null;
    StorageReference reference;
    FirebaseFirestore firebaseFirestore;

//    DatabaseReference reference;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_candidate);

        reference = FirebaseStorage.getInstance().getReference();
        firebaseFirestore = firebaseFirestore.getInstance();

        candidateName = findViewById(R.id.candidate_name);
        submitbtn = findViewById(R.id.candidate_submit_btn);
        candidateSpinner = findViewById(R.id.candidate_spinner);
//        reference = FirebaseDatabase.getInstance().getReference("Candidate");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line,candPost);
        candidateSpinner.setAdapter(adapter);

        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = candidateName.getText().toString().trim();
                String post = candidateSpinner.getSelectedItem().toString();

                if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(post)){

//                    createCandidate(name,post);

                    String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                    Map<String,Object> map = new HashMap<>();
                    map.put("name",name);
                    map.put("post",post);
                    map.put("timestamp", FieldValue.serverTimestamp());
                    firebaseFirestore.collection("Candidate")
                            .add(map)
                            .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                                @Override
                                public void onComplete(@NonNull Task<DocumentReference> task) {
                                    if (task.isSuccessful()){
                                        startActivity(new Intent(CreateCandidate.this,Dashboard.class));
                                        finish();
                                    }else{
                                        Toast.makeText(CreateCandidate.this, "Data not stored", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }else{
                    Toast.makeText(CreateCandidate.this, "Enter details", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

//    private void createCandidate(String name, String post) {
//        Candidate_data candidateData = new Candidate_data(name,post);
//        reference.child(Objects.requireNonNull("new Candidate")).setValue(candidateData);
//    }
}