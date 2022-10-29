package com.example.order;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ViewOrder extends AppCompatActivity {


    EditText txtName , txtAdd, txtConNo, txtEmail;

    Button btnDelete;
    Button btnUpdate;
    DatabaseReference dbRef;
    Food fd;

    private void clearControls(){

        txtName.setText("");
        txtAdd.setText("");
        txtConNo.setText("");
        txtEmail.setText("");
    }

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_order);

        txtName = findViewById(R.id.EtName);
        txtAdd = findViewById(R.id.EtAddress);
        txtConNo = findViewById(R.id.EtConNo);
        txtEmail = findViewById(R.id.EtEmail);



        btnDelete = findViewById(R.id.BtnDelete);

        btnUpdate = findViewById(R.id.BtnUpdate);

        getSupportActionBar().setTitle("View Order");






        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference delRef = FirebaseDatabase.getInstance().getReference().child("Food");
                delRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                        if (dataSnapshot.hasChild("fd1")) {
                            dbRef = FirebaseDatabase.getInstance().getReference().child("Food").child("fd1");
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


        ImageView imageView3 = (ImageView) findViewById(R.id.imageView3);
        imageView3.setImageResource(R.drawable.friedrice);




    }

    protected void onResume() {
        super.onResume();
        DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("Food").child("fd1");
        readRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChildren()) {
                    txtName.setText(dataSnapshot.child("name").getValue().toString());
                    txtAdd.setText(dataSnapshot.child("address").getValue().toString());
                    txtConNo.setText(dataSnapshot.child("conNo").getValue().toString());
                    txtEmail.setText(dataSnapshot.child("email").getValue().toString());

                } else
                    Toast.makeText(getApplicationContext(), "No Source to Display", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatabaseReference updRef = FirebaseDatabase.getInstance().getReference().child("Food");
                updRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChild("Food")) {
                            try {

                                fd.setName(txtName.getText().toString().trim());
                                fd.setAddress(txtAdd.getText().toString().trim());
                                fd.setConNo(Integer.parseInt(txtConNo.getText().toString().trim()));
                                fd.setEmail(txtEmail.getText().toString().trim());


                                dbRef = FirebaseDatabase.getInstance().getReference().child("Food").child("fd1");
                                dbRef.setValue(fd);
                                clearControls();


                                Toast.makeText(getApplicationContext(), "Data Update Successfully", Toast.LENGTH_SHORT).show();
                            } catch (NumberFormatException e) {
                                Toast.makeText(getApplicationContext(), "Invalid Contact Number", Toast.LENGTH_SHORT).show();
                            }
                        } else
                            Toast.makeText(getApplicationContext(), "Update Successfully", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

    }



}