package com.example.sonaraapp.commonclasses;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

public class PhoneCallListener extends PhoneStateListener {
    public Context mctx;
    public PhoneCallListener(Context mctx){
        this.mctx = mctx;
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

                // restart app
//                Intent i = mctx.getPackageManager().getLaunchIntentForPackage(mctx.getPackageName());
//                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                mctx.startActivity(i);

                isPhoneCalling = false;
                AlertDialog.Builder builder1 = new AlertDialog.Builder(mctx);
                builder1.setMessage("Write your message here.");
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                builder1.setNegativeButton(
                        "No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();
            }

        }
    }

}
