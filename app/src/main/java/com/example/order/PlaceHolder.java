package com.example.order;

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

public class PlaceHolder extends AppCompatActivity {
    private Button button;


    EditText txtName , txtAdd, txtConNo, txtEmail;
    Button btnSave ;
    DatabaseReference dbRef;
    Food fd;

    private void clearControls(){

        txtName.setText("");
        txtAdd.setText("");
        txtConNo.setText("");
        txtEmail.setText("");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_holder);

        txtName = findViewById(R.id.EtName);
        txtAdd = findViewById(R.id.EtAddress);
        txtConNo = findViewById(R.id.EtConNo);
        txtEmail = findViewById(R.id.EtEmail);

        btnSave = findViewById(R.id.BtnSave);



        fd = new Food();

        getSupportActionBar().setTitle("ADD ORDER");

        btnSave.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View view) {

                dbRef = FirebaseDatabase.getInstance("https://order-66e6f-default-rtdb.firebaseio.com/").getReference().child("Food");



                try {

                    if (TextUtils.isEmpty(txtName.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter a Name", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(txtAdd.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter an Address", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(txtConNo.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter an Contact No", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(txtEmail.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter an Email", Toast.LENGTH_SHORT).show();



                    else{
                        fd.setName(txtName.getText().toString().trim());
                        fd.setAddress(txtAdd.getText().toString().trim());
                        fd.setConNo(Integer.parseInt(txtConNo.getText().toString().trim()));
                        fd.setEmail(txtEmail.getText().toString().trim());


                        //dbRef.push().setValue(fd);


                        dbRef.child("fd1").setValue(fd);



                        Toast.makeText(getApplicationContext(), "Data Saved Successfully", Toast.LENGTH_SHORT).show();
                        clearControls();
                        Intent intent = new Intent(getApplicationContext(), OrderSuccessful.class);
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