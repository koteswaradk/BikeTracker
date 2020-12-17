package com.stratvave.biketracker.settings;

import com.stratvave.biketracker.main.R;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.telephony.PhoneStateListener;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.RadioGroup;
import android.widget.Toast;

public class GetCallerInfoActivity extends Activity implements OnClickListener {

	String number;
	RadioGroup rg;
	String text="I Am Busy call you Later";
	SharedPreferences prefs;
	SharedPreferences.Editor edit;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main);
		findViewById(R.id.ok_button_ok).setOnClickListener(this);
		 prefs = PreferenceManager.getDefaultSharedPreferences(GetCallerInfoActivity.this);
		TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		rg = (RadioGroup) findViewById(R.id.radioGroup1);
		
		PhoneStateListener callStateListener = new PhoneStateListener() {
			public void onCallStateChanged(int state, String incomingNumber) {
				// TODO React to incoming call.
				number = incomingNumber;
				if (state == TelephonyManager.CALL_STATE_RINGING) {
					Toast.makeText(getApplicationContext(),"Phone Is Riging" + number, Toast.LENGTH_LONG).show();
					 sendSMS(number,text);
					
					 /* Intent smsIntent = new Intent(GetCallerInfoActivity.this,
					  MyEventService.class); Bundle bundle = new Bundle();
					  
					  bundle.putCharSequence("extraSmsNumber",number);
					  
					  bundle.putCharSequence("extraSmsText", "");
					  
					  smsIntent.putExtras(bundle);
					  
					  PendingIntent Sender =
					  PendingIntent.getService(GetCallerInfoActivity.this, 0
					  ,smsIntent , 0); startActivity(Sender);*/
					 
				}
				if (state == TelephonyManager.CALL_STATE_OFFHOOK) {
					Toast.makeText(getApplicationContext(),
							"Phone is Currently in A call", Toast.LENGTH_LONG)
							.show();
				}

				if (state == TelephonyManager.CALL_STATE_IDLE) {
					Toast.makeText(getApplicationContext(),
							"phone is neither ringing nor in a call",
							Toast.LENGTH_LONG).show();
				}
			}

			/*private void startActivity(PendingIntent sender) {
				// TODO Auto-generated method stub

			}*/
		};
		telephonyManager.listen(callStateListener,
				PhoneStateListener.LISTEN_CALL_STATE);

	}

	
	 public void sendSMS(String phoneNumber, String message) { PendingIntent
	 pi = PendingIntent.getActivity(this, 0, new Intent(this,
	 PhoneCallReceiver.class), 0); SmsManager sms = SmsManager.getDefault();
	 sms.sendTextMessage(phoneNumber, null, message, pi, null); }


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.ok_button:
			rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

				@Override
				public void onCheckedChanged(RadioGroup group, int checkedId) {
					// TODO Auto-generated method stub
					switch (checkedId) {
					case R.id.radio0:
						Toast.makeText(GetCallerInfoActivity.this, "Milage r1", Toast.LENGTH_SHORT).show();
						text="I Am Busy call you Later";
						 edit=prefs.edit();
						edit.putString("key", text);
						break;

					case R.id.radio1:
						Toast.makeText(GetCallerInfoActivity.this, "Fuel r2", Toast.LENGTH_SHORT).show();
						text="I Am Driving CAll Me Later";
						 edit=prefs.edit();
						edit.putString("key", text);
											break;
					case R.id.radio2:
						Toast.makeText(GetCallerInfoActivity.this, "Expences r3", Toast.LENGTH_SHORT).show();
						text="I Am Driving CAll You Later";
						edit=prefs.edit();
						edit.putString("key", text);
						break;
					}
					
				}

				
			});
			break;

		
		}
	}
	 
}
