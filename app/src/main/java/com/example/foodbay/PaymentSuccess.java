package com.example.foodbay;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class PaymentSuccess extends AppCompatActivity {

    private Button appmenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.paysuccess);

        appmenu = (Button) findViewById(R.id.menu);

        appmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMenu();
            }
        });

    }

    public void openMenu(){
        Intent menuPage = new Intent(this, Menu.class);
        startActivity(menuPage);
    }

}

