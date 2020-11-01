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

public class Complaints extends AppCompatActivity {
EditText et_complaint_name,et_complaint_mobno,et_complaint_modelno,et_complaint_problem,et_complaint_estimate,et_complaint_paid;
Button addcomplaint;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaints);
        et_complaint_name = findViewById(R.id.et_complaint_name_of_customer);
        et_complaint_mobno = findViewById(R.id.et_complaint_mobile);
        et_complaint_modelno = findViewById(R.id.et_complaint_model_no);
        et_complaint_problem = findViewById(R.id.et_complaint_porblem);
        et_complaint_estimate = findViewById(R.id.et_complaint_estimate);
        et_complaint_paid = findViewById(R.id.et_complaint_apid);
        addcomplaint = findViewById(R.id.btn_add_complaint);

        addcomplaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (et_complaint_name.getText().toString().isEmpty()) {
                    et_complaint_name.setError("Username is can't be blank");
                } else if (et_complaint_mobno.getText().toString().isEmpty()) {
                    et_complaint_mobno.setError("Mobile Number can't be blank");
                } else if (et_complaint_modelno.getText().toString().length() != 10) {
                    et_complaint_modelno.setError("Mobile Number is Invalid!");
                } else if (et_complaint_problem.getText().toString().isEmpty()) {
                    et_complaint_problem.setError("Email can't be blank");
                } else if (et_complaint_estimate.getText().toString().isEmpty()) {
                    et_complaint_estimate.setError("Password can't be blank");
                } else if (et_complaint_paid.getText().toString().isEmpty()) {
                    et_complaint_paid.setError("Repassword can't be blank");
                } else {
                    addcomplaint(et_complaint_name.getText().toString(), et_complaint_mobno.getText().toString(), et_complaint_modelno.getText().toString(), et_complaint_problem.getText().toString(), et_complaint_estimate.getText().toString(), et_complaint_paid.getText().toString());
                }
            }
        });
    }

        private void addcomplaint(final String name, final String mobile, final String modelno, final String problem, final String estimate, final String paid) {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL.URL_COMPLAINTS, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    String name = response;
                    Toast.makeText(Complaints.this, "toast+ "+name, Toast.LENGTH_SHORT).show();

                    //                    if(Integer.parseInt(response)>0){
//                        SharedData sd = new SharedData(signup.this);
//                        sd.SaveSharedPrefernce(mobile,pass,response);
//                        Intent in = new Intent(signup.this,EmployeeProfile.class);
//                        startActivity(in);
//                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(Complaints.this, "error  "+error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    //{name:req.body.name,mobile:req.body.mobile,modelno:req.body.modelno,serialno:req.body.serialno,problem:req.body.problem,workdone:req.body.workdone,estimate:req.body.estimate,paid:req.body.paid,remarks:req.body.remarks}
                    params.put("name",name);
                    params.put("mobile",mobile);
                    params.put("modelno",modelno);
                    params.put("engineer_id","21");

                    params.put("problem",problem);
                    params.put("estimate",estimate);
                    params.put("paid",paid);

//                Toast.makeText(signup.this, "--"+mobile, Toast.LENGTH_SHORT).show();
                    return params; // {email: 'shivnag@gmail.com',pass:'124'}
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(Complaints.this);
            requestQueue.add(stringRequest);
//        VolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
        }


    }
