package com.example.sonaraapp.commonclasses;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.telecom.TelecomManager;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sonaraapp.R;

import java.util.List;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder> {
    private Context mCtx;
    private List<Employee> employeeList;

    //getting the context and product list with constructor
    public EmployeeAdapter(Context mCtx, List<Employee> employeeList) {
        this.mCtx = mCtx;
        this.employeeList = employeeList;
    }

    @Override
    public EmployeeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.activity_employee, null);
        return new EmployeeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeViewHolder holder, int position) {
        final Employee employee = employeeList.get(position);
        holder.name.setText(employee.getName());
        holder.mobno.setText(employee.getMobile());
        holder.email.setText(employee.getEmail());
//        holder.email.setText(employee.getEmail());
    }

    @Override
    public int getItemCount() {
        return employeeList.size();
    }

    class EmployeeViewHolder extends RecyclerView.ViewHolder {
        TextView name,mobno,email;
        public EmployeeViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            mobno = itemView.findViewById(R.id.mobno);
            email = itemView.findViewById(R.id.email);
        }
    }
}
