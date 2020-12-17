package com.stratvave.biketracker.reminders;

import com.stratvave.biketracker.main.R;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class AlarmReceiver extends BroadcastReceiver {
	 
		private static int NOTIFICATION_ID = 1;
	 
		
		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			/*NotificationManager mNotificationManager = 
					(NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
			*/Bundle bundle = intent.getExtras();
	        String itemName = bundle.getString("item_name");
	        String reminderOrAlarmMessage = bundle.getString("message");
	        String activityToTrigg = bundle.getString("activityToTrigg");
	        int itemId = Integer.parseInt(bundle.getString("item_id"));
	        NotificationManager nm = (NotificationManager) context.getSystemService("notification");
	        CharSequence text = itemName + " "+reminderOrAlarmMessage;
	        Notification notification = new Notification(R.drawable.icon, text,
	                System.currentTimeMillis());
	        Intent newIntent = new Intent();
	        newIntent.setAction(activityToTrigg);
	        newIntent.putExtra("item_id", itemId);
	        CharSequence text1= itemName + " "+reminderOrAlarmMessage;
	        CharSequence text2= "jhkjhskd";
	        PendingIntent pIntent = PendingIntent.getActivity(context,0, newIntent, 0);
	        notification.setLatestEventInfo(context, text1, text2, pIntent);
	        notification.flags = Notification.FLAG_AUTO_CANCEL;
	        notification.defaults = Notification.DEFAULT_ALL;
	        nm.notify(itemId, notification);
		
	 
	};
}
