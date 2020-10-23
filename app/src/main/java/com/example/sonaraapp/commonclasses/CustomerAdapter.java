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

public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.CustomerViewHolder> {
    private Context mCtx;
    private List<Customer> customerList;

    //getting the context and product list with constructor
    public CustomerAdapter(Context mCtx, List<Customer> customerList) {
        this.mCtx = mCtx;
        this.customerList = customerList;
    }

    @Override
    public CustomerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.activity_customer, null);
        return new CustomerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerViewHolder holder, int position) {
        final Customer customer = customerList.get(position);
        holder.name.setText(customer.getName());
        holder.mobno.setText(customer.getMobile());
//        holder.customer_call.setText(customer.getCallnumber());
        holder.serialno.setText(customer.getSerialno());
        holder.problems.setText(customer.getProblem());
        holder.workdone.setText(customer.getWorkdone());
        holder.estimate.setText(customer.getEstimate());
        holder.paid.setText(customer.getPaid());
        holder.remarks.setText(customer.getRemarks());
        holder.customer_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number = customer.getCallnumber();
                PhoneCallListener phoneListener = new PhoneCallListener(mCtx,customer.getMobile());
                TelephonyManager telephonyManager =     (TelephonyManager) mCtx.getSystemService(Context.TELEPHONY_SERVICE);
                telephonyManager.listen(phoneListener, PhoneStateListener.LISTEN_CALL_STATE);

                if (ActivityCompat.checkSelfPermission(mCtx,Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                    callIntent.setData(Uri.parse("tel:"+customer.getCallnumber()));
//                    callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mCtx.startActivity(callIntent);
                }else{
                    Toast.makeText(mCtx, "You don't assign permission.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return customerList.size();
    }

    class CustomerViewHolder extends RecyclerView.ViewHolder {
        TextView name,mobno,customer_call,serialno,problems,workdone,estimate,paid,remarks;
        public CustomerViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            mobno = itemView.findViewById(R.id.mobno);
            customer_call = itemView.findViewById(R.id.customer_call);
            serialno = itemView.findViewById(R.id.serialno);
            problems = itemView.findViewById(R.id.problems);
            workdone = itemView.findViewById(R.id.workdone);
            estimate = itemView.findViewById(R.id.estimate);
            paid = itemView.findViewById(R.id.paid);
            remarks = itemView.findViewById(R.id.remarks);
        }
    }
}
