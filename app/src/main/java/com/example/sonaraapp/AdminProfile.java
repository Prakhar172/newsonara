package com.example.sonaraapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.sonaraapp.commonclasses.SharedData;

public class AdminProfile extends AppCompatActivity {
Button addengineer, addcustomer,showcustomer,logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminprofile);
        addcustomer = findViewById(R.id.btn_add_customer);
        addengineer = findViewById(R.id.btn_add_enginner);
        showcustomer = findViewById(R.id.btn_show_customer);
        logout = findViewById(R.id.btn_logout);


    addcustomer.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent in = new Intent(AdminProfile.this,addcustomer.class);
            startActivity(in);
        }
    });
    addengineer.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent in = new Intent(AdminProfile.this,AddEnginner.class);
            startActivity(in);
        }
    });
    showcustomer.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent in = new Intent(AdminProfile.this,customerlist.class);
            startActivity(in);
        }
    });
    logout.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            SharedData sd = new SharedData(AdminProfile.this);
            sd.clearSharedPreferences();
            startActivity(new Intent(AdminProfile.this,login.class));
        }
    });
    }
}