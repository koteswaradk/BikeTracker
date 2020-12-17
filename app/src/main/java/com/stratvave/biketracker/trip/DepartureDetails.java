package com.stratvave.biketracker.trip;

import java.util.ArrayList;
import java.util.Calendar;

import com.googlecode.android.widgets.DateSlider.DateSlider;
import com.googlecode.android.widgets.DateSlider.DateTimeSlider;
import com.googlecode.android.widgets.DateSlider.labeler.TimeLabeler;
import com.stratvave.biketracker.databases.DataHelper;
import com.stratvave.biketracker.main.MainBikeTrackerActivity;
import com.stratvave.biketracker.main.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Spinner;
import android.widget.Toast;

public class DepartureDetails extends Activity implements android.view.View.OnClickListener,OnItemSelectedListener, OnCheckedChangeListener{
	static final int DATETIMESELECTOR_ID = 0;
	RadioButton dep_business, dep_personal;
	EditText purpose_et,client_et,dep_dt_et,dep_odo_et,dep_loc_et,dep_notes_et;
	Button dep_submit, dep_cancel, dep_clear;
	Spinner dep_vehicle;
	ArrayList<String> bikename;
	private DataHelper dh;
	
	String purpose_ets,client_ets,dep_dt_ets,dep_odo_ets,dep_loc_ets,dep_notes_ets, dep_vehicles, dep_radio;
	RadioGroup typeofgerny;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.departure);
		
		dh= new DataHelper(this);
		dh.open();
		bikename=dh.getBikeName();
		dh.close();
		
		dep_business=(RadioButton) findViewById(R.id.business_rb);
		dep_personal=(RadioButton) findViewById(R.id.personal_rb);
		typeofgerny=(RadioGroup) findViewById(R.id.type_rg);
		
		
		purpose_et=(EditText) findViewById(R.id.purpose_et);
		client_et=(EditText) findViewById(R.id.client_et);
		dep_dt_et=(EditText) findViewById(R.id.dep_dt_et);
		dep_odo_et=(EditText) findViewById(R.id.dep_odo_et);
		dep_loc_et=(EditText) findViewById(R.id.dep_loc_et);
		dep_notes_et=(EditText) findViewById(R.id.dep_notes_et);
		
		dep_vehicle=(Spinner) findViewById(R.id.dep_vehicle_spinner);
		dep_vehicle.setOnItemSelectedListener(DepartureDetails.this);
		
		dep_business.setOnClickListener(this);
		dep_personal.setOnClickListener(this);
		
		typeofgerny.setOnCheckedChangeListener(this);
		/*dep_submit=(Button) findViewById(R.id.dep_submit_button);
		dep_cancel=(Button) findViewById(R.id.dep_cancel_button);
		dep_clear=(Button) findViewById(R.id.dep_clear_button);*/
		
		findViewById(R.id.dep_submit_button).setOnClickListener(this);
		findViewById(R.id.dep_cancel_button).setOnClickListener(this);
	//	findViewById(R.id.dep_clear_button).setOnClickListener(this);
		
		 ArrayAdapter<String> aa = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, bikename);
		    aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		    dep_vehicle.setAdapter(aa);
		dep_dt_et.setFocusable(false);
		dep_dt_et.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showDialog(DATETIMESELECTOR_ID);
			}
		});
	}
	
	private DateSlider.OnDateSetListener mDateTimeSetListener =
	        new DateSlider.OnDateSetListener() {
	            public void onDateSet(DateSlider view, Calendar selectedDate) {
	                // update the dateText view with the corresponding date
	                int minute = selectedDate.get(Calendar.MINUTE) /
	                        TimeLabeler.MINUTEINTERVAL*TimeLabeler.MINUTEINTERVAL;
	                dep_dt_et.setText(String.format("%n%te. %tB %tY%n%tH:%02d",
	                        selectedDate, selectedDate, selectedDate, selectedDate, minute));
	            }
	    };
	    
	 @Override
	    protected Dialog onCreateDialog(int id) {
	        // this method is called after invoking 'showDialog' for the first time
	        // here we initiate the corresponding DateSlideSelector and return the dialog to its caller
	    	
	        final Calendar c = Calendar.getInstance();
	        switch (id) {
	        
	        case DATETIMESELECTOR_ID:
	            return new DateTimeSlider(this,mDateTimeSetListener,c);
	        }
	        return null;
	    }


	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.dep_submit_button:
			
			if(purpose_et.length()<=0)
			{
				purpose_et.setError("Enter trip purpose");
			}else
			if(client_et.length()<=0)
			{
				client_et.setError("Enter client you are going to meet");
			}else
			
			if (dep_dt_et.length()<=0) {
				dep_dt_et.setError("Enter Departure Date");
			}else
			
			if (dep_odo_et.length()<=0) {
				dep_odo_et.setError("Enter Dparture odometer reading");
			}else
			
			if (dep_loc_et.length()<=0) {
				dep_loc_et.setError("Enter Departure location");
			}else
			{

				purpose_ets=purpose_et.getText().toString();
				client_ets=client_et.getText().toString();
				dep_dt_ets=dep_dt_et.getText().toString();
				dep_odo_ets=dep_odo_et.getText().toString();
				dep_loc_ets=dep_loc_et.getText().toString();
				dep_notes_ets=dep_notes_et.getText().toString();
				
				
		    AlertDialog.Builder alertDialog= new AlertDialog.Builder(DepartureDetails.this);
			alertDialog.setIcon(R.drawable.et);
			alertDialog.setMessage("Save data to database");
			
			alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
				
				
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					//String depart=dep_radio;
					dh.open();
					long check=dh.departure(dep_radio, purpose_ets, client_ets, dep_vehicles, dep_dt_ets, dep_odo_ets, dep_loc_ets, dep_notes_ets);
					dh.close();
					if (check!=-1) {
						LayoutInflater inflater = getLayoutInflater();
				    	 View layout = inflater.inflate(R.layout.customtoast,
				    	                                (ViewGroup) findViewById(R.id.toast_layout_root));

				    	 TextView text = (TextView) layout.findViewById(R.id.text);
				    	 text.setText("Departure Details Saved to Success Fully");
		               text.setTextSize(20);
				    	 Toast toast = new Toast(getApplicationContext());
				    	 toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
				    	 toast.setDuration(Toast.LENGTH_LONG);
				    	 toast.setView(layout);
				    	 toast.show();
					
						finish();
						
					}else{
						LayoutInflater inflater = getLayoutInflater();
				    	 View layout = inflater.inflate(R.layout.customtoast,
				    	                                (ViewGroup) findViewById(R.id.toast_layout_root));

				    	 TextView text = (TextView) layout.findViewById(R.id.text);
				    	 text.setText("Sorry Departure Details Not Saved");
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
			
			alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
				
				
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
				
					Toast.makeText(getBaseContext(), "Cancelled", Toast.LENGTH_SHORT).show();
					finish();
				}
			});
			
			alertDialog.show();
			}
			break;

		case R.id.dep_cancel_button:
			Intent i= new Intent(DepartureDetails.this,MainBikeTrackerActivity.class);
			startActivity(i);
			finish();
			/*AlertDialog.Builder alertDialog2= new AlertDialog.Builder(DepartureDetails.this);
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
					finish();
				}
			});
					alertDialog2.show();*/
			
			break;
		/*case R.id.dep_business:
			if (dep_business.isSelected()) {
				dep_radio=dep_business.getText().toString();
				
			}
			
			break;
		case R.id.dep_personal:
			break;*/
			/*case R.id.dep_clear_button:
			AlertDialog.Builder alertDialog1= new AlertDialog.Builder(DepartureDetails.this);
			alertDialog1.setTitle("Alert");
			alertDialog1.setMessage("Do you want to clear all the data");
			alertDialog1.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
				
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					purpose_et.setText("");
					client_et.setText("");
					dep_dt_et.setText("");
					dep_odo_et.setText("");
					dep_loc_et.setText("");
					dep_notes_et.setText("");
						}
			});
			
			
			alertDialog1.setNegativeButton("No", new DialogInterface.OnClickListener() {
				
					public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
				
					Toast.makeText(getApplicationContext(), "Cancelled", Toast.LENGTH_SHORT).show();		
				}
			});
			
			alertDialog1.show();
			break;*/
			
	
		}
		
	}

	public void onItemSelected(AdapterView<?> parent, View arg1, int position,
			long arg3) {
		// TODO Auto-generated method stub
		dep_vehicles=parent.getItemAtPosition(position).toString();
	}

	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		// TODO Auto-generated method stub
		if (dep_business.isChecked()) {
			dep_radio=dep_business.getText().toString();
			//Toast.makeText(DepartureDetails.this, "buisness Radio Button"+dep_radio, Toast.LENGTH_SHORT).show();
		}else{
			dep_radio=dep_personal.getText().toString();
			//Toast.makeText(DepartureDetails.this, "personal Radio Button"+dep_radio, Toast.LENGTH_SHORT).show();
		
	}
	}

}
