package com.example.foodbay;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class PaymentGate extends AppCompatActivity {

    Button paynow;
    EditText etholder;
    EditText etcardNumber;
    EditText etdate;
    EditText etcvv;
    ProgressBar pProgg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.paymentgate);

        //Assign Variable
        etholder = findViewById(R.id.holder);
        etcardNumber = findViewById(R.id.cardNumber);
        etdate = findViewById(R.id.expdate);
        etcvv = findViewById(R.id.cvv);
        paynow = findViewById(R.id.paynow);
        pProgg = findViewById(R.id.payProg);

        //Initializing Validation Style


        //Adding validation

        paynow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                pProgg.setVisibility(View.VISIBLE);

               if (!validateHolder() | !validateVisa() ){
                   Toast.makeText(PaymentGate.this, "Please enter valid Visa details !", Toast.LENGTH_SHORT).show();
               }/*
               else if(!validateHolder() | !validateMaster()){
                   Toast.makeText(PaymentGate.this, "Please enter valid Master card details !", Toast.LENGTH_SHORT).show();
               }*/
               else{
                   pProgg.setVisibility(View.GONE);

                   Toast.makeText(PaymentGate.this, "Payment Success !", Toast.LENGTH_SHORT).show();

                   startActivity(new Intent(getApplicationContext(),PaymentSuccess.class));

               }
            }
        });

    }

    private  boolean validateHolder() {
        String val = etholder.getEditableText().toString().trim();
        String checkspaces = "\\A\\w{2,20}\\z";

        if (val.isEmpty()){
            etholder.setError("Required !");
            return false;
        }
        else if(val.length()>=30){
            etholder.setError("Length error !");
            return false;
        }
        /*else if(!val.matches(checkspaces)){
            etholder.setError("No white spaces are allowed!");
            return false;
        }*/
        else{
            etholder.setError(null);
            etholder.setEnabled(false);
            return true;
        }
    }

    private  boolean validateVisa() {
        String val = etcardNumber.getEditableText().toString().trim();
        String checkVisa = "^4[0-9]{12}(?:[0-9]{3})?$";

        if (val.isEmpty()){
            etcardNumber.setError("Required !");
            return false;
        }
        else if(!val.matches(checkVisa)){
            etcardNumber.setError("Invalid Visa card !");
            return false;
        }
        else{
            etcardNumber.setError(null);
            etcardNumber.setEnabled(false);
            return true;
        }
    }
    private  boolean validateMaster() {
        String val = etcardNumber.getEditableText().toString().trim();
        String checkMaster = "^5[1-5][0-9]{14}|^(222[1-9]|22[3-9]\\\\d|2[3-6]\\\\d{2}|27[0-1]\\\\d|2720)[0-9]{12}$";

        if (val.isEmpty()){
            etcardNumber.setError("Required !");
            return false;
        }
        else if(!val.matches(checkMaster)){
            etcardNumber.setError("Invalid Master card !");
            return false;
        }
        else{
            etcardNumber.setError(null);
            etcardNumber.setEnabled(false);
            return true;
        }
    }

    private  boolean validateCvv() {
        String val = etcardNumber.getEditableText().toString().trim();
        String checkcvv = "^[0-9]{3,4}$";

        if (val.isEmpty()){
            etcardNumber.setError("Required !");
            return false;
        }
        else if(!val.matches(checkcvv)){
            etcardNumber.setError("Invalid CVV !");
            return false;
        }
        else{
            etcardNumber.setError(null);
            etcardNumber.setEnabled(false);
            return true;
        }
    }

    boolean validateCardExpiryDate() {
        String val = etdate.getEditableText().toString().trim();
        String checkDate = "(?:0[1-9]|1[0-2])/[0-9]{2}";

        if (val.isEmpty()){
            etcardNumber.setError("Required !");
            return false;
        }
        else if(!val.matches(checkDate)){
            etcardNumber.setError("Invalid date or card is expired !");
            return false;
        }
        else{
            etcardNumber.setError(null);
            etcardNumber.setEnabled(false);
            return true;
        }
    }

}