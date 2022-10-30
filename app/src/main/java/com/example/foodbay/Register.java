package com.example.foodbay;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.core.Tag;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {

    public static final String TAG = "TAG";
    EditText etUsername, etEmail, etPassword, etConfirmpw, etContact;
    Button btnReg;
    Button btnSign;
    User user;
    DatabaseReference dbRef;
    FirebaseDatabase rootNode;
    FirebaseAuth fAuth;
    FirebaseFirestore fstore;
    String userID;

    private DatabaseReference mDatabase;

    public void clearinputs(){
        etUsername.setText("");
        etEmail.setText("");
        etPassword.setText("");
        etConfirmpw.setText("");
        etContact.setText("");
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        etUsername = findViewById(R.id.username);
        etEmail = findViewById(R.id.email);
        etPassword = findViewById(R.id.password);
        etConfirmpw = findViewById(R.id.confirmpw);
        etContact = findViewById(R.id.contact);
        btnReg = findViewById(R.id.register);
        btnSign = findViewById(R.id.regbypass);

        fAuth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();

        if (fAuth.getCurrentUser() != null){
            signed();
            finish();
        }

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = etUsername.getText().toString();
                String email = etEmail.getText().toString().trim();
                String password = etPassword.getText().toString().trim();
                String confirm = etConfirmpw.getText().toString().trim();
                String phone = etContact.getText().toString();

                if(!validateUserName() | !validateEmail() | !validatePassword()){
                    Toast.makeText(Register.this, "Sign up failed", Toast.LENGTH_SHORT).show();
                }
                else if(!password.equals(confirm)){
                    Toast.makeText(Register.this, "Confirm password failed !", Toast.LENGTH_SHORT).show();
                }
                else{

                    fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(Register.this, "User registered successfully", Toast.LENGTH_SHORT).show();
                                userID = fAuth.getCurrentUser().getUid(); //for retrive the user id of currently registered user
                                DocumentReference documentReference = fstore.collection("users").document(userID);
                                Map<String,Object> user = new HashMap<>();
                                user.put("Username",userName);
                                user.put("Email",email);
                                user.put("Phone",phone);
                                documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Log.d(TAG, "onSuccess: user Profile is created for "+ userID);
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Log.d(TAG, "onFailure: " + e.toString());
                                    }
                                });
                                signed();
                            }
                            else {
                                Toast.makeText(Register.this, "Registration failed ! "+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }

            }
        });



        //user = new User();

        /*btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //dbRef = FirebaseDatabase.getInstance("https://foodbay-9fac6-default-rtdb.firebaseio.com/").getReference().child("User");
                //dbRef = rootNode.getReference("Users");

                final String username = etUsername.getText().toString();
                final String email = etEmail.getText().toString();
                final String password = etPassword.getText().toString();
                final String confirmpw = etConfirmpw.getText().toString();
                final Integer contact = Integer.valueOf(etContact.getText().toString());

                if(!validateUserName() | !validateEmail() | !validatePassword()){
                    Toast.makeText(Register.this, "Sign up failed", Toast.LENGTH_SHORT).show();
                }
                else if(!password.equals(confirmpw)){
                    Toast.makeText(Register.this, "Confirm password failed !", Toast.LENGTH_SHORT).show();
                }
                else{
                    /*user.setUsername(etUsername.getText().toString().trim());
                    user.setEmail(etEmail.getText().toString().trim());
                    user.setPassword(etPassword.getText().toString().trim());
                    user.setConfirmpw(etConfirmpw.getText().toString().trim());
                    user.getContact(etContact.getText().toString().trim());*/

                   /* mDatabase = FirebaseDatabase.getInstance("https://foodbay-9fac6-default-rtdb.firebaseio.com/").getReference();

                    User user = new User(username,email,password,confirmpw,contact);

                    String userId = new String();
                    mDatabase.child("users").child(userId).setValue(user);

                    signed();

                    System.out.println(userId);
                    //dbRef.push().setValue(user);
                    /*String userid = user.getId();

                    User members = new User(userid,username,email,password,confirmpw,contact);
                    dbRef.child(userid).setValue(members);

                    System.out.println(members);*/
                    //Toast.makeText(getApplicationContext(), "User registered Successfully", Toast.LENGTH_SHORT).show();
                    //login();
                    /*firebaseAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                        //assert user != null;
                                        String userid = user.getUid();

                                        User members = new User(userid,username,email,password,confirmpw,contact);
                                        dbRef.child(userid).setValue(members);

                                        System.out.println(members);
                                        Toast.makeText(Register.this, "Registration successfully", Toast.LENGTH_SHORT).show();
                                        clearinputs();
                                        login();
                                    } else {
                                        Toast.makeText(Register.this, "Registration failed", Toast.LENGTH_SHORT).show();

                                    }

                                }
                            });

                }

            }
        });*/



        btnSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

    }
    /*public void writeNewUser(String userId, String username, String email, String password, String confirmpw, Integer contact) {
        User user = new User(username,email,password,confirmpw,contact);

        mDatabase.child("users").child(userId).setValue(user);
    }*/



    private  boolean validateUserName() {
        String val = etUsername.getEditableText().toString().trim();
        String checkspaces = "\\A\\w{2,20}\\z";

        if (val.isEmpty()){
            etUsername.setError("Required !");
            return false;
        }
        else if(val.length()>=15){
            etUsername.setError("User name is too large!");
            return false;
        }
        else if(!val.matches(checkspaces)){
            etUsername.setError("No white spaces are allowed!");
            return false;
        }
        else{
            etUsername.setError(null);
            etUsername.setEnabled(false);
            return true;
        }
    }
    private  boolean validateEmail() {
        String val = etEmail.getEditableText().toString().trim();
        String checkEmail = "[a-zA-Z0-9._-]+@[a-z].+[a-z]+\\.+[a-z]+";

        if (val.isEmpty()){
            etEmail.setError("Required !");
            return false;
        }
        else if(!val.matches(checkEmail)){
            etEmail.setError("Invalid Email!");
            return false;
        }
        else{
            etEmail.setError(null);
            etEmail.setEnabled(false);
            return true;
        }
    }

    private  boolean validatePassword() {
        String val = etPassword.getEditableText().toString().trim();
        String checkpassword = "^"+
                //"(?=.*[0-9])"   +
                //"(?=.*[a-z])"   +
                //"(?=.*[A-Z])"   +
                "(?=.*[a-zA-Z])"+
                "(?=.*[@#$%^&+=])"+
                "(?=\\S+$)"+
                ".{2,}"+
                "$";

        if (val.isEmpty()){
            etPassword.setError("Required !");
            return false;
        }
        else if(!val.matches(checkpassword)){
            etPassword.setError("Password should contain 4 characters!");
            return false;
        }
        else{
            etPassword.setError(null);
            return true;
        }
    }



    public void login(){
        Intent log = new Intent(this,Login.class);
        startActivity(log);
    }

    public void signed(){
        Intent signed = new Intent(this,Menu.class);
        startActivity(signed);
    }

}