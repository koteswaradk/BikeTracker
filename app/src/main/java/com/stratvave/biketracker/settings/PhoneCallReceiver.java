package com.stratvave.biketracker.settings;

import java.lang.reflect.Method;

import com.android.internal.telephony.ITelephony;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.KeyEvent;

public class PhoneCallReceiver extends BroadcastReceiver{

	 Context context = null;
	 private static final String TAG = "Phone call";
	 private ITelephony telephonyService;
	@Override
	public void onReceive(Context arg0, Intent intent) {
		// TODO Auto-generated method stub
		
		if (!intent.getAction().equals("android.intent.action.PHONE_STATE")) return;
        String extraState = intent.getStringExtra(TelephonyManager.EXTRA_STATE);

        if (extraState.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
            String incomingNumber =
                intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);

            
            /*if (incomingNumber.contentEquals("1234567")) {
                //---answer the call---
                Intent i = new Intent(Intent.ACTION_MEDIA_BUTTON);
                i.putExtra(Intent.EXTRA_KEY_EVENT,
                        new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_HEADSETHOOK));
                context.sendOrderedBroadcast(i, null);
            }*/
        }
        return;
		/*Log.v(TAG, "Receving....");
		  TelephonyManager telephony = (TelephonyManager) 
		  context.getSystemService(Context.TELEPHONY_SERVICE);  
		  try {
		   Class c = Class.forName(telephony.getClass().getName());
		   Method m = c.getDeclaredMethod("getITelephony");
		   m.setAccessible(true);
		   telephonyService = (ITelephony) m.invoke(telephony);
		   //telephonyService.silenceRinger();
		   telephonyService.endCall();
		   String messageToSend = "this is a message";
		   String number = "8147711098";

		   SmsManager.getDefault().sendTextMessage(number, null, messageToSend, null,null);
		  } catch (Exception e) {
		   e.printStackTrace();
		  }*/
	}
	

}
