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
import android.view.View;
import android.widget.Button;

import com.example.sonaraapp.commonclasses.Customer;
import com.example.sonaraapp.commonclasses.CustomerAdapter;

import java.util.ArrayList;
import java.util.List;

public class customerlist extends AppCompatActivity {
    Button customercall;
    RecyclerView recyclerView;
    List<Customer> customerList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customerlist);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        customerList = new ArrayList<>();
        customerList.add(new Customer("Anand","8979669612","123AB","Battery","compeleted","4500","good works","2300","8979669612"));
        customerList.add(new Customer("Anand","8979669612","123AB","Battery","compeleted","4500","good works","2300","8979669612"));
        customerList.add(new Customer("Anand","8979669612","123AB","Battery","compeleted","4500","good works","2300","8979669612"));
        customerList.add(new Customer("Anand","8979669612","123AB","Battery","compeleted","4500","good works","2300","8979669612"));
        customerList.add(new Customer("Anand","8979669612","123AB","Battery","compeleted","4500","good works","2300","8979669612"));
        customerList.add(new Customer("Anand","8979669612","123AB","Battery","compeleted","4500","good works","2300","8979669612"));
        customerList.add(new Customer("Anand","8979669612","123AB","Battery","compeleted","4500","good works","2300","8979669612"));
        customercall = findViewById(R.id.customer_call);

        CustomerAdapter adapter = new CustomerAdapter(this,customerList);
        recyclerView.setAdapter(adapter);



    }
}