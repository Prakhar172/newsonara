package com.example.sonaraapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class sold_activity extends AppCompatActivity {
Button show_all_customer, products;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sold_activity);
        show_all_customer = findViewById(R.id.btn_show_customer);
        products = findViewById(R.id.btn_products);
        show_all_customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(sold_activity.this,customerlist.class);
                startActivity(in);
            }
        });
products.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent in = new Intent(sold_activity.this,products.class);
        startActivity(in);

    }
});
    }
}