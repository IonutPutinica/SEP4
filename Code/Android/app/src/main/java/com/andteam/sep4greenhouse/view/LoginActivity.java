package com.andteam.sep4greenhouse.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Toast;

import com.andteam.sep4greenhouse.R;
import com.andteam.sep4greenhouse.model.UserProfileDTO;
import com.andteam.sep4greenhouse.repository.UserRepository;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {


    EditText username;
    EditText password;
    Button btnLogin;
    Button btnRegister;
    FirebaseAuth auth;
    ScrollView scrolllogin;

    public final static String KEY_USERNAME = "username";

    // Do observer on loginViewModel

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        scrolllogin= (ScrollView)findViewById(R.id.scrolllogin);

        auth = FirebaseAuth.getInstance();
        username = findViewById(R.id.username_login_input);
        password = findViewById(R.id.password_login_input);

        btnRegister = findViewById(R.id.button_register);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onUserRegisterClick();
            }
        });

        //firebase part
        btnRegister = (Button) findViewById(R.id.button_register);
        btnLogin = (Button) findViewById(R.id.button_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.signInWithEmailAndPassword(username.getText().toString(), password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            onUserLoginClick();
                        } else {
                            makeToastOnFailiure();
                        }
                    }
                });
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onUserRegisterClick();
            }
        });
        autoFillLogin();
    }

    private void makeToastOnFailiure() {
        Toast.makeText(this, "Operation unsuccessful! Error.", Toast.LENGTH_LONG).show();
    }

    private void onUserRegisterClick() {
        UserRepository.getInstance().setEmail(username.getText().toString());
        Intent register = new Intent(this, RegisterActivity.class);
        startActivity(register);
    }

    private void onUserLoginClick() {
        UserRepository.getInstance().setEmail(username.getText().toString());
        Intent login = new Intent(this, MainActivity.class);
        startActivity(login);
    }

    private void passEmail() {
        if (username != null) {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(KEY_USERNAME, username.getText().toString());
            sendIntent.setType("text/plain");
            startActivity(sendIntent);
        } else {
            Toast.makeText(this, "Could not parse username!", Toast.LENGTH_LONG).show();
        }
    }

    private void autoFillLogin(){
        username.setText("abcdef@gmail.com");
        password.setText("Abcdef123");
    }
}