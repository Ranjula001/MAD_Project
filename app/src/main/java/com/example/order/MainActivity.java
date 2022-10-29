package com.example.order;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


public class MainActivity extends AppCompatActivity {
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("Menu");

        ImageView myImageView = (ImageView) findViewById(R.id.my_image_view);
        myImageView.setImageResource(R.drawable.friedrice);

        ImageView myImageView2 = (ImageView) findViewById(R.id.my_image_view2);
        myImageView2.setImageResource(R.drawable.noodles);

        ImageView myImageView3 = (ImageView) findViewById(R.id.my_image_view3);
        myImageView3.setImageResource(R.drawable.nasigooran);

        ImageView myImageView4 = (ImageView) findViewById(R.id.my_image_view4);
        myImageView4.setImageResource(R.drawable.biriyani);

        ImageView myImageView5 = (ImageView) findViewById(R.id.my_image_view5);
        myImageView5.setImageResource(R.drawable.sandwich);

        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity2();
            }
        });

        button = (Button) findViewById(R.id.button3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity2();
            }
        });

        button = (Button) findViewById(R.id.button4);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity2();
            }
        });

        button = (Button) findViewById(R.id.button5);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity2();
            }
        });

        button = (Button) findViewById(R.id.button6);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity2();
            }
        });
    }

    public void openActivity2() {
        Intent intent = new Intent(this, OrderDetails.class);
        startActivity(intent);
    }
}