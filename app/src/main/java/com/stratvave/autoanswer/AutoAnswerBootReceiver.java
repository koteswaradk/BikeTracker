package com.stratvave.autoanswer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.stratvave.autoanswer.AutoAnswerBootReceiver;

public class AutoAnswerBootReceiver extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent arg1) {
		// TODO Auto-generated method stub
		AutoAnswerNotifier notifier = new AutoAnswerNotifier(context); 
		notifier.updateNotification(); 
		
	}

}
