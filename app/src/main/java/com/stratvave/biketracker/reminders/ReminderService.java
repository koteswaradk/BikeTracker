package com.stratvave.biketracker.reminders;




import com.stratvave.biketracker.main.R;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Vibrator;

import android.util.Log;

public class ReminderService extends WakeReminderIntentService {
	  public static final String KEY_DATE_TIME = "reminder_date_time"; 
	    public static final String KEY_ROWID = "_id";
	    public static final String VEHCLE_NAME = "_vehiclename";
	    public static final String REMIND_ABOUT = "_remindabout";
	    public static final String KEY_NOTE = "_remindernote";
	    String bikename,aboutreminder;
	Cursor cursor;
	public ReminderService() {
		super("ReminderService");
			}

	@Override
	void doReminderWork(Intent intent) {
		Log.d("ReminderService", "Doing work.");
		Long rowId = intent.getExtras().getLong(RemindersDbAdapter.KEY_ROWID);
		 
		NotificationManager mgr = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
		
		Intent notificationIntent = new Intent(this, ReminderEditActivity.class); 
		notificationIntent.putExtra(RemindersDbAdapter.KEY_ROWID, rowId); 
		RemindersDbAdapter rda=new RemindersDbAdapter(this);
		rda.open();
		cursor=rda.fetchReminder(rowId);
		bikename=cursor.getString(cursor.getColumnIndexOrThrow(RemindersDbAdapter.REMIND_ABOUT));
		aboutreminder =cursor.getString( cursor.getColumnIndexOrThrow(RemindersDbAdapter.VEHCLE_NAME));
		rda.close();
		// startManagingCursor(cursor);
		
         
		PendingIntent pi = PendingIntent.getActivity(this, (int)(long)rowId, notificationIntent, PendingIntent.FLAG_ONE_SHOT); 
		
		Notification note=new Notification(R.drawable.icon, getString(R.string.notify_new_task_message), System.currentTimeMillis());
		
		Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
		 if(soundUri == null){
	         // alert is null, using backup
			 soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
	         if(soundUri == null){  // I can't see this ever being null (as always have a default notification) but just incase
	             // alert backup is null, using 2nd backup
	        	 soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);               
	         }
	     }
		 Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), soundUri);
		 r.play();
		/*NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getApplicationContext())
        .setSmallIcon(R.drawable.icon)
        .setContentTitle(getString(R.string.notify_new_task_message))
        .setContentText("Your Bike "+aboutreminder+"Need To Be For"+bikename)
        .setSound(soundUri); //
*/		//note.setLatestEventInfo(this, getString(R.string.notify_new_task_title), getString(R.string.notify_new_task_message), pi);
		note.setLatestEventInfo(this,bikename,"Your Bike "+aboutreminder+"Need To Be For"+bikename, pi);
		note.defaults |= Notification.DEFAULT_VIBRATE; 
		note.defaults |= Notification.DEFAULT_SOUND;
		note.defaults |= Notification.DEFAULT_LIGHTS;
		note.defaults |= Notification.DEFAULT_ALL;
		note.flags |= Notification.FLAG_AUTO_CANCEL;
		
		/*  Vibrator v = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
	        
		     // Vibrate for 300 milliseconds
		     v.vibrate(10000);*/
		   
		// An issue could occur if user ever enters over 2,147,483,647 tasks. (Max int value). 
		// I highly doubt this will ever happen. But is good to note. 
		int id = (int)((long)rowId);
		mgr.notify(id, note); 
		
		
	}
}
