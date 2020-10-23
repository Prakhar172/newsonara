package com.example.sonaraapp.commonclasses;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.sonaraapp.AddEnginner;
import com.example.sonaraapp.AdminProfile;
import com.example.sonaraapp.R;

import java.util.HashMap;
import java.util.Map;

public class PhoneCallListener extends PhoneStateListener {
    public Context mctx;
    public String number;
    public PhoneCallListener(Context mctx,String number){
        this.mctx = mctx;
        this.number = number;

    }
    private boolean isPhoneCalling = false;

    String LOG_TAG = "LOGGING 123";

    @Override
    public void onCallStateChanged(int state, String incomingNumber) {

        if (TelephonyManager.CALL_STATE_RINGING == state) {
            // phone ringing
            Log.i(LOG_TAG, "RINGING, number: " + incomingNumber);
        }

        if (TelephonyManager.CALL_STATE_OFFHOOK == state) {
            // active
            Log.i(LOG_TAG, "OFFHOOK");

            isPhoneCalling = true;
        }

        if (TelephonyManager.CALL_STATE_IDLE == state) {
            // run when class initial and phone call ended, need detect flag
            // from CALL_STATE_OFFHOOK
            Log.i(LOG_TAG, "IDLE");
            if (isPhoneCalling) {

                Log.i(LOG_TAG, "restart app");


                isPhoneCalling = false;

                final Dialog dialog = new Dialog(mctx);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setCancelable(false);
                dialog.setContentView(R.layout.activity_custom_dialog);
                Button dialogButton = dialog.findViewById(R.id.btn_dialog);
                final EditText text_dialog = dialog.findViewById(R.id.text_dialog);
                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SharedData sharedData = new SharedData(mctx);
                        AddRemark(text_dialog.getText().toString(),sharedData.GetMobile());
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }

        }
    }

    private void AddRemark(final String remarks,final  String mobile) {
            StringRequest stringRequest = new StringRequest(Request.Method.PUT, URL.URL_REMARKS, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Toast.makeText(mctx, ""+response, Toast.LENGTH_SHORT).show();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(mctx, "error  "+error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("remarks",remarks);
                    params.put("mobile",mobile);
                    return params; // {email: 'shivnag@gmail.com',pass:'124'}
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(mctx);
            requestQueue.add(stringRequest);
        }
    }

