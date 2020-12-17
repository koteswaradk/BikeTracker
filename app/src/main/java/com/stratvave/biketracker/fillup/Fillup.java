package com.stratvave.biketracker.fillup;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import com.googlecode.android.widgets.DateSlider.AlternativeDateSlider;
import com.googlecode.android.widgets.DateSlider.CustomDateSlider;
import com.googlecode.android.widgets.DateSlider.DateSlider;
import com.googlecode.android.widgets.DateSlider.DateTimeSlider;
import com.googlecode.android.widgets.DateSlider.DefaultDateSlider;
import com.googlecode.android.widgets.DateSlider.MonthYearDateSlider;
import com.googlecode.android.widgets.DateSlider.TimeSlider;
import com.googlecode.android.widgets.DateSlider.labeler.TimeLabeler;
import com.stratvave.biketracker.databases.DataHelper;
import com.stratvave.biketracker.main.MainBikeTrackerActivity;
import com.stratvave.biketracker.main.R;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;


public class Fillup extends Activity implements OnClickListener,OnItemSelectedListener{
	
	static final int DATETIMESELECTOR_ID = 0;
	ImageButton submit_fillup, cancel_fillup,clear_fillup;
	
	ArrayList<String> vename;
	double total_cost_ets_int;
	EditText date_time_et,fuel_vol_et,price_lit_et,odo_read_et,total_cost_et,fuel_station_et,fuel_additive_et,fillup_note;
	
	Spinner fillup_type_spinner,vehicle_name_spinner,fuelcat_spinner,fueltype_spinner,fuelbrand_spinner,paymenttype_spinner;
	
	String date_time_ets, fuel_vol_ets, price_lit_ets, odo_read_ets, total_cost_ets, fuel_station_ets, fuel_additive_ets, fillup_notes,
			fillup_type_s, vehicle_name_s, fuelcat_s, fueltype_s, fuelbrand_s, paymenttype_s,String;
	private  DataHelper db;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.fillup);
		
		
		//button
		 findViewById(R.id.submit_fillup_button).setOnClickListener(this);
		 findViewById(R.id.cancel_fill_button).setOnClickListener(this);
		/* findViewById(R.id.clear_button).setOnClickListener(this);*/
		 
		 //spinner
		 fillup_type_spinner= (Spinner) findViewById(R.id.fillup_type_spinner);
		 vehicle_name_spinner=(Spinner) findViewById(R.id.vehicle_spinner);
		 fuelcat_spinner=(Spinner) findViewById(R.id.fuelcat_spinner);
		 fueltype_spinner=(Spinner) findViewById(R.id.fueltype_spinner);
		 fuelbrand_spinner=(Spinner) findViewById(R.id.fuelbrand_spinner);
		 paymenttype_spinner=(Spinner) findViewById(R.id.paymenttype_spinner);
		 
		 //edit text
		 date_time_et=(EditText) findViewById(R.id.date_time_et);
		 fuel_vol_et=(EditText) findViewById(R.id.fuel_vol_et);
		 price_lit_et =(EditText) findViewById(R.id.price_lit_et);
		 odo_read_et=(EditText) findViewById(R.id.odo_read_et);
		 total_cost_et=(EditText) findViewById(R.id.total_cost_et);
		 fuel_station_et=(EditText) findViewById(R.id.fuel_station_et);
		 fuel_additive_et=(EditText) findViewById(R.id.fuel_additive_et);
		 fillup_note=(EditText) findViewById(R.id.fillup_notes_et);
		
		 /*TAKING THE VALUE OF SPINNER AND EDIT TEXT TO STRING SO AS TO PASS TO THE DATABASE*/
		 
		 fillup_type_spinner.setOnItemSelectedListener(Fillup.this);	//FILL UP TYPE SPINNER
		 total_cost_et.setOnClickListener(this);
		 total_cost_et.setFocusable(false);
		 total_cost_et.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					 //showDialog(DATETIMESELECTOR_ID);
					 fuel_vol_ets=fuel_vol_et.getText().toString();
					 price_lit_ets=price_lit_et.getText().toString();
					 total_cost_ets_int=(Double.parseDouble(fuel_vol_ets))*(Double.parseDouble(price_lit_ets));
					 total_cost_ets= Double.toString(total_cost_ets_int);
					total_cost_et.setText(total_cost_ets);
					 //
				}
			});
		
		
		
		 date_time_et.setFocusable(false);
		 date_time_et.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				 showDialog(DATETIMESELECTOR_ID);
			}
		});
		 
		 vename=new ArrayList<String>();
		  db=new DataHelper(this);
		 db.open();
		ArrayList<String> arraylist= db.getBikeName();
		db.close();
	/*	 DataHelper db=new DataHelper(this);
	 * 
		 db.open();
		 Cursor cursor= db.getBikeDetails();
		 if (cursor!=null) {
			 if (cursor.moveToFirst()) {
				 for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext()){
					 int i=cursor.getColumnIndex("KEY_BikeName");
					 vename.add(cursor.getString(i));
					 
				 }
				
			}
			 if (cursor.moveToFirst()) {
			        do {
			        	vename.add(cursor.getString(cursor.getColumnIndex("KEY_BikeName"))); // "Title" is the field name(column) of the Table                 
			        } while (cursor.moveToNext());
			    }
			 System.out.println(vename);
		
			
		}*/
	
		 
		 vehicle_name_spinner.setOnItemSelectedListener(this);
		    ArrayAdapter<String> aa = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, arraylist);
		    aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		    vehicle_name_spinner.setAdapter(aa);
		 vehicle_name_spinner.setOnItemSelectedListener(new OnItemSelectedListener() {	//VEHICLE NAME SPINNER

			
			public void onItemSelected(AdapterView<?> parent, View arg1,
					int position, long arg3) {
				// TODO Auto-generated method stub
				vehicle_name_s=parent.getItemAtPosition(position).toString();
			}

			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		 
		 
		 
		 fuelcat_spinner.setOnItemSelectedListener(new OnItemSelectedListener() {		//FUEL CATEGORY SPINNER

			public void onItemSelected(AdapterView<?> parent, View arg1,
					int position, long arg3) {
				// TODO Auto-generated method stub
				fuelcat_s=parent.getItemAtPosition(position).toString();
			}

			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		 
		 fueltype_spinner.setOnItemSelectedListener(new OnItemSelectedListener() {		//FUEL TYPE SPINNER

			public void onItemSelected(AdapterView<?> parent, View arg1,
					int position, long arg3) {
				// TODO Auto-generated method stub
				fueltype_s=parent.getItemAtPosition(position).toString();
			}

			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		 
		 fuelbrand_spinner.setOnItemSelectedListener(new OnItemSelectedListener() {		//FUEL BRAND SPINNER

			public void onItemSelected(AdapterView<?> parent, View arg1,
					int position, long arg3) {
				// TODO Auto-generated method stub
				fuelbrand_s=parent.getItemAtPosition(position).toString();
			}

			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		 
		
		 
		 paymenttype_spinner.setOnItemSelectedListener(new OnItemSelectedListener() {		//PAYMENT TYPE SPINNER

			public void onItemSelected(AdapterView<?> parent, View arg1,
					int position, long arg3) {
				// TODO Auto-generated method stub
				paymenttype_s=parent.getItemAtPosition(position).toString();
			}

			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		 
		
		 
	}
	private DateSlider.OnDateSetListener mDateTimeSetListener =
	        new DateSlider.OnDateSetListener() {
	            public void onDateSet(DateSlider view, Calendar selectedDate) {
	                // update the dateText view with the corresponding date
	                int minute = selectedDate.get(Calendar.MINUTE) /
	                        TimeLabeler.MINUTEINTERVAL*TimeLabeler.MINUTEINTERVAL;
	                date_time_et.setText(String.format("%te. %tB %tY%n%tH:%02d",
	                        selectedDate, selectedDate, selectedDate, selectedDate, minute));
	                date_time_ets=String.format("%te. %tB %tY%n%tH:%02d",
	                        selectedDate, selectedDate, selectedDate, selectedDate, minute);
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
		case R.id.submit_fillup_button:
		if(date_time_et.length()<=0)
		{
			date_time_et.setError("Enter Date and Time");
		}else
		if(fuel_vol_et.length()<=0)
		{
			fuel_vol_et.setError("Enter Fuel Volume");
		}else
		
		if (price_lit_et.length()<=0) {
			price_lit_et.setError("Enter Price/Litre");
		}else
		
		if (odo_read_et.length()<=0) {
			odo_read_et.setError("Enter Odometer Reading");
		}/*else
		
		if (total_cost_et.length()<0) {
			total_cost_et.setError("Enter Totalcost Of Fuel");
		}*/else
		
		if (fuel_station_et.length()<=0) {
			fuel_station_et.setError("Enter the Fuelling Station");
		}else
		
		if (fuel_additive_et.length()<=0) {
			fuel_additive_et.setError("Enter Fuel Additive");
		}else
		
		{	
			 date_time_ets=date_time_et.getText().toString();						
			 fuel_vol_ets=fuel_vol_et.getText().toString();
			 price_lit_ets=price_lit_et.getText().toString();
			 odo_read_ets=odo_read_et.getText().toString();
			 total_cost_ets=total_cost_et.getText().toString();
			 fuel_additive_ets=fuel_additive_et.getText().toString();
			 fillup_notes=fillup_note.getText().toString();
			 fuel_station_ets=fuel_station_et.getText().toString();
			 
			
			/* total_cost_ets_int=(Integer.parseInt(fuel_vol_ets))*(Integer.parseInt(price_lit_ets));
			 total_cost_ets= Double.toString(total_cost_ets_int);*/
			AlertDialog.Builder alertDialog= new AlertDialog.Builder(Fillup.this);
			alertDialog.setIcon(R.drawable.et);
			alertDialog.setMessage("Save data to database");
			
			alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
				
				
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					
					db.open();
					db.fill_up(fillup_type_s, date_time_ets, fuel_vol_ets, price_lit_ets, odo_read_ets, total_cost_ets,
							   vehicle_name_s, fuelcat_s, fueltype_s, fuelbrand_s, fuel_station_ets, paymenttype_s, 
							   fuel_additive_ets, fillup_notes);
					db.close();
					LayoutInflater inflater = getLayoutInflater();
			    	 View layout = inflater.inflate(R.layout.customtoast,
			    	                                (ViewGroup) findViewById(R.id.toast_layout_root));

			    	 TextView text = (TextView) layout.findViewById(R.id.text);
			    	 text.setText("Your Fill Up Is Added");
	                  text.setTextSize(20);
			    	 Toast toast = new Toast(getApplicationContext());
			    	 toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
			    	 toast.setDuration(Toast.LENGTH_LONG);
			    	 toast.setView(layout);
			    	 toast.show();
					
				
					finish();
				}
			});
			
			alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
				
				
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
				
					//Toast.makeText(getBaseContext(), "Cancelled", Toast.LENGTH_SHORT).show();
					finish();
					
				}
			});
			
			alertDialog.show();
		}	
			break;
			
		case R.id.cancel_fill_button:
			finish();
			/*AlertDialog.Builder alertDialog2= new AlertDialog.Builder(Fillup.this);
			alertDialog2.setTitle("Cancel");
			alertDialog2.setMessage("Return to home page");
			
			alertDialog2.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
				
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
				
					Intent i= new Intent(Fillup.this,MainBikeTrackerActivity.class);
					startActivity(i);
				}
			});

			alertDialog2.setNegativeButton("No", new DialogInterface.OnClickListener() {
				
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					
				}
			});
					alertDialog2.show();*/
			
			break;	
		/*case R.id.clear_button:
			AlertDialog.Builder alertDialog1= new AlertDialog.Builder(Fillup.this);
			alertDialog1.setTitle("Alert");
			alertDialog1.setMessage("Do you want to clear all the data");
			alertDialog1.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
				
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					date_time_et.setText("");
					fuel_vol_et.setText("");
					price_lit_et.setText("");
					odo_read_et.setText("");
					total_cost_et.setText("");
					fuel_station_et.setText("");
					fuel_additive_et.setText("");
					fillup_note.setText("");
				
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
		
		}
	}

	public void onItemSelected(AdapterView<?> parent, View arg1, int position,
			long arg3) {
		// TODO Auto-generated method stub
		fillup_type_s=parent.getItemAtPosition(position).toString();
		
	}

	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}
	
	/*private void loadBikeName(){
		
		List<String> bike_name= dh.getName();
		
		ArrayAdapter<String> dataAdapter=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, bike_name);
		
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
		
		vehicle_name_spinner.setAdapter(dataAdapter);
	}*/
	
}

		
