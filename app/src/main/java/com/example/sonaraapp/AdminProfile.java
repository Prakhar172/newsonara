package com.example.sonaraapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminProfile extends AppCompatActivity {
Button addengineering, addcustomer,customerpanel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminprofile);
        addcustomer = findViewById(R.id.btn_add_customer);
        addengineering = findViewById(R.id.add_engineering);
        customerpanel = findViewById(R.id.btn_customer_panel);
        addengineering.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    addcustomer.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent in = new Intent(AdminProfile.this,addcustomer.class);
            startActivity(in);
        }
    });
    customerpanel.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent in = new Intent(AdminProfile.this,customerlist.class);
            startActivity(in);

        }
    });
    }
}