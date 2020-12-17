package com.stratvave.biketracker.extras;

import java.util.ArrayList;

import org.xml.sax.DTDHandler;

import com.stratvave.biketracker.addvehicle.Bike;
import com.stratvave.biketracker.databases.DataHelper;
import com.stratvave.biketracker.main.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

public class ViewInsuranceDetails extends Activity implements OnClickListener{
	TextView insured_bike_name_et,
			incompany_name_address_et,
			policy_certificate_et,
			policy_issued_Office_et,
			policy_issue_date_et,
			policy_from_date_et,
			policy_upto_date_et,
			insured_name_et,
			insured_addressr_et,
			total_value_et,
			total_premimun_et;
	 String selected_bike;String[] users;
	 ArrayList<String> insurancebike;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		 requestWindowFeature(Window.FEATURE_NO_TITLE);
				setContentView(R.layout.viewisurancedetails);
				TextView tv=(TextView) findViewById(R.id.tv);
				tv.setText("View Your Insurance Details");
				
				insured_bike_name_et=(TextView) findViewById(R.id.insured_bike_name_et);
				incompany_name_address_et=(TextView) findViewById(R.id.incompany_name_address_et);
				policy_certificate_et=(TextView) findViewById(R.id.policy_certificate_et);
				policy_issued_Office_et=(TextView) findViewById(R.id.policy_issued_Office_et);
				policy_issue_date_et=(TextView) findViewById(R.id.policy_issue_date_et);
				policy_from_date_et=(TextView) findViewById(R.id.policy_from_date_et);
				policy_upto_date_et=(TextView) findViewById(R.id.policy_upto_date_et);
				insured_name_et=(TextView) findViewById(R.id.insured_name_et);
				insured_addressr_et=(TextView) findViewById(R.id.insured_addressr_et);
				total_value_et=(TextView) findViewById(R.id.total_value_et);
				total_premimun_et=(TextView) findViewById(R.id.total_premimun_et);
				
				
				findViewById(R.id.ok_button).setOnClickListener(this);
				findViewById(R.id.cancel_button).setOnClickListener(this);
				
				DataHelper datahelper=new DataHelper(this);
				datahelper.open();
				insurancebike=datahelper.getabikesofInsuranceDetails();
				datahelper.close();
				if(insurancebike.isEmpty()){
					Toast.makeText(ViewInsuranceDetails.this, "insurance details not added", Toast.LENGTH_SHORT).show();
					finish();
				}
				else{
					users = new String[insurancebike.size()];
					users = insurancebike.toArray(users);
					
					AlertDialog.Builder builder3=new AlertDialog.Builder(ViewInsuranceDetails.this);
					//builder3.setCancelable(false);
					
					  builder3.setTitle("Select your Bike").setItems(users, new DialogInterface.OnClickListener() {
				
					  @Override
				
					  public void onClick(DialogInterface dialog, int which) {
				
					// TODO Auto-generated method stub
						  selected_bike=insurancebike.get(which);
					//Toast.makeText(getApplicationContext(), "U Selected "+roadtaxdetails1.get(which), Toast.LENGTH_LONG).show();
						  DataHelper datahelper1=new DataHelper(ViewInsuranceDetails.this);
							datahelper1.open();
							ArrayList<String> insurancedetails= datahelper1.getInsuranceDetails(selected_bike);
							datahelper1.close();
							
							
							if (!insurancedetails.isEmpty()) {
								insured_bike_name_et.setText(":"+" "+insurancedetails.get(0).toString());
								incompany_name_address_et.setText(":"+" "+insurancedetails.get(1).toString());
								policy_certificate_et.setText(":"+" "+insurancedetails.get(2).toString());
								policy_issued_Office_et.setText(":"+" "+insurancedetails.get(3).toString());
								policy_issue_date_et.setText(":"+" "+insurancedetails.get(4).toString());
								policy_from_date_et.setText(":"+" "+insurancedetails.get(5).toString());
								policy_upto_date_et.setText(":"+" "+insurancedetails.get(6).toString());			
								insured_name_et.setText(":"+" "+insurancedetails.get(7).toString());
								insured_addressr_et.setText(":"+" "+insurancedetails.get(8).toString());			
								total_value_et.setText(":"+" "+insurancedetails.get(9).toString());
								total_premimun_et.setText(":"+" "+insurancedetails.get(10).toString());
								
							}
							
					  }
		  
		});
					  builder3.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								onBackPressed();
							}
						});

		  builder3.show();
				
			}
				
	
				}
	

	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.ok_button:
			finish();
			break;

         case R.id.cancel_button:
        	finish();
			break;
		}
		
	}
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		finish();
	}

}
