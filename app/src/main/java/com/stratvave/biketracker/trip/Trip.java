package com.stratvave.biketracker.trip;

import com.stratvave.biketracker.main.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageButton;

public class Trip extends Activity implements OnClickListener{
	
	ImageButton submit_trip, cancel_trip,clear_trip;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.trip);
		findViewById(R.id.departure_trip_button).setOnClickListener(this);
		findViewById(R.id.arrival_trip_button).setOnClickListener(this);
			
	}

	
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.departure_trip_button:
			startActivity(new Intent(this, DepartureDetails.class));
		break;
		case R.id.arrival_trip_button:
			startActivity(new Intent(this, ArrivalDetails.class));
			
			break;
		/*case R.id.submit_trip_button:
			else
				if(arr_dt_et.length()<=0)
				{
					arr_dt_et.setError("Enter Arrival Date");
				}else
				else
				
				if (arr_notes_et.length()<=0) {
					arr_notes_et.setError("Enter Enter Arrival Note");
				}else{
						AlertDialog.Builder alertDialog= new AlertDialog.Builder(Trip.this);
				
						alertDialog.setTitle("Save");
						alertDialog.setMessage("Saving to database");
						
						alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
							
								Toast.makeText(getBaseContext(), "Saved to database", Toast.LENGTH_SHORT).show();
								
							}
						});
						
						alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
							
							Toast.makeText(getBaseContext(), "Cancelled", Toast.LENGTH_SHORT).show();
								
							}
						});
						
						alertDialog.show();
			}
			
			break;
		case R.id.cancel_trip_button:
			
			startActivity(new Intent(Trip.this,MainBikeTrackerActivity.class));
			break;
		case R.id.clear_trip_button:
	
			break;*/
			
		
		}
		
		
	}
}
