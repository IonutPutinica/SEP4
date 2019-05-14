package com.andteam.sep4greenhouse.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;

import com.andteam.sep4greenhouse.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class ModifyAccountFragment extends Fragment {

    EditText email;
    EditText password;
    Button saveNewCredentials;
    FloatingActionButton deleteAccount;
    ScrollView scrollmodifacc;
    private FirebaseUser user;


    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_modifyaccount, container, false);
        scrollmodifacc= (ScrollView)view.findViewById(R.id.scrollmodifacc);


        email = view.findViewById(R.id.newEmail_input);
        password = view.findViewById(R.id.newPassword_input);

        user = FirebaseAuth.getInstance().getCurrentUser();

        //email.setText(user.getEmail().toString());
        saveNewCredentials = view.findViewById(R.id.button_newcredsave);
        saveNewCredentials.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.updateEmail(email.getText().toString());
                user.updatePassword(password.getText().toString());
                Intent login = new Intent(getContext(), MainActivity.class);
                startActivity(login);
            }
        });

        deleteAccount = view.findViewById(R.id.delete_profile);
        deleteAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.delete();
                Intent login = new Intent(getContext(), MainActivity.class);
                startActivity(login);
            }
        });
        return view;
    }

}