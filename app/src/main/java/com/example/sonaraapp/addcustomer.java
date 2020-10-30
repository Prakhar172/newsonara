package com.example.sonaraapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
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
//class ListDisplay extends Activity {
//    // Array of strings...
//    String[] mobileArray = {"Android", "IPhone", "WindowsMobile", "Blackberry",
//            "WebOS", "Ubuntu", "Windows7", "Max OS X"};
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        ArrayAdapter adapter = new ArrayAdapter<String>(this,
//                R.layout.activity_addcustomer, mobileArray);
//
//        ListView listView = (ListView) findViewById(R.id.Products_List_view);
//        listView.setAdapter(adapter);
//    }
//}
public class addcustomer extends AppCompatActivity {
    EditText et_name_of_customer, et_mobile, et_model_no, et_serial_no, et_problems, et_estimate, et_paid, Products_list_view;
    Button btn_signup;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcustomer);
        et_name_of_customer = findViewById(R.id.et_name_of_customer);
        et_mobile = findViewById(R.id.et_mobile);
        et_model_no = findViewById(R.id.et_model_no);
        et_serial_no = findViewById(R.id.et_serial_no);
        et_estimate = findViewById(R.id.et_estimate);
//        Products_list_view = findViewById(R.id.Products_List_view);
        et_paid = findViewById(R.id.et_apid);
        btn_signup = findViewById(R.id.btn_signup);

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (et_name_of_customer.getText().toString().isEmpty()) {
                    et_name_of_customer.setError("Username can't be blank");
                } else if (et_mobile.getText().toString().isEmpty()) {
                    et_mobile.setError("Mobile Number can't be blank");
                } else if (et_model_no.getText().toString().isEmpty()) {
                    et_model_no.setError("Model Number can't be blank");
                } else if (et_serial_no.getText().toString().isEmpty()) {
                    et_serial_no.setError("Serial Number Can't be blank");
                } else if (et_problems.getText().toString().isEmpty()) {
                    et_problems.setError("Problem can't be blank");
                } else if (et_estimate.getText().toString().isEmpty()) {
                    et_estimate.setError("Estimate Value can't be blank");
                } else if (et_paid.getText().toString().isEmpty()) {
                    et_paid.setError("Paid Value can't be blank");
                } else {
                    SharedData sharedData = new SharedData(addcustomer.this);
                    Toast.makeText(addcustomer.this, "so" + sharedData.GetMobile(), Toast.LENGTH_SHORT).show();
                    AddUser(et_name_of_customer.getText().toString(), et_mobile.getText().toString(), et_model_no.getText().toString(), et_serial_no.getText().toString(), et_problems.getText().toString(), et_estimate.getText().toString(), et_paid.getText().toString(), sharedData.GetMobile());
                }
            }
        });

    }

    private void AddUser(final String username, final String mobile, final String modelno, final String serialno, final String problems, final String estimate, final String paid, final String enginner_id) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL.URL_ADDCUSTOMER, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                Toast.makeText(addcustomer.this, ""+response, Toast.LENGTH_SHORT).show();
                if (Integer.parseInt(response) > 0) {
                    String adminnumber = "0987654321";
                    if (!response.equalsIgnoreCase(adminnumber)) {
                        Intent in = new Intent(addcustomer.this, EmployeeProfile.class);
                        startActivity(in);
                    } else {
                        Intent in = new Intent(addcustomer.this, AdminProfile.class);
                        startActivity(in);
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(addcustomer.this, "error --> " + error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
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
                params.put("estimate", estimate);
                params.put("paid", paid);
                params.put("enginner_id", enginner_id);
                return params; // {email: 'shivnag@gmail.com',pass:'124'}
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }
}
