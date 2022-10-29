package com.example.inqury;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.NumberFormat;

public class Inquiry02 extends AppCompatActivity {

    EditText txtName, txtConNo, txtEmail, txtSub, txtContent;
    Button btnShow;
    Button btnDelete;
    Button btnUpdate;
    DatabaseReference dbRef;
    Inquiry iq;

    String searchID;


    private void clearControls() {

        txtName.setText("");
        txtConNo.setText("");
        txtEmail.setText("");
        txtSub.setText("");
        txtContent.setText("");
    }


    private Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inquiry02);

        txtName = findViewById(R.id.EtName);
        txtConNo = findViewById(R.id.EtConNo);
        txtEmail = findViewById(R.id.EtEmail);
        txtSub = findViewById(R.id.EtSubject);
        txtContent = findViewById(R.id.EtContent);

        btnDelete = findViewById(R.id.BtnDelete);

        //btnShow = findViewById(R.id.btnShow);

        btnUpdate = findViewById(R.id.BtnUpdate);


        getSupportActionBar().setTitle("View Inquiry");

  /*
        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("Inquiry").child("iq1");
                readRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChildren()) {
                            txtName.setText(dataSnapshot.child("name").getValue().toString());
                            txtConNo.setText(dataSnapshot.child("conNo").getValue().toString());
                            txtEmail.setText(dataSnapshot.child("email").getValue().toString());
                            txtSub.setText(dataSnapshot.child("subject").getValue().toString());
                            txtContent.setText(dataSnapshot.child("content").getValue().toString());

                        } else
                            Toast.makeText(getApplicationContext(), "No Source to Display", Toast.LENGTH_SHORT).show();
                    }


                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {


                    }
                });
            }
        }); */


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference updRef = FirebaseDatabase.getInstance().getReference().child("Inquiry");
                updRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChild("Inquiry")) {
                            try {
                                iq.setName(txtName.getText().toString().trim());
                                iq.setConNo(Integer.parseInt(txtConNo.getText().toString().trim()));
                                iq.setEmail(txtEmail.getText().toString().trim());
                                iq.setSubject(txtSub.getText().toString().trim());
                                iq.setContent(txtContent.getText().toString().trim());


                                dbRef = FirebaseDatabase.getInstance().getReference().child("Inquiry").child("iq1");
                                dbRef.setValue(iq);
                                clearControls();

                                Toast.makeText(getApplicationContext(), "Data updated successfully", Toast.LENGTH_SHORT).show();
                            } catch (NumberFormatException e) {
                                Toast.makeText(getApplicationContext(), "Invalid data", Toast.LENGTH_SHORT).show();
                            }

                        } else
                            Toast.makeText(getApplicationContext(), "No source to update", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Log.d("TAG", databaseError.getMessage());
                    }
                });
            }
        });


        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                DatabaseReference delRef = FirebaseDatabase.getInstance().getReference().child("Inquiry");
                delRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                        if (dataSnapshot.hasChild("iq1")) {
                            dbRef = FirebaseDatabase.getInstance().getReference().child("Inquiry").child("iq1");
                            dbRef.removeValue();
                            clearControls();
                            Toast.makeText(getApplicationContext(), "Data Deleted Successfully", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(getApplicationContext(), DeleteSuccess.class);
                            startActivity(intent);


                        } else
                            Toast.makeText(getApplicationContext(), "No Source to Delete", Toast.LENGTH_SHORT).show();


                    }

                    private void clearControls() {
                    }


                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {


                    }
                });
            }

        });


    }


    @Override
    protected void onResume() {
        super.onResume();
        DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("Inquiry").child("iq1");
        readRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChildren()) {
                    txtName.setText(dataSnapshot.child("name").getValue().toString());
                    txtConNo.setText(dataSnapshot.child("conNo").getValue().toString());
                    txtEmail.setText(dataSnapshot.child("email").getValue().toString());
                    txtSub.setText(dataSnapshot.child("subject").getValue().toString());
                    txtContent.setText(dataSnapshot.child("content").getValue().toString());

                } else
                    Toast.makeText(getApplicationContext(), "No Source to Display", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}


