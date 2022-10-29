package com.example.tablebooking;

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

import java.nio.channels.UnresolvedAddressException;

public class TableBooking extends AppCompatActivity {

    EditText txtName , txtConNo, txtEmail,txtNic,txtDate,txtTime,txtGuest;
    Button btnSave ;
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
        setContentView(R.layout.activity_table_booking);

        txtName = findViewById(R.id.EtName);
        txtConNo = findViewById(R.id.EtConNo);
        txtEmail = findViewById(R.id.EtEmail);
        txtDate = findViewById(R.id.EtDate);
        txtTime = findViewById(R.id.EtTime);
        txtNic = findViewById(R.id.EtNic);
        txtGuest = findViewById(R.id.EtGuests);

        btnSave = findViewById(R.id.BtnUpdate);


        tb = new Table();

        btnSave.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View view) {



                dbRef = FirebaseDatabase.getInstance("https://table-booking-a1faf-default-rtdb.firebaseio.com/").getReference().child("Table");



                try {

                    if (TextUtils.isEmpty(txtName.getText().toString()))

                        txtName.setError( " name is required!" );
                       // Toast.makeText(getBaseContext(), "Please enter an name", Toast.LENGTH_SHORT).show();

                    else if (TextUtils.isEmpty(txtEmail.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter an Email", Toast.LENGTH_SHORT).show();

                    else if (TextUtils.isEmpty(txtNic.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter an Nic", Toast.LENGTH_SHORT).show();

                    else if (TextUtils.isEmpty(txtDate.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter an Date", Toast.LENGTH_SHORT).show();

                    else if (TextUtils.isEmpty(txtTime.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter an address", Toast.LENGTH_SHORT).show();

                    else if (TextUtils.isEmpty(txtGuest.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter an address", Toast.LENGTH_SHORT).show();

                    else{
                        tb.setName(txtName.getText().toString().trim());
                        tb.setEmail(txtEmail.getText().toString().trim());
                        tb.setConNo(Integer.parseInt(txtConNo.getText().toString().trim()));
                        tb.setNic(txtNic.getText().toString().trim());
                        tb.setDate(Integer.parseInt(txtDate.getText().toString().trim()));
                        tb.setTime(Integer.parseInt(txtTime.getText().toString().trim()));
                        tb.setGuest(Integer.parseInt(txtGuest.getText().toString().trim()));



                    //    dbRef.push().setValue(tb);

                        dbRef.child("tb1").setValue(tb);



                        Toast.makeText(getApplicationContext(), "Data Saved Successfully", Toast.LENGTH_LONG).show();
                        clearControls();


                            Intent intent = new Intent(getApplicationContext(), EditTableBooking.class);
                            startActivity(intent);

                    }



                }



                catch (NumberFormatException e){
                    Toast.makeText(getApplicationContext(), "Invalid Contact Number", Toast.LENGTH_SHORT).show();
                }



            }




        });


      /*  btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("Table").child("tb1");
                readRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChildren()) {
                            txtName.setText(dataSnapshot.child("name").getValue().toString());
                            txtConNo.setText(dataSnapshot.child("conNo").getValue().toString());
                            txtEmail.setText(dataSnapshot.child("email").getValue().toString());
                            txtDate.setText(dataSnapshot.child("date").getValue().toString());
                            txtTime.setText(dataSnapshot.child("time").getValue().toString());
                            txtNic.setText(dataSnapshot.child("nic").getValue().toString());
                            txtGuest.setText(dataSnapshot.child("name").getValue().toString());
                        }



                        else
                            Toast.makeText(getApplicationContext(), "No Source to Display", Toast.LENGTH_SHORT).show();
                    }



                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {



                    }
                });
            }
        }); */

    }

}