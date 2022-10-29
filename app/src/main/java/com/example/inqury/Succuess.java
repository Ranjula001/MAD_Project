package com.example.inqury;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Succuess extends AppCompatActivity {
    private Button button01;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_succuess);

        getSupportActionBar().setTitle("Success");


        ImageView imageView3 = (ImageView) findViewById(R.id.imageView2);
        imageView3.setImageResource(R.drawable.microsoftteams_image__3_);

        button01 = (Button) findViewById(R.id.button9);
        button01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity6();
            }
        });
    }
    public void openActivity6() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}