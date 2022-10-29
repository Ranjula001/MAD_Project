package com.example.tablebooking;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;


public class ReserveTable extends AppCompatActivity {

    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve_table);

        ImageView myImageView = (ImageView) findViewById(R.id.imageView7);
        myImageView.setImageResource(R.drawable.twoperson);

        button = (Button) findViewById(R.id.button3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity8();
            }

        });
    }
    public void openActivity8() {
        Intent intent = new Intent(this, TableBooking.class);
        startActivity(intent);
    }
}
