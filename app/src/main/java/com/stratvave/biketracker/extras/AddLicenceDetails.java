package com.stratvave.biketracker.extras;

import java.util.Calendar;

import com.googlecode.android.widgets.DateSlider.DateSlider;
import com.googlecode.android.widgets.DateSlider.DefaultDateSlider;
import com.googlecode.android.widgets.DateSlider.MonthYearDateSlider;
import com.stratvave.biketracker.addvehicle.AddVehicle;
import com.stratvave.biketracker.databases.DataHelper;
import com.stratvave.biketracker.main.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class AddLicenceDetails extends Activity implements OnClickListener, OnItemSelectedListener {
	
	static final int DEFAULTDATESELECTOR_ID_0=0;
	static final int DEFAULTDATESELECTOR_ID_1=1;
	static final int DEFAULTDATESELECTOR_ID_2=2;
	static final int DEFAULTDATESELECTOR_ID_3=3;
	static final int DEFAULTDATESELECTOR_ID_4=4;
	DataHelper dh;
	TextView titlebar;
	Spinner vehiclecat_spinner,calss_of_vehcle_spinner;
	EditText owner_name_et,father_name_s_w_d_et,phonenumber_et,licence_number_et,licence_issue_date_et,valide_from_date_et,date_of_birth_et
	,valid_uptodate_et,registrationUpto_et,licence_with_effect_date_et,blood_group_et,addres_et,default_date_set;

	String owner_name_et_s,father_name_s_w_d_et_s,phonenumber_et_s,licence_number_et_s,licence_issue_date_et_s,valide_from_date_s,default_date,
	date_of_birth_et_s,valid_uptodate_et_s, registrationUpto_et_s, licence_with_effect_date_et_s,blood_group_et_s,catogory_s,addres_et_s,calss_of_vehcle_spinner_s;  
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.addlicence);
		titlebar=(TextView) findViewById(R.id.tv);
		
		 default_date_set=new EditText(this);
		titlebar.setText("Add Your Licence Details");
		
		findViewById(R.id.ok_button).setOnClickListener(this);
		findViewById(R.id.cancel_button).setOnClickListener(this);
		
		owner_name_et=(EditText) findViewById(R.id.owner_name_et);
		father_name_s_w_d_et=(EditText) findViewById(R.id.Father_Name_s_w_d_et);
		phonenumber_et=(EditText) findViewById(R.id.Phonenumber_et);
		licence_number_et=(EditText) findViewById(R.id.Licence_number_et);
		addres_et=(EditText) findViewById(R.id.Addres_et);
		
		licence_issue_date_et=(EditText) findViewById(R.id.licence_issue_date_et);
		valide_from_date_et=(EditText) findViewById(R.id.valide_from_date_et);
		date_of_birth_et=(EditText) findViewById(R.id.date_of_birth_et);
		valid_uptodate_et=(EditText) findViewById(R.id.valid_uptodate_et);
		licence_with_effect_date_et=(EditText) findViewById(R.id.licence_with_effect_date_et);
		
		registrationUpto_et=(EditText) findViewById(R.id.RegistrationUpto_et);
		blood_group_et=(EditText) findViewById(R.id.Blood_group_et);
		
		
		vehiclecat_spinner=(Spinner) findViewById(R.id.vehiclecat_spinner);
		calss_of_vehcle_spinner=(Spinner) findViewById(R.id.calss_of_vehcle_spinner);
		
		
		calss_of_vehcle_spinner.setOnItemSelectedListener(this);
		
		licence_issue_date_et.setOnClickListener(this);
		date_of_birth_et.setOnClickListener(this);
		valide_from_date_et.setOnClickListener(this);
		valid_uptodate_et.setOnClickListener(this);
        licence_with_effect_date_et.setOnClickListener(this);
        
		licence_issue_date_et.setFocusable(false);
		date_of_birth_et.setFocusable(false);
		valide_from_date_et.setFocusable(false);
		valid_uptodate_et.setFocusable(false);
		licence_with_effect_date_et.setFocusable(false);
		
		vehiclecat_spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View arg1, int position,
					long arg3) {
				// TODO Auto-generated method stub
				catogory_s=parent.getItemAtPosition(position).toString();
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}
/*	private DateSlider.OnDateSetListener mDateSetListener =
	        new DateSlider.OnDateSetListener() {
	            public void onDateSet(DateSlider view, Calendar selectedDate) {
	                // update the dateText view with the corresponding date
	            	//Toast.makeText(AddVehicle.this, String.format("The chosen date:%n%te. %tB %tY", selectedDate, selectedDate, selectedDate), Toast.LENGTH_SHORT).show();
	               // dateText.setText();
	            	default_date_set.setText(String.format("%te. %tB %tY", selectedDate, selectedDate, selectedDate));
	            }
	    };*/
	    private DateSlider.OnDateSetListener mDateSetListener_0 =
		        new DateSlider.OnDateSetListener() {
		            public void onDateSet(DateSlider view, Calendar selectedDate) {
		                // update the dateText view with the corresponding date
		            	//Toast.makeText(AddVehicle.this, String.format("The chosen date:%n%te. %tB %tY", selectedDate, selectedDate, selectedDate), Toast.LENGTH_SHORT).show();
		               // dateText.setText();
		            	licence_issue_date_et.setText(String.format("%te. %tB %tY", selectedDate, selectedDate, selectedDate));
		            }
		    };
		    private DateSlider.OnDateSetListener mDateSetListener_1 =
			        new DateSlider.OnDateSetListener() {
			            public void onDateSet(DateSlider view, Calendar selectedDate) {
			                // update the dateText view with the corresponding date
			            	//Toast.makeText(AddVehicle.this, String.format("The chosen date:%n%te. %tB %tY", selectedDate, selectedDate, selectedDate), Toast.LENGTH_SHORT).show();
			               // dateText.setText();
			            	date_of_birth_et.setText(String.format("%te. %tB %tY", selectedDate, selectedDate, selectedDate));
			            }
			    };
			    private DateSlider.OnDateSetListener mDateSetListener_2 =
				        new DateSlider.OnDateSetListener() {
				            public void onDateSet(DateSlider view, Calendar selectedDate) {
				                // update the dateText view with the corresponding date
				            	//Toast.makeText(AddVehicle.this, String.format("The chosen date:%n%te. %tB %tY", selectedDate, selectedDate, selectedDate), Toast.LENGTH_SHORT).show();
				               // dateText.setText();
				            	valide_from_date_et.setText(String.format("%te. %tB %tY", selectedDate, selectedDate, selectedDate));
				            }
				    };
				    private DateSlider.OnDateSetListener mDateSetListener_3 =
					        new DateSlider.OnDateSetListener() {
					            public void onDateSet(DateSlider view, Calendar selectedDate) {
					                // update the dateText view with the corresponding date
					            	//Toast.makeText(AddVehicle.this, String.format("The chosen date:%n%te. %tB %tY", selectedDate, selectedDate, selectedDate), Toast.LENGTH_SHORT).show();
					               // dateText.setText();
					            	valid_uptodate_et.setText(String.format("%te. %tB %tY", selectedDate, selectedDate, selectedDate));
					            }
					    };
					    private DateSlider.OnDateSetListener mDateSetListener_4 =
						        new DateSlider.OnDateSetListener() {
						            public void onDateSet(DateSlider view, Calendar selectedDate) {
						                // update the dateText view with the corresponding date
						            	//Toast.makeText(AddVehicle.this, String.format("The chosen date:%n%te. %tB %tY", selectedDate, selectedDate, selectedDate), Toast.LENGTH_SHORT).show();
						               // dateText.setText();
						            	licence_with_effect_date_et.setText(String.format("%te. %tB %tY", selectedDate, selectedDate, selectedDate));
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
			case DEFAULTDATESELECTOR_ID_3:
	            return new DefaultDateSlider(this,mDateSetListener_3,c);
			case DEFAULTDATESELECTOR_ID_4:
	            return new DefaultDateSlider(this,mDateSetListener_4,c);
	        }
	        return null;
	    }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.licence_issue_date_et:
			showDialog(DEFAULTDATESELECTOR_ID_0);
			
			break;
		case R.id.date_of_birth_et:
			showDialog(DEFAULTDATESELECTOR_ID_1);
			
			break;
		case R.id.valide_from_date_et:
			showDialog(DEFAULTDATESELECTOR_ID_2);
						break;
		case R.id.valid_uptodate_et:
			showDialog(DEFAULTDATESELECTOR_ID_3);
			
			break;
		case R.id.licence_with_effect_date_et:
			showDialog(DEFAULTDATESELECTOR_ID_4);
			
			break;
		case R.id.ok_button:
			if(owner_name_et.length()<=0)
			{ 
			  owner_name_et.setError("Enter the name");				
			}else
			if(father_name_s_w_d_et.length()<=0)
			{	
				
				father_name_s_w_d_et.setError("Enter the registration number");
			}else
			if (date_of_birth_et.length()<=0) {
				
				date_of_birth_et.setError(" Enter Registration date");
			}else
			if (licence_number_et.length()<=0) {
				
				licence_number_et.setError("Enter Bike Model");
			}else
			
			if (blood_group_et.length()<=0) {
				
				blood_group_et.setError(" Enter Bike Color");
			}else
			if (addres_et.length()<=0) {
				
				addres_et.setError("Enter Bike Model");
			}
			else
				
				if (phonenumber_et.length()<=0) {
					
					phonenumber_et.setError(" Enter the manufacturer");
				}else
			if (licence_number_et.length()<=0) {
				
				licence_number_et.setError("Enter Bike Model");
			}else
			
			if (licence_issue_date_et.length()<=0) {
			
				licence_issue_date_et.setError("Enter Bike Model");
			}else
			
			if (valide_from_date_et.length()<=0) {
				
				valide_from_date_et.setError("Year of Manufacture");
			}else
			
		
			
			if (valid_uptodate_et.length()<=0) {
				
				valid_uptodate_et.setError(" Chassis number");
			}else
			
			if (registrationUpto_et.length()<=0) {
				
				registrationUpto_et.setError("Engine number");
			}else
			
			if (licence_with_effect_date_et.length()<=0) {
				
				licence_with_effect_date_et.setError("Enter CC");
			}else
			
			{
				
			
				//LayoutInflater sms= LayoutInflater.from(AddLicenceDetails.this); 
				//final View prompttouser =sms.inflate(R.layout.customalertdialog, null);
				AlertDialog alertDialog = new AlertDialog.Builder(
						AddLicenceDetails.this).create();
                 alertDialog.setIcon(R.drawable.icon);
 
		
			alertDialog.setTitle("Save");
			
			alertDialog.setMessage("Save details to database");
			
			alertDialog.setButton("Yes", new DialogInterface.OnClickListener() {
				
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					owner_name_et_s=owner_name_et.getText().toString();
					father_name_s_w_d_et_s=father_name_s_w_d_et.getText().toString();
					phonenumber_et_s=phonenumber_et.getText().toString();
					blood_group_et_s=blood_group_et.getText().toString();
					licence_number_et_s=licence_number_et.getText().toString();
					addres_et_s=addres_et.getText().toString();
					licence_issue_date_et_s=licence_issue_date_et.getText().toString();
					valide_from_date_s=valide_from_date_et.getText().toString();
					date_of_birth_et_s=date_of_birth_et.getText().toString();
					valid_uptodate_et_s=valid_uptodate_et.getText().toString();
					registrationUpto_et_s=registrationUpto_et.getText().toString();
					licence_with_effect_date_et_s=licence_with_effect_date_et.getText().toString();
					dh=new DataHelper(AddLicenceDetails.this);
							dh.open();
							long insertcheck=dh.addlicencedetails(owner_name_et_s, father_name_s_w_d_et_s, date_of_birth_et_s, catogory_s,blood_group_et_s,
									addres_et_s,phonenumber_et_s, licence_number_et_s,licence_issue_date_et_s,valide_from_date_s, valid_uptodate_et_s,calss_of_vehcle_spinner_s, 
									registrationUpto_et_s,licence_with_effect_date_et_s);
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

		}
		
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View arg1, int position,
			long arg3) {
		// TODO Auto-generated method stub
		calss_of_vehcle_spinner_s=parent.getItemAtPosition(position).toString();
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}

}




