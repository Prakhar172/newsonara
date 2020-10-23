package com.example.sonaraapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.sonaraapp.commonclasses.SharedData;
import com.example.sonaraapp.commonclasses.URL;

import java.util.HashMap;
import java.util.Map;

public class login extends AppCompatActivity {
Button signup,login;
EditText et_pass,et_mobile;
Button btn_login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login = findViewById(R.id.btn_login);
        et_mobile = findViewById(R.id.et_mobile);
        et_pass = findViewById(R.id.et_pass);
        signup = findViewById(R.id.btn_lgn_signup);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(login.this,signup.class);
                startActivity(in);
            }
        });
        Toast.makeText(this, "--------><----------", Toast.LENGTH_SHORT).show();
        SharedData sharedData = new SharedData(login.this);
        if(!sharedData.GetSharedPrefernce()){
            signup.setOnClickListener((new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent in = new Intent(login.this,signup.class);
                    startActivity(in);
                }
            }));

            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (et_mobile.getText().toString().isEmpty()) {
                        et_mobile.setError("Mobile Number can't be blank");
                    } else if (et_mobile.getText().toString().length() != 10) {
                        et_mobile.setError("Mobile Number is not Valid");
                    } else if (et_pass.getText().toString().isEmpty()) {
                        et_pass.setError("Password can't be blank");
                    } else {
                        LoginUser(et_mobile.getText().toString(), et_pass.getText().toString());
                    }
                }
            });
        }
        else if(sharedData.GetName().equalsIgnoreCase("Admin")){
            Intent in = new Intent(login.this,AdminProfile.class);
            startActivity(in);
        }else{
            Intent in = new Intent(login.this,EmployeeProfile.class);
            startActivity(in);
        }

    }
    private void LoginUser(final String mobile, final String pass) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL.URL_LOGIN, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String[] name = response.split("-");
                if(Integer.parseInt(name[0])>0) {
                    SharedData sd = new SharedData(login.this);
                    sd.SaveSharedPrefernce(mobile, pass, name[1]);
                    if (name[1].equalsIgnoreCase("Admin")) {
                        Intent in = new Intent(login.this, AdminProfile.class);
                        startActivity(in);
                    } else {
                        Intent in = new Intent(login.this, EmployeeProfile.class);
                        startActivity(in);
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(login.this, "error  "+error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("mobile",mobile);
                params.put("pass",pass);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(login.this);
        requestQueue.add(stringRequest);
    }

}