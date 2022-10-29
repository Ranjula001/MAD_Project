package com.example.tablebooking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DeleteBooking extends AppCompatActivity {

    EditText txtName , txtConNo, txtEmail,txtNic,txtDate,txtTime,txtGuest;
    Button btnDelete ;
    DatabaseReference dbRef;
    Table tb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_booking);


            txtName = findViewById(R.id.EtName);
            txtConNo = findViewById(R.id.EtConNo);
            txtEmail = findViewById(R.id.EtEmail);
            txtDate = findViewById(R.id.EtDate);
            txtTime = findViewById(R.id.EtTime);
            txtNic = findViewById(R.id.EtNic);
            txtGuest = findViewById(R.id.EtGuests);


            btnDelete = findViewById(R.id.BtnDelete);

        getSupportActionBar().setTitle("Cancel booking");

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference delRef = FirebaseDatabase.getInstance().getReference().child("Table");
                delRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                         if (dataSnapshot.hasChild("tb1")) {
                            dbRef = FirebaseDatabase.getInstance().getReference().child("Table").child("tb1");
                            dbRef.removeValue();
                            clearControls();
                            Toast.makeText(getApplicationContext(), "Data Deleted Successfully", Toast.LENGTH_SHORT).show();
                        }



                        else
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
}