package com.example.sonaraapp;

import androidx.appcompat.app.AppCompatActivity;

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
import com.example.sonaraapp.commonclasses.URL;

import java.util.HashMap;
import java.util.Map;

public class addcustomer extends AppCompatActivity {
    EditText et_name_of_customer, et_mobile, et_model_no, et_serial_no, et_problems, et_workdone, et_estimate, et_apid, et_remark;
    Button btn_signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcustomer);
        et_name_of_customer = findViewById(R.id.et_name_of_customer);
        et_mobile = findViewById(R.id.et_mobile);
        et_model_no = findViewById(R.id.et_model_no);
        et_serial_no = findViewById(R.id.et_serial_no);
        et_problems = findViewById(R.id.et_problems);
        et_estimate = findViewById(R.id.et_estimate);
        et_apid = findViewById(R.id.et_apid);
        btn_signup = findViewById(R.id.btn_signup);
        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddUser(et_name_of_customer.getText().toString(), et_mobile.getText().toString(), et_model_no.getText().toString(), et_serial_no.getText().toString(), et_problems.getText().toString(), et_workdone.getText().toString(), et_estimate.getText().toString(), et_apid.getText().toString(), et_remark.getText().toString());
            }
        });

    }

    private void AddUser(final String username, final String mobile, final String modelno, final String serialno, final String problems, final String workdone, final String estimate, final String paid, final String remarks) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL.URL_ADDCUSTOMER, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "" + response, Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(addcustomer.this, "error --> "+error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                //{name:req.body.name,mobile:req.body.mobile,modelno:req.body.modelno,serialno:req.body.serialno,problem:req.body.problem,workdone:req.body.workdone,estimate:req.body.estimate,paid:req.body.paid,remarks:req.body.remarks}
                params.put("name", username);
                params.put("mobile", mobile);
                params.put("modelno", modelno);
                params.put("serialno", serialno);
                params.put("problem", problems);
                params.put("workdone", workdone);
                params.put("estimate", estimate);
                params.put("paid", paid);
                params.put("remarks", remarks);
                return params; // {email: 'shivnag@gmail.com',pass:'124'}
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }
}
