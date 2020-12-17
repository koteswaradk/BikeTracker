package com.stratvave.biketracker.settings;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.IBinder;

import android.telephony.SmsManager;
import android.widget.Toast;

public class MyEventService extends Service{
	
	private String smsTextToSend= " ", smsNumberToSend="  ";

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		
		
	}

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		
		return null;
	}
	
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		 super.onDestroy();
		 
	}

	@Override
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		super.onStart(intent, startId);
		
		try{
		Bundle bundle = intent.getExtras();
        smsNumberToSend = (String) bundle.getCharSequence("extraSmsNumber");
        
        smsTextToSend = (String) bundle.getCharSequence("extraSmsText");
  
     
        if((smsNumberToSend.length()>0)&&smsTextToSend.length()>0){
        	
        	sendSms(smsNumberToSend,smsTextToSend);
        
		
        }
        
       else{
    	   Toast.makeText(MyEventService.this, "Sms Alert was Disabled by You", Toast.LENGTH_LONG).show();
       }
		}
		catch (NullPointerException e) {
			// TODO: handle exception
			
		}
		
	}

	public void sendSms(String smsNumberToSend2, String smsTextToSend2) {
		// TODO Auto-generated method stub
		 
		  String SENT = "SMS_SENT";
	        String DELIVERED = "SMS_DELIVERED";
	 
	        PendingIntent sentPI = PendingIntent.getBroadcast(this, 0,
	            new Intent(SENT), 0);
	 
	        PendingIntent deliveredPI = PendingIntent.getBroadcast(this, 0,
	            new Intent(DELIVERED), 0);
	        //---when the SMS has been sent---
	        registerReceiver(new BroadcastReceiver(){
	            @Override
	            public void onReceive(Context arg0, Intent arg1) {
	                switch (getResultCode())
	                {
	                    case Activity.RESULT_OK:
	                        Toast.makeText(getBaseContext(), "SMS is being sending to"+smsNumberToSend, 
	                                Toast.LENGTH_SHORT).show();
	                        break;
	                    case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
	                        Toast.makeText(getBaseContext(), "Service Failure", 
	                                Toast.LENGTH_SHORT).show();
	                        break;
	                    case SmsManager.RESULT_ERROR_NO_SERVICE:
	                        Toast.makeText(getBaseContext(), "No service", 
	                                Toast.LENGTH_SHORT).show();
	                        break;
	                    case SmsManager.RESULT_ERROR_NULL_PDU:
	                        Toast.makeText(getBaseContext(), "Null PDU", 
	                                Toast.LENGTH_SHORT).show();
	                        break;
	                    case SmsManager.RESULT_ERROR_RADIO_OFF:
	                        Toast.makeText(getBaseContext(), "Radio off", 
	                                Toast.LENGTH_SHORT).show();
	                        break;
	                }
	            }
	        }, new IntentFilter(SENT));
	 
	        //---when the SMS has been delivered---
	        registerReceiver(new BroadcastReceiver(){
	            @Override
	            public void onReceive(Context arg0, Intent arg1) {
	            	try{
	                switch (getResultCode())
	                {
	                    case Activity.RESULT_OK:
	                    	 AlertDialog.Builder alertDialogBuilderforsms = new AlertDialog.Builder(MyEventService.this);
	         				alertDialogBuilderforsms.setTitle("SMS Notification");
	         				alertDialogBuilderforsms.setMessage(" ");
	         			
	         					 alertDialogBuilderforsms.setCancelable(false)
	         					 .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	         					public void onClick(DialogInterface dialog, int which) {
	         							// TODO Auto-generated method stub
	         						 Toast.makeText(getBaseContext(), "SMS Sent to"+smsNumberToSend, 
	     	                                Toast.LENGTH_SHORT).show();
	         						}
	         					
	         					});
	                     
	                        
	                        break;
	                    case Activity.RESULT_CANCELED:
	                        Toast.makeText(getBaseContext(), "SMS not delivered", 
	                                Toast.LENGTH_SHORT).show();
	                    
	                        break; 
	                  
	                }
	            	}
	            	catch (Exception e) {
						// TODO: handle exception
	            		
	            		
					}
	            }
	        }, new IntentFilter(DELIVERED));        
	        SmsManager smsManager = SmsManager.getDefault();         
		 smsManager.sendTextMessage(smsNumberToSend2, null, smsTextToSend2,sentPI, deliveredPI);
	}

	@Override
	public boolean onUnbind(Intent intent) {
		// TODO Auto-generated method stub
		Toast.makeText(this, "MyAlarmService.onUnbind()", Toast.LENGTH_LONG).show();
		return super.onUnbind(intent);
	}

	

}
