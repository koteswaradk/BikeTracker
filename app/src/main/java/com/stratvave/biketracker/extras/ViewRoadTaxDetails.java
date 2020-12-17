package com.stratvave.biketracker.extras;

import java.util.ArrayList;

import com.stratvave.biketracker.databases.DataHelper;
import com.stratvave.biketracker.main.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

public class ViewRoadTaxDetails extends Activity implements OnClickListener{
	
	TextView bike_name_et,serialnumber_et,date_of_birth_et,amount_payed_as_tax_et,period_from_et,
	Period_up_to_et,office_of_tax_payed_et;
	String selected_bike;String[] users;
//	String arr[]={"jhskjdhs","shdjshkjdh"};
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.viewroadtaxdetails);
		
		bike_name_et=(TextView) findViewById(R.id.bike_name_et);
		serialnumber_et=(TextView) findViewById(R.id.serialnumber_et);
		date_of_birth_et=(TextView) findViewById(R.id.date_of_birth_et);
		office_of_tax_payed_et=(TextView) findViewById(R.id.office_of_tax_payed_et);
		amount_payed_as_tax_et=(TextView) findViewById(R.id.amount_payed_as_tax_et);
		period_from_et=(TextView) findViewById(R.id.period_from_et);
		Period_up_to_et=(TextView) findViewById(R.id.Period_up_to_et);
		
		findViewById(R.id.ok_button).setOnClickListener(this);
		findViewById(R.id.cancel_button).setOnClickListener(this);
		
		DataHelper datahelper1=new DataHelper(this);
		datahelper1.open();
		final ArrayList<String> roadtaxdetails1=datahelper1.getabikesofRoadaTaxDetails();
		datahelper1.close();
		if (!roadtaxdetails1.isEmpty()) {
				Toast.makeText(ViewRoadTaxDetails.this, ""+roadtaxdetails1, Toast.LENGTH_SHORT).show();
				users = new String[roadtaxdetails1.size()];
				users = roadtaxdetails1.toArray(users);
				
				AlertDialog.Builder builder3=new AlertDialog.Builder(ViewRoadTaxDetails.this);
				//builder3.setCancelable(false);
				
				  builder3.setTitle("Select your Bike").setItems(users, new DialogInterface.OnClickListener() {
			
				  @Override
			
				  public void onClick(DialogInterface dialog, int which) {
			
				// TODO Auto-generated method stub
					  selected_bike=roadtaxdetails1.get(which);
				//Toast.makeText(getApplicationContext(), "U Selected "+roadtaxdetails1.get(which), Toast.LENGTH_LONG).show();
				  DataHelper datahelper=new DataHelper(ViewRoadTaxDetails.this);
					datahelper.open();
					ArrayList<String> roadtaxdetails=datahelper.getaRoadaTaxDetails(selected_bike);
					datahelper.close();
					Toast.makeText(getApplicationContext(), "U Selected "+roadtaxdetails, Toast.LENGTH_LONG).show();
					bike_name_et.setText(roadtaxdetails.get(0).toString());
					serialnumber_et.setText(roadtaxdetails.get(1).toString());
					date_of_birth_et.setText(roadtaxdetails.get(2).toString());
					office_of_tax_payed_et.setText(roadtaxdetails.get(3).toString());
					amount_payed_as_tax_et.setText(roadtaxdetails.get(4).toString());
					period_from_et.setText(roadtaxdetails.get(5).toString());
					Period_up_to_et.setText(roadtaxdetails.get(6).toString());
	  
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
	 
			
		}else{
			
			Toast.makeText(ViewRoadTaxDetails.this, "road tax details  not added", Toast.LENGTH_SHORT).show();
			finish();
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
