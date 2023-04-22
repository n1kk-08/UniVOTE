package com.example.univote;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileFragment extends Fragment {

    Button Logout;
    public static final String PREFERENCES = "prefkey";
    public static final String IsLogIn = "islogin";

    FirebaseUser mUser;

    TextView username;
    SharedPreferences sharedPreferences;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_profile, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        sharedPreferences = requireContext().getSharedPreferences(PREFERENCES,MODE_PRIVATE);
        SharedPreferences.Editor pref = sharedPreferences.edit();
        pref.putBoolean(IsLogIn,true);
        pref.commit();

        Logout = view.findViewById(R.id.Logout);
        username = view.findViewById(R.id.name_profile);

        mUser = FirebaseAuth.getInstance().getCurrentUser();
        String nameUser = mUser.getDisplayName();
        username.setText(nameUser);

        Logout.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            pref.putBoolean(IsLogIn,false);
            pref.commit();
            Intent intent = new Intent(getActivity(),LogIn.class);
            startActivity(intent);
            getActivity().finish();
        });

    }
}