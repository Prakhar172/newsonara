package com.example.sonaraapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.sonaraapp.commonclasses.SharedData;

public class EmployeeProfile extends AppCompatActivity {

    Button btn_add_customer,btn_show_customer,btn_logout,btn_Complaints;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_profile);
        btn_add_customer = findViewById(R.id.btn_add_customer);
        btn_show_customer = findViewById(R.id.btn_show_customer);
        btn_Complaints = findViewById(R.id.btn_emplyoe_complaint);
        btn_logout = findViewById(R.id.btn_logout);
        SharedData sp = new SharedData(EmployeeProfile.this);
//        Toast.makeText(this, ""+sp.GetName(), Toast.LENGTH_SHORT).show();
        btn_Complaints.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(EmployeeProfile.this,Complaints.class);
                startActivity(in);

            }
        });
        btn_add_customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(EmployeeProfile.this,addcustomer.class);
                startActivity(in);
            }
        });

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedData sd = new SharedData(EmployeeProfile.this);
                sd.clearSharedPreferences();
                startActivity(new Intent(EmployeeProfile.this,login.class));
            }
        });

        btn_show_customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(EmployeeProfile.this,customerlist.class);
                startActivity(in);
            }
        });
    }
}