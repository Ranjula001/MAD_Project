package com.example.inqury;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ContactUs extends AppCompatActivity {

    EditText txtName , txtConNo, txtEmail,txtSub,txtContent;
    Button btnSave ;
    DatabaseReference dbRef;
    Inquiry iq;


    private void clearControls(){

        txtName.setText("");
        txtConNo.setText("");
        txtEmail.setText("");
        txtSub.setText("");
        txtContent.setText("");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        txtName = findViewById(R.id.EtName);
        txtConNo = findViewById(R.id.EtConNo);
        txtEmail = findViewById(R.id.EtEmail);
        txtSub = findViewById(R.id.EtSubject);
        txtContent= findViewById(R.id.EtContent);

        btnSave = findViewById(R.id.BtnSave);

        iq = new Inquiry();
      /*  iq = new Inquiry();

        txtName = findViewById(R.id.EtName);
        txtConNo = findViewById(R.id.EtConNo);
        txtEmail = findViewById(R.id.EtEmail);
        txtSub = findViewById(R.id.EtSubject);
        txtContent= findViewById(R.id.EtContent);

        btnSave = findViewById(R.id.BtnSave); */




        getSupportActionBar().setTitle("ADD Inquiry");



        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                dbRef = FirebaseDatabase.getInstance("https://inqury-61789-default-rtdb.firebaseio.com/").getReference().child("Inquiry");



                try {

                    if (TextUtils.isEmpty(txtName.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter a name", Toast.LENGTH_SHORT).show();

                    else if (TextUtils.isEmpty(txtEmail.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter an Email", Toast.LENGTH_SHORT).show();

                    else if (TextUtils.isEmpty(txtSub.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter an Subject", Toast.LENGTH_SHORT).show();

                    else if (TextUtils.isEmpty(txtContent.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter an Content", Toast.LENGTH_SHORT).show();



                    else{
                        iq.setName(txtName.getText().toString().trim());
                        iq.setEmail(txtEmail.getText().toString().trim());
                        iq.setConNo(Integer.parseInt(txtConNo.getText().toString().trim()));
                        iq.setSubject(txtSub.getText().toString().trim());
                        iq.setContent(txtContent.getText().toString().trim());



                       // dbRef.push().setValue(iq);

                        dbRef.child("iq1").setValue(iq);



                        Toast.makeText(getApplicationContext(), "Data Saved Successfully", Toast.LENGTH_LONG).show();
                        clearControls();

                        Intent intent = new Intent(getApplicationContext(), Succuess.class);
                        startActivity(intent);
                    }



                }



                catch (NumberFormatException e){
                    Toast.makeText(getApplicationContext(), "Invalid Contact Number", Toast.LENGTH_SHORT).show();
 }

            }
        });
    }



}