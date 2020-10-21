package com.example.sonaraapp.commonclasses;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
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
        Customer customer = customerList.get(position);
        holder.name.setText(customer.getName());
        holder.mobno.setText(customer.getMobile());
        holder.customer_call.setText(customer.getCallnumber());
        holder.serialno.setText(customer.getSerialno());
        holder.problems.setText(customer.getProblem());
        holder.workdone.setText(customer.getWorkdone());
        holder.estimate.setText(customer.getEstimate());
        holder.paid.setText(customer.getPaid());
        holder.remarks.setText(customer.getRemarks());
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
