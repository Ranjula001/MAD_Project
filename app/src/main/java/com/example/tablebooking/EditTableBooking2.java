package com.example.tablebooking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EditTableBooking2 extends AppCompatActivity {

    EditText txtName , txtConNo, txtEmail,txtNic,txtDate,txtTime,txtGuest;
    Button btnUpdate ;
    Button btnShow;
    DatabaseReference dbRef;
    Table tb;

    private void clearControls(){

        txtName.setText("");
        txtConNo.setText("");
        txtEmail.setText("");
        txtDate.setText("");
        txtTime.setText("");
        txtNic.setText("");
        txtGuest.setText("");
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_table_booking2);

        txtName = findViewById(R.id.EtName);
        txtConNo = findViewById(R.id.EtConNo);
        txtEmail = findViewById(R.id.EtEmail);
        txtDate = findViewById(R.id.EtDate);
        txtTime = findViewById(R.id.EtTime);
        txtNic = findViewById(R.id.EtNic);

        btnUpdate = findViewById(R.id.BtnUpdate);

        getSupportActionBar().setTitle("Update Details");

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openActivity6();

                DatabaseReference updRef = FirebaseDatabase.getInstance().getReference().child("Table");
                updRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChild("tb1")) {
                            try {

                                tb.setName(txtName.getText().toString().trim());
                                tb.setConNo(Integer.parseInt(txtConNo.getText().toString().trim()));
                                tb.setNic(txtNic.getText().toString().trim());
                                tb.setEmail(txtEmail.getText().toString().trim());
                                tb.setDate(Integer.parseInt(txtDate.getText().toString().trim()));
                                tb.setTime(Integer.parseInt(txtTime.getText().toString().trim()));


                                dbRef = FirebaseDatabase.getInstance().getReference().child("Table").child("tb1");
                                dbRef.setValue(tb);
                                clearControls();


                                Toast.makeText(getApplicationContext(), "Data Update Successfully", Toast.LENGTH_SHORT).show();
                            } catch (NumberFormatException e) {
                                Toast.makeText(getApplicationContext(), "Invalid Contact Number", Toast.LENGTH_SHORT).show();
                            }
                        } else
                            Toast.makeText(getApplicationContext(), "No Source to Update", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });



    }


    public void openActivity6() {
        Intent intent = new Intent(this, EditTableBooking.class);
        startActivity(intent);
    }

}

