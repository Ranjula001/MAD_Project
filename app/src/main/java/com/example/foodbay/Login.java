package com.example.foodbay;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    Button btnSignIn;
    //TextView fgtPW;
    ProgressBar loginpr;
    TextView crtAcc;
    EditText etEmail;
    EditText etPW;

    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnSignIn = findViewById(R.id.signIn);
        //fgtPW = (TextView) findViewById(R.id.linkForgotPW);
        crtAcc = findViewById(R.id.linkCreateAcc);
        etEmail = findViewById(R.id.EmailLogin);
        etPW = findViewById(R.id.PWLogin);
        loginpr = findViewById(R.id.LoginProgress);

        fAuth = FirebaseAuth.getInstance();

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = etEmail.getText().toString().trim();
                String password = etPW.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    etEmail.setError("required : Please enter registered email !");
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    etPW.setError("required : Please enter registered password !");
                    return;
                }

                loginpr.setVisibility(View.VISIBLE);

                fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Login.this, "Logging in", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),Menu.class));
                        }
                        else{
                            Toast.makeText(Login.this, "Authenticate error !", Toast.LENGTH_SHORT).show();
                            loginpr.setVisibility(View.GONE);
                        }
                    }
                });

            }
        });

       crtAcc.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               startActivity(new Intent(getApplicationContext(),Register.class));
           }
       });


    }
}
