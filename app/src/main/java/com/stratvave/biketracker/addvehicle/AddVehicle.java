package com.stratvave.biketracker.addvehicle;


import java.util.ArrayList;
import java.util.Calendar;

import com.googlecode.android.widgets.DateSlider.DateSlider;
import com.googlecode.android.widgets.DateSlider.DefaultDateSlider;
import com.googlecode.android.widgets.DateSlider.MonthYearDateSlider;
import com.stratvave.biketracker.databases.DataHelper;
import com.stratvave.biketracker.main.MainBikeTrackerActivity;
import com.stratvave.biketracker.main.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteConstraintException;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

public class AddVehicle extends Activity implements OnClickListener,OnItemSelectedListener{

	static final int MONTHYEARDATESELECTOR_ID = 0;
	static final int DEFAULTDATESELECTOR_ID=1;
	EditText name_et, a_regno, a_manu, a_bcolor, a_bmodel, a_yom, a_regdate, a_chassis, a_engineno, a_cc,a_fcupto; 
	Spinner a_vehicletype, a_fueltype;
	
	String name_ets,a_regnos,a_manus,a_bcolors,a_bmodels,a_yoms,a_regdates,a_chassiss,a_enginenos,a_ccs,a_fcuptos,
			a_vehicletypes,a_fueltypes;	
	ImageButton a_ok, a_cancel, a_show;
	ProgressDialog progressDialog;
	
	private DataHelper dh;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.addvehicle);	
		
		name_et= (EditText) findViewById(R.id.name_et);
		a_regno= (EditText) findViewById(R.id.registration_et);
		a_manu= (EditText) findViewById(R.id.manufacturer_et);
		a_bcolor= (EditText) findViewById(R.id.BikeColor_et);
		a_bmodel= (EditText) findViewById(R.id.BikeModel_et);
		a_yom= (EditText) findViewById(R.id.YearOfManufacture_et);
		a_regdate= (EditText) findViewById(R.id.RegistrationDate_et);
		a_chassis= (EditText) findViewById(R.id.ChassisNumber_et);
		a_engineno= (EditText) findViewById(R.id.EngineNumber_et);
		a_cc= (EditText) findViewById(R.id.CC_et);
		a_fcupto= (EditText) findViewById(R.id.FCupto_et);
		
		a_vehicletype=(Spinner) findViewById(R.id.vehicletype_spinner);
		a_fueltype=(Spinner) findViewById(R.id.fueltype_spinner);
		
		 findViewById(R.id.ok_button).setOnClickListener(this);
		 findViewById(R.id.cancel_button).setOnClickListener(this);
		/* findViewById(R.id.clear_button).setOnClickListener(this);		*/
		 
		a_vehicletype.setOnItemSelectedListener(AddVehicle.this);
		a_yom.setFocusable(false);
		a_regdate.setFocusable(false);
		
		a_yom.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// showDialog(DEFAULTDATESELECTOR_ID);
				 showDialog(MONTHYEARDATESELECTOR_ID);
			}
		});
		
		a_regdate.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showDialog(DEFAULTDATESELECTOR_ID);
			}
		});
		
		a_fueltype.setOnItemSelectedListener(new OnItemSelectedListener() {

			public void onItemSelected(AdapterView<?> parentfueltype, View arg1,
					int positionfueltype, long arg3) {
				// TODO Auto-generated method stub
				a_fueltypes=parentfueltype.getItemAtPosition(positionfueltype).toString();
				
			}

			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		 
	}
	private DateSlider.OnDateSetListener mDateSetListener =
	        new DateSlider.OnDateSetListener() {
	            public void onDateSet(DateSlider view, Calendar selectedDate) {
	                // update the dateText view with the corresponding date
	            	//Toast.makeText(AddVehicle.this, String.format("The chosen date:%n%te. %tB %tY", selectedDate, selectedDate, selectedDate), Toast.LENGTH_SHORT).show();
	               // dateText.setText();
	            	a_regdate.setText(String.format("%te. %tB %tY", selectedDate, selectedDate, selectedDate));
	            }
	    };
	 private DateSlider.OnDateSetListener mMonthYearSetListener =
		        new DateSlider.OnDateSetListener() {
		            public void onDateSet(DateSlider view, Calendar selectedDate) {
		                // update the dateText view with the corresponding date
		            	a_yom.setText(String.format("%tB %tY", selectedDate, selectedDate));
		            }
		    };

	    @Override
	    protected Dialog onCreateDialog(int id) {
	        // this method is called after invoking 'showDialog' for the first time
	        // here we initiate the corresponding DateSlideSelector and return the dialog to its caller
	    	
	    	// get today's date and time
	        final Calendar c = Calendar.getInstance();
	        
	        switch (id) {
	        case MONTHYEARDATESELECTOR_ID:
	        	 return new MonthYearDateSlider(this,mMonthYearSetListener,c);
			case DEFAULTDATESELECTOR_ID:
	            return new DefaultDateSlider(this,mDateSetListener,c);
	        }
	        return null;
	    }
	
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		
		case R.id.ok_button:
			
			
			if(name_et.length()<=0)
			{	name_et.setError(null);
				name_et.setError("Enter the name");				
			}else
			if(a_regno.length()<=0)
			{	
				name_et.setError(null);
				a_regno.setError("Enter the registration number");
			}else
			
			if (a_manu.length()<=0) {
				a_regno.setError(null);
				a_manu.setError(" Enter the manufacturer");
			}else
			
			if (a_bcolor.length()<=0) {
				a_manu.setError(null);
				a_bcolor.setError(" Enter Bike Color");
			}else
			
			if (a_bmodel.length()<=0) {
				a_bcolor.setError(null);
				a_bmodel.setError("Enter Bike Model");
			}else
			
			if (a_yom.length()<=0) {
				a_bmodel.setError(null);
				a_yom.setError("Year of Manufacture");
			}else
			
			if (a_regdate.length()<=0) {
				a_yom.setError(null);
				a_regdate.setError(" Enter Registration date");
			}else
			
			if (a_chassis.length()<=0) {
				a_regdate.setError(null);
				a_chassis.setError(" Chassis number");
			}else
			
			if (a_engineno.length()<=0) {
				a_chassis.setError(null);
				a_engineno.setError("Engine number");
			}else
			
			if (a_cc.length()<=0) {
				a_engineno.setError(null);
				a_cc.setError("Enter CC");
			}else
			
			if (a_fcupto.length()<=0) {
				a_cc.setError(null);
				a_fcupto.setError("Enter FC upto");
			}else{
				
			
			//	LayoutInflater sms= LayoutInflater.from(AddVehicle.this); 
			//	final View prompttouser =sms.inflate(R.layout.customalertdialog, null);
				AlertDialog alertDialog = new AlertDialog.Builder(
                        AddVehicle.this).create();
                 alertDialog.setIcon(R.drawable.icon);
 
		
			alertDialog.setTitle("Save");
			
			alertDialog.setMessage("Save details to database");
			
			alertDialog.setButton("Yes", new DialogInterface.OnClickListener() {
				
				/*String name_ets,a_regnos,a_manus,a_bcolors,a_bmodels,a_yoms,a_regdates,a_chassiss,a_enginenos,a_ccs,a_fcuptos,
				a_vehicletypes,a_fueltypes;*/
				
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					name_ets=name_et.getText().toString();
					a_regnos=a_regno.getText().toString();
					a_manus=a_manu.getText().toString();
					a_bcolors=a_bcolor.getText().toString();
					a_bmodels=a_bmodel.getText().toString();
					a_yoms=a_yom.getText().toString();
					a_regdates=a_regdate.getText().toString();
					a_chassiss=a_chassis.getText().toString();
					a_enginenos=a_engineno.getText().toString();
					a_ccs=a_cc.getText().toString();
					a_fcuptos=a_fcupto.getText().toString();
					
					/*this.dh=new DataHelper(AddVehiclethis);
					dh.open();
					ArrayList<String> arraylist1= dh.getBikeName();
					dh.close();
					if (arraylist1.isEmpty()) {
						Toast.makeText(AddVehicle.this, "YOU NEED TO ADD VEHICLE DETAILS FIRST", Toast.LENGTH_SHORT).show();
						//finish();
					} else{
					startActivity(new Intent(AddVehicle.this,AddBikePurchaseData.class));
					}*/
					dh=new DataHelper(AddVehicle.this);
					dh.open();
					Boolean check=dh.entryloginCheck(name_ets);
					dh.close();
						if (check) {
							Toast.makeText(getApplicationContext(), "Vehicle Detail For "+name_ets+" Alerady Exists"+" You Can Update Duplicate Details Are Not Allowed...", Toast.LENGTH_LONG).show();
							finish();
						}else{
							dh.open();
							long insertcheck=dh.addvehicle(name_ets, a_regnos, a_manus, a_vehicletypes, a_bcolors, a_bmodels, a_yoms, a_regdates, a_chassiss,
																					a_enginenos, a_fueltypes, a_ccs, a_fcuptos);
							dh.close();
							if (insertcheck!=-1) {
								Toast.makeText(getApplicationContext(), "Vehicle Detail is Added Success Fully...", Toast.LENGTH_LONG).show();
								finish();
								
							}else if (insertcheck==-1) {
								
								Toast.makeText(getApplicationContext(), "Sorry your Vehicle Detail is Not  Added...", Toast.LENGTH_LONG).show();
								name_et.setText("");
								a_regno.setText("");
								a_manu.setText("");
								a_bcolor.setText("");
								a_bmodel.setText("");
								a_yom.setText("");
								a_regdate.setText("");
								a_chassis.setText("");
								a_engineno.setText("");
								a_cc.setText("");
								a_fcupto.setText("");							
							}
							
						
				}
				}
			});
			
			alertDialog.setButton2("No", new DialogInterface.OnClickListener() {
				
				
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
				
					//Toast.makeText(getApplicationContext(), "Cancelled", Toast.LENGTH_SHORT).show();
					finish();
					
					
				}
			});
			
			alertDialog.show();
			}
			break;

		case R.id.cancel_button:
					// ask whether to cancel the entries. if yes go to main activity, else remain in the same page
			
			Intent i= new Intent(AddVehicle.this,MainBikeTrackerActivity.class);
			startActivity(i);
			finish();
			/*AlertDialog.Builder alertDialog2= new AlertDialog.Builder(AddVehicle.this);
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
		/*case R.id.clear_button:
					//clear all the data from the fields
			
			AlertDialog.Builder alertDialog1= new AlertDialog.Builder(AddVehicle.this);
			alertDialog1.setTitle("Alert");
			alertDialog1.setMessage("Do you want to clear all the data");
			alertDialog1.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
				
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					
					name_et.setText("");
					a_regno.setText("");
					a_manu.setText("");
					a_bcolor.setText("");
					a_bmodel.setText("");
					a_yom.setText("");
					a_regdate.setText("");
					a_chassis.setText("");
					a_engineno.setText("");
					a_cc.setText("");
					a_fcupto.setText("");	
						
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



	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		
	}



	public void onItemSelected(AdapterView<?> parent, View arg1, int position,
			long arg3) {
		// TODO Auto-generated method stub
		a_vehicletypes=parent.getItemAtPosition(position).toString();
	}



	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
