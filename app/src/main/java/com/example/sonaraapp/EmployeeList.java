package com.example.sonaraapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
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
import com.example.sonaraapp.commonclasses.Employee;
import com.example.sonaraapp.commonclasses.EmployeeAdapter;
import com.example.sonaraapp.commonclasses.SharedData;
import com.example.sonaraapp.commonclasses.URL;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeList extends AppCompatActivity {
    RecyclerView recyclerView;
    List<Employee> employeeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_list);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        employeeList = new ArrayList<>();
        SharedData sp = new SharedData(EmployeeList.this);
        ShowAllCustomer(employeeList);
    }

    private void ShowAllCustomer(final List<Employee> li) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL.URL_SHOWALLEMPLOYEES, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);

                    for(int i=0;i<jsonArray.length();i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        Log.d("logged",jsonObject.getString("name"));
                        li.add(new Employee(jsonObject.getString("name"),jsonObject.getString("mobile"),jsonObject.getString("email")));
                    }
                    EmployeeAdapter adapter = new EmployeeAdapter(EmployeeList.this,employeeList);
                    recyclerView.setAdapter(adapter);

                }catch (Exception e){
                    Toast.makeText(EmployeeList.this, "error"+e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(EmployeeList.this, "error  "+error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(EmployeeList.this);
        requestQueue.add(stringRequest);
    }
}