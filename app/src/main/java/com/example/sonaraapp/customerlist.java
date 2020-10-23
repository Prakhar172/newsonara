package com.example.sonaraapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.sonaraapp.commonclasses.Customer;
import com.example.sonaraapp.commonclasses.CustomerAdapter;
import com.example.sonaraapp.commonclasses.SharedData;
import com.example.sonaraapp.commonclasses.URL;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class customerlist extends AppCompatActivity {
    Button customercall;
    RecyclerView recyclerView;
    List<Customer> customerList;
    int userid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customerlist);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        customerList = new ArrayList<>();
        SharedData sp = new SharedData(customerlist.this);
        ShowAllUser(customerList,sp.GetMobile());
//        customerList.add(new Customer("Anand","8979669612","123AB","Battery","compeleted","4500","good works","2300","8979669612"));





    }
    private void ShowAllUser(final List<Customer> li,final String mobile) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL.URL_SHOWALLCUSTOMER, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);

                    for(int i=0;i<jsonArray.length();i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
//                        Log.d("mobile",jsonObject.getString("enginner_id"));
                        if (mobile.equalsIgnoreCase(jsonObject.getString("enginner_id"))) {
                            userid = jsonObject.getInt("id");
                            li.add(new Customer(jsonObject.getString("name"), jsonObject.getString("mobile"), jsonObject.getString("serialno"), jsonObject.getString("problem"), jsonObject.getString("workdone"), jsonObject.getString("estimate"), jsonObject.getString("remarks"), jsonObject.getString("paid"), jsonObject.getString("mobile")));
                        }else{
                            if(mobile.equalsIgnoreCase("0987654321")){
                                userid = jsonObject.getInt("id");
                                li.add(new Customer(jsonObject.getString("name"), jsonObject.getString("mobile"), jsonObject.getString("serialno"), jsonObject.getString("problem"), jsonObject.getString("workdone"), jsonObject.getString("estimate"), jsonObject.getString("remarks"), jsonObject.getString("paid"), jsonObject.getString("mobile")));
                            }
                        }
                    }
                    customercall = findViewById(R.id.customer_call);
                    CustomerAdapter adapter = new CustomerAdapter(customerlist.this,customerList);
                    recyclerView.setAdapter(adapter);

                }catch (Exception e){
                    Toast.makeText(customerlist.this, "error"+e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(customerlist.this, "error  "+error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(customerlist.this);
        requestQueue.add(stringRequest);
    }
}