package com.example.foodbay;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class Menu extends AppCompatActivity {

    ImageButton btnTable, btnFood, btnInquiry, btnProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        btnTable =  findViewById(R.id.bookTable);
        btnFood =  findViewById(R.id.orderFood);
        btnInquiry =  findViewById(R.id.inquiry);
        btnProfile =  findViewById(R.id.profile);



        btnFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),PaymentGate.class));
            }
        });


        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getApplicationContext(),UserProfile.class));

            }
        });


    }

    /*public void playAct(){
        Intent act = new Intent(this, PaymentGate.class);
        startActivity(act);
    }*/

    /*public void userProf(){
        Intent user = new Intent(this, UserProfile.class);
        startActivity(user);
    }*/

}