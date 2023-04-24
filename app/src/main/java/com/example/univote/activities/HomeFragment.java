package com.example.univote.activities;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.univote.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


public class HomeFragment extends Fragment implements HomeFragmentnew {

    private Button createBtn, startBtn,voteBtn;
    public static final String PREFERENCES = "prefkey";
    SharedPreferences sharedPreferences;
    public static final String IsLogIn = "islogin";
    private TextView nametxt, batchtxt;
    private FirebaseFirestore firebaseFirestore;
    private String uid;
    private String nameUser;
    FirebaseUser mUser;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        firebaseFirestore = FirebaseFirestore.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        nametxt = view.findViewById(R.id.name_profile);
        batchtxt = view.findViewById(R.id.batch_profile);
        createBtn = view.findViewById(R.id.create_btn);
        startBtn = view.findViewById(R.id.candidate_create_voting);
        voteBtn = view.findViewById(R.id.vote_btn);
//        mUser = FirebaseAuth.getInstance().getCurrentUser();
//        nameUser = mUser.getDisplayName();



        sharedPreferences = requireContext().getSharedPreferences(PREFERENCES,MODE_PRIVATE);
        SharedPreferences.Editor pref = sharedPreferences.edit();
        pref.putBoolean(IsLogIn,true);
        pref.commit();

        createBtn.setOnClickListener(v -> startActivity(new Intent(requireContext(),CreateCandidate.class)));
//        if (nameUser.equals("Nikk")){
//            createBtn.setVisibility(View.VISIBLE);
//            startBtn.setVisibility(View.VISIBLE);
//            voteBtn.setVisibility(View.GONE);
//        }else{
//            createBtn.setVisibility(View.GONE);
//            startBtn.setVisibility(View.GONE);
//            voteBtn.setVisibility(View.VISIBLE);
//        }
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),AllCandidateActivity.class));
            }
        });

        voteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),AllCandidateActivity.class));
            }
        });

        firebaseFirestore.collection("Users")
                .document(user.getUid())
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                        if (task.isSuccessful()){
                            String name = task.getResult().getString("name");
                            String batch = task.getResult().getString("batch");

                            if (name.equals("Nikk")){
                                createBtn.setVisibility(View.VISIBLE);
                                startBtn.setVisibility(View.VISIBLE);
                                voteBtn.setVisibility(View.GONE);
                            }else{
                                createBtn.setVisibility(View.GONE);
                                startBtn.setVisibility(View.GONE);
                                voteBtn.setVisibility(View.VISIBLE);
                            }

//                            nametxt.setText(name);
//                            batchtxt.setText(batch);
                        }else {
                            Toast.makeText(requireContext(), "User not found", Toast.LENGTH_SHORT).show();

                        }

                    }
                });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getActivity().getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        int id = item.getItemId();
//        SharedPreferences.Editor pref = sharedPreferences.edit();
//        switch (id) {
//            case R.id.show_result:
//                startActivity(new Intent(getActivity(),ResultActivity.class));
//                return true;
//            case R.id.log_out:
//                FirebaseAuth.getInstance().signOut();
//                pref.putBoolean(IsLogIn, false);
//                pref.commit();
//                Intent intent = new Intent(getActivity(), LogIn.class);
//                startActivity(intent);
//                getActivity().finish();
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }
}