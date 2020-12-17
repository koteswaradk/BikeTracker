package com.stratvave.biketracker.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.Window;

public class SplashScreen extends Activity {
	
	private boolean activity_status=true;
	private int splash_time=2000;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.splashscreen);
		
		Thread splash= new Thread(new Runnable() {
			
			
			public void run() {
				// TODO Auto-generated method stub
				
				try {
					int wait_id=0;
					
					while(activity_status && wait_id<splash_time)
					{	
					Thread.sleep(500);
						if(activity_status)
						{
							wait_id+=100;
						}
					} 
					}
				
				catch (Exception e) 
				{
					// TODO: handle exception
				}
				finally
				{
					finish();
					startActivity(new Intent("com.stratvave.biketracker.main.MainBikeTrackerActivity"));
				}
		};
		
		});
		splash.start();
		
		
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		if(event.getAction()==MotionEvent.ACTION_DOWN)
		{
			activity_status=false;
		}
		
		return true; 
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}
}
