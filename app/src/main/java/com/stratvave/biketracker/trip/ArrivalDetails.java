package com.stratvave.biketracker.trip;

import com.stratvave.biketracker.databases.DataHelper;
import com.stratvave.biketracker.main.MainBikeTrackerActivity;
import com.stratvave.biketracker.main.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ArrivalDetails extends Activity implements OnClickListener{
	
	
	Button arr_submit, arr_cancel,arr_clear;
	EditText arr_notes_et,arr_loc_et,arr_odo_et;
	
	String arr_odo_ets, arr_loc_ets, arr_notes_ets;
	
	private DataHelper dh;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.arrival);
		
		try {
			this.dh= new DataHelper(this);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error "+e);
			
		}
		
		//arr_dt_et=(EditText) findViewById(R.id.arr_dt_et);
		
		 arr_odo_et= (EditText) findViewById(R.id.arr_odo_et);
		 arr_loc_et= (EditText) findViewById(R.id.arr_loc_et);
		 arr_notes_et= (EditText) findViewById(R.id.arr_notes_et);
		 
		
		
		 findViewById(R.id.arr_submit_button).setOnClickListener(this);
		 findViewById(R.id.arr_cancel_button).setOnClickListener(this);
		/* findViewById(R.id.arr_clear_button).setOnClickListener(this);*/
		
		
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		switch (v.getId()) {
		case R.id.arr_submit_button:

			if (arr_odo_et.length()<=0) {
				arr_odo_et.setError("Enter Arrival odometer");
			}else
			
			if (arr_loc_et.length()<=0) {
				arr_loc_et.setError("Enter Arrival location");
			}else
				if (arr_notes_et.length()<=0) {
					arr_notes_et.setError("EEnter the note here");
					}
				else
			{

					 arr_odo_ets=arr_odo_et.getText().toString();
					 arr_loc_ets=arr_loc_et.getText().toString();
					 arr_notes_ets=arr_notes_et.getText().toString();
				AlertDialog.Builder alertDialog= new AlertDialog.Builder(ArrivalDetails.this);
			alertDialog.setIcon(R.drawable.et);
			alertDialog.setMessage("Save Arrival Details to database");
			
			alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
				
				
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					
					dh.open();
					long check= dh.arrival(arr_odo_ets, arr_loc_ets, arr_notes_ets);
					dh.close();
					if (check!=-1) {
						LayoutInflater inflater = getLayoutInflater();
				    	 View layout = inflater.inflate(R.layout.customtoast,
				    	                                (ViewGroup) findViewById(R.id.toast_layout_root));

				    	 TextView text = (TextView) layout.findViewById(R.id.text);
				    	 text.setText("Arrival Details Saved Success Fully");
		               text.setTextSize(20);
				    	 Toast toast = new Toast(getApplicationContext());
				    	 toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
				    	 toast.setDuration(Toast.LENGTH_LONG);
				    	 toast.setView(layout);
				    	 toast.show();
					
						finish();
					}
					else {
						LayoutInflater inflater = getLayoutInflater();
				    	 View layout = inflater.inflate(R.layout.customtoast,
				    	                                (ViewGroup) findViewById(R.id.toast_layout_root));

				    	 TextView text = (TextView) layout.findViewById(R.id.text);
				    	 text.setText("Sorry Arrival Details Not Saved");
		               text.setTextSize(20);
				    	 Toast toast = new Toast(getApplicationContext());
				    	 toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
				    	 toast.setDuration(Toast.LENGTH_LONG);
				    	 toast.setView(layout);
				    	 toast.show();
					
						finish();
					}
	
				}
			});
			
			alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
				
				
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
				
					LayoutInflater inflater = getLayoutInflater();
			    	 View layout = inflater.inflate(R.layout.customtoast,
			    	                                (ViewGroup) findViewById(R.id.toast_layout_root));

			    	 TextView text = (TextView) layout.findViewById(R.id.text);
			    	 text.setText("Cancelled");
	               text.setTextSize(20);
			    	 Toast toast = new Toast(getApplicationContext());
			    	 toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
			    	 toast.setDuration(Toast.LENGTH_LONG);
			    	 toast.setView(layout);
			    	 toast.show();
					finish();
					
				}
			});
			
			alertDialog.show();
			}
			
			
			break;

		case R.id.arr_cancel_button:
			
			Intent i= new Intent(ArrivalDetails.this,MainBikeTrackerActivity.class);
			startActivity(i);
			finish();
			/*AlertDialog.Builder alertDialog2= new AlertDialog.Builder(ArrivalDetails.this);
			alertDialog2.setTitle("Cancel");
			alertDialog2.setMessage("Return to home page");
			
			alertDialog2.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
				
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
				
				
				}
			});

			alertDialog2.setNegativeButton("No", new DialogInterface.OnClickListener() {
				
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					
				}
			});
					alertDialog2.show();*/
			break;
			
		/*case R.id.arr_clear_button:
			AlertDialog.Builder alertDialog1= new AlertDialog.Builder(ArrivalDetails.this);
			alertDialog1.setTitle("Alert");
			alertDialog1.setMessage("Do you want to clear all the data");
			alertDialog1.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
				
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					arr_odo_et.setText("");
					arr_loc_et.setText("");
					arr_notes_et.setText("");
					
						}
			});
			
			
			alertDialog1.setNegativeButton("No", new DialogInterface.OnClickListener() {
				
					public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
				
					Toast.makeText(getApplicationContext(), "Cancelled", Toast.LENGTH_SHORT).show();		
				}
			});
			
			alertDialog1.show();
			
			break;
			*/
		default:
			break;
		}
		
	}

}
