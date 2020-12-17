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
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class AddInsuranceDetails extends Activity implements OnClickListener{
	
	static final int DEFAULTDATESELECTOR_ID_0=0;
	static final int DEFAULTDATESELECTOR_ID_1=1;
	static final int DEFAULTDATESELECTOR_ID_2=2;
	
	 TextView titlebar;DataHelper dh;
	 Spinner vehicle_name_spinner;
	 EditText select_bikename_et,incompany_name_address_et,policy_certificate_et,
	          policy_issued_Office_et,policy_issue_date_et,policy_from_date_et,
	          policy_upto_date_et,insured_name_et,insured_addressr_et,total_premimun_et,total_value_et;
	 
	 String select_bikename_et_s,incompany_name_address_et_s,policy_certificate_et_s,
     policy_issued_Office_et_s,policy_issue_date_et_s,policy_from_date_et_s,
     policy_upto_date_et_s,insured_name_et_s,insured_addressr_et_s,total_premimun_et_s,total_value_et_s;
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.addinsurancedetails);
        titlebar=(TextView) findViewById(R.id.tv);
		
		titlebar.setText("Add Insurance Details ");
		
		//select_bikename_et=(EditText) findViewById(R.id.select_bikename_et);
		incompany_name_address_et=(EditText) findViewById(R.id.incompany_name_address_et);
		policy_certificate_et=(EditText) findViewById(R.id.policy_certificate_et);
		policy_issued_Office_et=(EditText) findViewById(R.id.policy_issued_Office_et);
		total_value_et=(EditText) findViewById(R.id.total_value_et);
		
		insured_name_et=(EditText) findViewById(R.id.insured_name_et);
		insured_addressr_et=(EditText) findViewById(R.id.insured_addressr_et);
		total_premimun_et=(EditText) findViewById(R.id.total_premimun_et);
		
		policy_issue_date_et=(EditText) findViewById(R.id.policy_issue_date_et);
		policy_from_date_et=(EditText) findViewById(R.id.policy_from_date_et);
		policy_upto_date_et=(EditText) findViewById(R.id.policy_upto_date_et);
		
		vehicle_name_spinner=(Spinner) findViewById(R.id.vehicle_spinner);
		
		policy_issue_date_et.setOnClickListener(this);
		policy_from_date_et.setOnClickListener(this);
		policy_upto_date_et.setOnClickListener(this);
		
		policy_issue_date_et.setFocusable(false);
		policy_from_date_et.setFocusable(false);
		policy_upto_date_et.setFocusable(false);
		
		findViewById(R.id.ok_button).setOnClickListener(this);
		findViewById(R.id.cancel_button).setOnClickListener(this);
		
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
			select_bikename_et_s=parent.getItemAtPosition(position).toString();
		}

		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub
			
		}
	});
	}
	 private DateSlider.OnDateSetListener mDateSetListener_0 =
		        new DateSlider.OnDateSetListener() {
		            public void onDateSet(DateSlider view, Calendar selectedDate) {
		                // update the dateText view with the corresponding date
		            	//Toast.makeText(AddVehicle.this, String.format("The chosen date:%n%te. %tB %tY", selectedDate, selectedDate, selectedDate), Toast.LENGTH_SHORT).show();
		               // dateText.setText();
		            	policy_issue_date_et.setText(String.format("%te. %tB %tY", selectedDate, selectedDate, selectedDate));
		            }
		    };
		    private DateSlider.OnDateSetListener mDateSetListener_1 =
			        new DateSlider.OnDateSetListener() {
			            public void onDateSet(DateSlider view, Calendar selectedDate) {
			                // update the dateText view with the corresponding date
			            	//Toast.makeText(AddVehicle.this, String.format("The chosen date:%n%te. %tB %tY", selectedDate, selectedDate, selectedDate), Toast.LENGTH_SHORT).show();
			               // dateText.setText();
			            	policy_from_date_et.setText(String.format("%te. %tB %tY", selectedDate, selectedDate, selectedDate));
			            }
			    };
			    private DateSlider.OnDateSetListener mDateSetListener_2 =
				        new DateSlider.OnDateSetListener() {
				            public void onDateSet(DateSlider view, Calendar selectedDate) {
				                // update the dateText view with the corresponding date
				            	//Toast.makeText(AddVehicle.this, String.format("The chosen date:%n%te. %tB %tY", selectedDate, selectedDate, selectedDate), Toast.LENGTH_SHORT).show();
				               // dateText.setText();
				            	policy_upto_date_et.setText(String.format("%te. %tB %tY", selectedDate, selectedDate, selectedDate));
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
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.policy_issue_date_et:
			showDialog(DEFAULTDATESELECTOR_ID_0);
			break;
		case R.id.policy_from_date_et:
			showDialog(DEFAULTDATESELECTOR_ID_1);
					break;
		case R.id.policy_upto_date_et:
			showDialog(DEFAULTDATESELECTOR_ID_2);
			break;
		case R.id.ok_button:
	
			/*if(select_bikename_et.length()<=0)
			{ 
				select_bikename_et.setError("Enter the name");				
			}else*/
			if(incompany_name_address_et.length()<=0)
			{	
				
				incompany_name_address_et.setError("Enter the registration number");
			}else
			if (policy_certificate_et.length()<=0) {
				
				policy_certificate_et.setError(" Enter Registration date");
			}else
			if (policy_issued_Office_et.length()<=0) {
				
				policy_issued_Office_et.setError("Enter Bike Model");
			}else
			
			if (policy_issue_date_et.length()<=0) {
				
				policy_issue_date_et.setError(" Enter Bike Color");
			}else
			if (policy_from_date_et.length()<=0) {
				
				policy_from_date_et.setError("Enter Bike Model");
			}else
			if (policy_upto_date_et.length()<=0) {
					
				policy_upto_date_et.setError("Enter Bike Model");
			}
			else
			if (insured_name_et.length()<=0) {
						
				insured_name_et.setError("Enter Bike Model");
			}
			else
			if (insured_addressr_et.length()<=0) {
								
				insured_addressr_et.setError("Enter Bike Model");
			}
			else
				if (total_value_et.length()<=0) {
										
					total_value_et.setError("Enter Bike Model");
					}
			else
			if (total_premimun_et.length()<=0) {
									
				total_premimun_et.setError("Enter Bike Model");
				}
			
			else{
				//select_bikename_et_s=select_bikename_et.getText().toString();
				incompany_name_address_et_s=incompany_name_address_et.getText().toString();
				policy_certificate_et_s=policy_certificate_et.getText().toString();
				policy_issued_Office_et_s=policy_issued_Office_et.getText().toString();
				policy_issue_date_et_s=policy_issue_date_et.getText().toString();
				policy_from_date_et_s=policy_from_date_et.getText().toString();
				policy_upto_date_et_s=policy_upto_date_et.getText().toString();				
				insured_name_et_s=insured_name_et.getText().toString();
				insured_addressr_et_s=insured_addressr_et.getText().toString();
				total_premimun_et_s=total_premimun_et.getText().toString();
				total_value_et_s=total_value_et.getText().toString();
				
				AlertDialog alertDialog = new AlertDialog.Builder(AddInsuranceDetails.this).create();
                alertDialog.setIcon(R.drawable.icon);
 
		
			alertDialog.setTitle("Save");
			
			alertDialog.setMessage("Save details to database");
			
			alertDialog.setButton("Yes", new DialogInterface.OnClickListener() {
				
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					
					dh=new DataHelper(AddInsuranceDetails.this);
							dh.open();
							
							
							long insertcheck=dh.addInsuranceDetails(select_bikename_et_s,incompany_name_address_et_s,policy_certificate_et_s,policy_issued_Office_et_s,policy_issue_date_et_s,policy_from_date_et_s,policy_upto_date_et_s,insured_name_et_s,insured_addressr_et_s,total_premimun_et_s,total_value_et_s);
							dh.close();
							if (insertcheck!=-1) {
								Toast.makeText(getApplicationContext(), "Insurance Detail is Added Success Fully...", Toast.LENGTH_LONG).show();
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
		
		}
		
	}

}
