package com.example.inqury;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class DeleteSuccess extends AppCompatActivity {
     private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_success);

        getSupportActionBar().setTitle("Delete");


        ImageView imageView3 = (ImageView) findViewById(R.id.del);
        imageView3.setImageResource(R.drawable.delete);

        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
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
