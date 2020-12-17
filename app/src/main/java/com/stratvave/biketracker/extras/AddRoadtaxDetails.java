package com.stratvave.biketracker.extras;

import java.util.ArrayList;
import java.util.Calendar;

import com.googlecode.android.widgets.DateSlider.DateSlider;
import com.googlecode.android.widgets.DateSlider.DefaultDateSlider;
import com.stratvave.biketracker.databases.DataHelper;
import com.stratvave.biketracker.main.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class AddRoadtaxDetails extends Activity implements OnClickListener {
	
	static final int DEFAULTDATESELECTOR_ID_0=0;
	static final int DEFAULTDATESELECTOR_ID_1=1;
	static final int DEFAULTDATESELECTOR_ID_2=2;
	 EditText bike_name_et,serialnumber_et,date_tax_pay_et,amount_payed_as_tax_et,period_from_et,Period_up_to_et,office_of_tax_payed_et;
	 String bike_name_et_s,serialnumber_et_s,date_tax_pay_et_s,amount_payed_as_tax_et_s,period_from_et_s,Period_up_to_et_s,office_of_tax_payed_et_s;
	 TextView titlebar;DataHelper dh;
	 Spinner vehicle_name_spinner;
	 @Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.addroadtaxdetails);
		titlebar=(TextView) findViewById(R.id.tv);
		
		titlebar.setText("Add Your Roadtax Details Details");
		
		vehicle_name_spinner=(Spinner) findViewById(R.id.bike_name_et);
		serialnumber_et=(EditText) findViewById(R.id.serialnumber_et);
		amount_payed_as_tax_et=(EditText) findViewById(R.id.amount_payed_as_tax_et);
		office_of_tax_payed_et=(EditText) findViewById(R.id.office_of_tax_payed_et);
		
		date_tax_pay_et=(EditText) findViewById(R.id.date_tax_pay_et);
		period_from_et=(EditText) findViewById(R.id.period_from_et);
		Period_up_to_et=(EditText) findViewById(R.id.Period_up_to_et);
		
		findViewById(R.id.ok_button).setOnClickListener(this);
		findViewById(R.id.cancel_button).setOnClickListener(this);
		date_tax_pay_et.setOnClickListener(this);
		date_tax_pay_et.setFocusable(false);
		period_from_et.setFocusable(false);
		period_from_et.setOnClickListener(this);
		Period_up_to_et.setFocusable(false);
		Period_up_to_et.setOnClickListener(this);
		//vehicle_name_spinner.setOnItemSelectedListener(this);
		DataHelper db=new DataHelper(this);
		 db.open();
		ArrayList<String> arraylist= db.getBikeName();
		db.close();
	    ArrayAdapter<String> aa = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, arraylist);
	    aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    vehicle_name_spinner.setAdapter(aa);
	 vehicle_name_spinner.setOnItemSelectedListener(new OnItemSelectedListener() {	//VEHICLE NAME SPINNER

		
		public void onItemSelected(AdapterView<?> parent, View arg1,
				int position, long arg3) {
			// TODO Auto-generated method stub
			bike_name_et_s=parent.getItemAtPosition(position).toString();
		}

		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub
			
		}
	});
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.ok_button:
			if(serialnumber_et.length()<=0)
			{	
				
				serialnumber_et.setError("Enter the registration number");
			}else
			if (amount_payed_as_tax_et.length()<=0) {
				
				amount_payed_as_tax_et.setError(" Enter Registration date");
			}else
			if (date_tax_pay_et.length()<=0) {
				
				date_tax_pay_et.setError("Enter Bike Model");
			}else
			
			if (period_from_et.length()<=0) {
				
				period_from_et.setError(" Enter Bike Color");
			}else
			if (Period_up_to_et.length()<=0) {
				
				Period_up_to_et.setError("Enter Bike Model");
			}
			else{
				
				//bike_name_et_s=bike_name_et.getText().toString();
				serialnumber_et_s=serialnumber_et.getText().toString();
				
				date_tax_pay_et_s=date_tax_pay_et.getText().toString();
				period_from_et_s=period_from_et.getText().toString();
				Period_up_to_et_s=Period_up_to_et.getText().toString();
				amount_payed_as_tax_et_s=amount_payed_as_tax_et.getText().toString();
				office_of_tax_payed_et_s=office_of_tax_payed_et.getText().toString();
				AlertDialog alertDialog = new AlertDialog.Builder(AddRoadtaxDetails.this).create();
                alertDialog.setIcon(R.drawable.icon);
 
		
			alertDialog.setTitle("Save");
			
			alertDialog.setMessage("Save details to database");
			
			alertDialog.setButton("Yes", new DialogInterface.OnClickListener() {
				
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					
					dh=new DataHelper(AddRoadtaxDetails.this);
							dh.open();
							
							
							long insertcheck=dh.addRoadTaxDetails(bike_name_et_s,serialnumber_et_s,date_tax_pay_et_s,office_of_tax_payed_et_s,amount_payed_as_tax_et_s,period_from_et_s,Period_up_to_et_s);
							dh.close();
							if (insertcheck!=-1) {
								Toast.makeText(getApplicationContext(), "Licence Detail is Added Success Fully...", Toast.LENGTH_LONG).show();
								finish();
								
							}else if (insertcheck==-1) {
								
								Toast.makeText(getApplicationContext(), "Sorry your  Detail is Not  Added...", Toast.LENGTH_LONG).show();
													
							}
							
						
				//}
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
			finish();
			break;
		case R.id.date_tax_pay_et:
			showDialog(DEFAULTDATESELECTOR_ID_0);
			
			break;
		case R.id.period_from_et:
			showDialog(DEFAULTDATESELECTOR_ID_1);
			
			break;
		case R.id.Period_up_to_et:
			showDialog(DEFAULTDATESELECTOR_ID_2);
						break;

		
		}
		
	}
	 private DateSlider.OnDateSetListener mDateSetListener_0 =
		        new DateSlider.OnDateSetListener() {
		            public void onDateSet(DateSlider view, Calendar selectedDate) {
		                // update the dateText view with the corresponding date
		            	//Toast.makeText(AddVehicle.this, String.format("The chosen date:%n%te. %tB %tY", selectedDate, selectedDate, selectedDate), Toast.LENGTH_SHORT).show();
		               // dateText.setText();
		            	date_tax_pay_et.setText(String.format("%te. %tB %tY", selectedDate, selectedDate, selectedDate));
		            }
		    };
		    private DateSlider.OnDateSetListener mDateSetListener_1 =
			        new DateSlider.OnDateSetListener() {
			            public void onDateSet(DateSlider view, Calendar selectedDate) {
			                // update the dateText view with the corresponding date
			            	//Toast.makeText(AddVehicle.this, String.format("The chosen date:%n%te. %tB %tY", selectedDate, selectedDate, selectedDate), Toast.LENGTH_SHORT).show();
			               // dateText.setText();
			            	period_from_et.setText(String.format("%te. %tB %tY", selectedDate, selectedDate, selectedDate));
			            }
			    };
			    private DateSlider.OnDateSetListener mDateSetListener_2 =
				        new DateSlider.OnDateSetListener() {
				            public void onDateSet(DateSlider view, Calendar selectedDate) {
				                // update the dateText view with the corresponding date
				            	//Toast.makeText(AddVehicle.this, String.format("The chosen date:%n%te. %tB %tY", selectedDate, selectedDate, selectedDate), Toast.LENGTH_SHORT).show();
				               // dateText.setText();
				            	Period_up_to_et.setText(String.format("%te. %tB %tY", selectedDate, selectedDate, selectedDate));
				            }
				    };
	 @Override
	    protected Dialog onCreateDialog(int id) {
	        // this method is called after invoking 'showDialog' for the first time
	        // here we initiate the corresponding DateSlideSelector and return the dialog to its caller
	    	
	    	// get today's date and time
	        final Calendar c = Calendar.getInstance();
	        
	        switch (id) {
			case DEFAULTDATESELECTOR_ID_0:
	            return new DefaultDateSlider(this,mDateSetListener_0,c);
			case DEFAULTDATESELECTOR_ID_1:
	            return new DefaultDateSlider(this,mDateSetListener_1,c);
			case DEFAULTDATESELECTOR_ID_2:
	            return new DefaultDateSlider(this,mDateSetListener_2,c);
		
	        }
	        return null;
	    }

}
