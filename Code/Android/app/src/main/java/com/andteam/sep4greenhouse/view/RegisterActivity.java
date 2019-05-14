package com.andteam.sep4greenhouse.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.andteam.sep4greenhouse.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    EditText username;
    EditText password;
    Button buttonSubmitRegister;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);
        auth = FirebaseAuth.getInstance();
        username = findViewById(R.id.email_register_input);
        password = findViewById(R.id.password_register_input);
        buttonSubmitRegister = findViewById(R.id.button_submit_register);
        buttonSubmitRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.createUserWithEmailAndPassword(username.getText().toString(), password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            openMainActivity();
                        } else {
                            makeToastOnFailiure();
                        }
                    }
                });
            }
        });

    }

    private void makeToast() {
        Toast.makeText(this, username.getText().toString() + " " + password.getText().toString(), Toast.LENGTH_LONG).show();
    }

    private void openMainActivity() {
        Intent mainActivity = new Intent(this, MainActivity.class);
        startActivity(mainActivity);
    }

    private void makeToastOnFailiure() {
        Toast.makeText(this, "Operation unsuccessful! Error.", Toast.LENGTH_LONG).show();
    }
}
