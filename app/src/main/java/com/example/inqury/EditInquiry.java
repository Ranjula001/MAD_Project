package com.example.inqury;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EditInquiry extends AppCompatActivity {
    private Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_inquiry);

        getSupportActionBar().setTitle("Edit Inquiry");

        button = findViewById(R.id.BtnUpdate);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity5();
            }
        });



    }

    public void openActivity5() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}