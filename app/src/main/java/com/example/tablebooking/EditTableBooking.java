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

public class EditTableBooking extends AppCompatActivity {

    EditText txtName, txtConNo, txtEmail, txtNic, txtDate, txtTime, txtGuest;
  //  Button btnShow;
    Button btnUpdate;
    DatabaseReference dbRef;
    Table tb;

    private void clearControls() {

        txtName.setText("");
        txtConNo.setText("");
        txtEmail.setText("");
        txtDate.setText("");
        txtTime.setText("");
        txtNic.setText("");
        txtGuest.setText("");
    }

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_table_booking);

        txtName = findViewById(R.id.EtName);
        txtConNo = findViewById(R.id.EtConNo);
        txtEmail = findViewById(R.id.EtEmail);
        txtDate = findViewById(R.id.EtDate);
        txtTime = findViewById(R.id.EtTime);
        txtNic = findViewById(R.id.EtNic);
        txtGuest = findViewById(R.id.EtGuests);

        btnUpdate = findViewById(R.id.BtnUpdate);



    //    btnShow = findViewById(R.id.btnShow);


        getSupportActionBar().setTitle("View Details");


    /*    btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("Table").child("tb1");
                readRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChildren()) {
                            txtName.setText(dataSnapshot.child("name").getValue().toString());
                            txtConNo.setText(dataSnapshot.child("conNo").getValue().toString());
                            txtNic.setText(dataSnapshot.child("nic").getValue().toString());
                            txtEmail.setText(dataSnapshot.child("email").getValue().toString());
                            txtDate.setText(dataSnapshot.child("date").getValue().toString());
                            txtTime.setText(dataSnapshot.child("time").getValue().toString());

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
                DatabaseReference updRef = FirebaseDatabase.getInstance("https://table-booking-a1faf-default-rtdb.firebaseio.com/").getReference().child("Table");
                updRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChild("Table")){
                            try{
                                tb.setName(txtName.getText().toString().trim());
                                tb.setNic(txtNic.getText().toString().trim());
                                tb.setEmail(txtEmail.getText().toString().trim());
                                tb.setDate(Integer.parseInt(txtDate.getText().toString().trim()));
                                tb.setTime(Integer.parseInt(txtTime.getText().toString().trim()));
                                tb.setConNo(Integer.parseInt(txtConNo.getText().toString().trim()));



                                dbRef = FirebaseDatabase.getInstance("https://table-booking-a1faf-default-rtdb.firebaseio.com/").getReference().child("Table").child("tb1");
                                dbRef.child("tb1").setValue(tb);




                                Toast.makeText(getApplicationContext(), "Data Update Successfully", Toast.LENGTH_LONG).show();
                                clearControls();
                            }



                            catch (NumberFormatException e){
                                Toast.makeText(getApplicationContext(), "Invalid Contact Number", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else
                            Toast.makeText(getApplicationContext(), "No Source to Update", Toast.LENGTH_SHORT).show();
                    }



                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {



                    }
                });
            }
        });


   /*     button = (Button) findViewById(R.id.btnUpdate);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity4();
            }

        }); */

        button = (Button) findViewById(R.id.button9);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity5();
            }

        });


    }


   /* public void openActivity4() {
        Intent intent = new Intent(this, EditTableBooking2.class);
        startActivity(intent);
    } */

    public void openActivity5() {
        Intent intent = new Intent(this, BookingSucess.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("Table").child("tb1");
        readRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChildren()) {
                    txtName.setText(dataSnapshot.child("name").getValue().toString());
                    txtConNo.setText(dataSnapshot.child("conNo").getValue().toString());
                    txtNic.setText(dataSnapshot.child("nic").getValue().toString());
                    txtEmail.setText(dataSnapshot.child("email").getValue().toString());
                    txtDate.setText(dataSnapshot.child("date").getValue().toString());
                    txtTime.setText(dataSnapshot.child("time").getValue().toString());

                } else
                    Toast.makeText(getApplicationContext(), "No Source to Display", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}