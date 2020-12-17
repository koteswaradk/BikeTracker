package com.stratvave.autoanswer;

import android.app.IntentService;
import android.content.Intent;
import java.lang.reflect.Method;
import com.android.internal.telephony.ITelephony;
import com.stratvave.biketracker.settings.GetCallerInfoActivity;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.preference.PreferenceManager;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.KeyEvent;

public class AutoAnswerIntentService extends IntentService {

	public AutoAnswerIntentService() {
		super("AutoAnswerIntentService");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		// TODO Auto-generated method stub

		Context context = getBaseContext();
		// Load preferences
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
		BluetoothHeadset bh = null;
		if (prefs.getBoolean("headset_only", false)) {
			bh = new BluetoothHeadset(this, null);
		}
		// Let the phone ring for a set delay
		try {
			Thread.sleep(Integer.parseInt(prefs.getString("delay", "2")) * 1000);
		} catch (InterruptedException e) {
			// We don't really care
		}
		// Check headset status right before picking up the call
		if (prefs.getBoolean("headset_only", false) && bh != null) {
			if (bh.getState() != BluetoothHeadset.STATE_CONNECTED) {
				bh.close();
				return;
			}
			bh.close();
		}
		// Make sure the phone is still ringing
		TelephonyManager tm = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
		if (tm.getCallState() != TelephonyManager.CALL_STATE_RINGING) {

			return;
		}
		// Answer the phone
		try {
			answerPhoneAidl(context);
		} catch (Exception e) {
			e.printStackTrace();
			Log.d("AutoAnswer",
					"Error trying to answer using telephony service.  Falling back to headset.");
			answerPhoneHeadsethook(context);
		}
		
		// Enable the speakerphone
		if (prefs.getBoolean("use_speakerphone", false)) {
			enableSpeakerPhone(context);
		}
		if (prefs.getBoolean("replay_with_sms", false)) {
			try {
				startActivity(new Intent(this, GetCallerInfoActivity.class));
				
				replaySms(context);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return;
		
		
	}

	@SuppressWarnings("unchecked")
	private void replaySms(Context context) throws Exception {
		// TODO Auto-generated method stub
		TelephonyManager tm = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
		Class c;
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
		String ss=prefs.getString("key", "");
		c = Class.forName(tm.getClass().getName());
		
		String number = TelephonyManager.EXTRA_INCOMING_NUMBER;
		Method m = c.getDeclaredMethod("getITelephony");
		m.setAccessible(true);
		ITelephony telephonyService;
		telephonyService = (ITelephony) m.invoke(tm);

		/*
		 * SmsManager sms = SmsManager.getDefault(); try {
		 * sms.sendTextMessage(number, null, "i am on Drive call you later",
		 * null, null); } catch(IllegalArgumentException e) {
		 * 
		 * }
		 */
		// Silence the ringer and answer the call!
		telephonyService.silenceRinger();
		//telephonyService.replaySms();
        GetCallerInfoActivity ii=new GetCallerInfoActivity();
		
		ii.sendSMS(number, ss);
	}

	private void enableSpeakerPhone(Context context) {
		AudioManager audioManager = (AudioManager) context
				.getSystemService(Context.AUDIO_SERVICE);
		audioManager.setSpeakerphoneOn(true);
	}

	private void answerPhoneHeadsethook(Context context) {
		// Simulate a press of the headset button to pick up the call
		Intent buttonDown = new Intent(Intent.ACTION_MEDIA_BUTTON);
		buttonDown.putExtra(Intent.EXTRA_KEY_EVENT, new KeyEvent(
				KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_HEADSETHOOK));
		context.sendOrderedBroadcast(buttonDown,
				"android.permission.CALL_PRIVILEGED");
		// froyo and beyond trigger on buttonUp instead of buttonDown
		Intent buttonUp = new Intent(Intent.ACTION_MEDIA_BUTTON);
		buttonUp.putExtra(Intent.EXTRA_KEY_EVENT, new KeyEvent(
				KeyEvent.ACTION_UP, KeyEvent.KEYCODE_HEADSETHOOK));
		context.sendOrderedBroadcast(buttonUp,
				"android.permission.CALL_PRIVILEGED");
	}

	@SuppressWarnings("unchecked")
	private void answerPhoneAidl(Context context) throws Exception {
		// Set up communication with the telephony service (thanks to Tedd's
		// Droid Tools!)
		
		TelephonyManager tm = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
		Class c = Class.forName(tm.getClass().getName());
		Method m = c.getDeclaredMethod("getITelephony");
		String number = TelephonyManager.EXTRA_INCOMING_NUMBER;
		m.setAccessible(true);
		ITelephony telephonyService;
		telephonyService = (ITelephony) m.invoke(tm);

		/*
		 * SmsManager sms = SmsManager.getDefault(); try {
		 * sms.sendTextMessage(number, null, "i am on Drive call you later",
		 * null, null); } catch(IllegalArgumentException e) {
		 * 
		 * }
		 */
		// Silence the ringer and answer the call!
		telephonyService.silenceRinger();
		telephonyService.answerRingingCall();
		
	}

}
