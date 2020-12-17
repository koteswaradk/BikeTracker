package com.stratvave.biketracker.extras;


import java.util.ArrayList;

import com.stratvave.biketracker.databases.DataHelper;
import com.stratvave.biketracker.main.MainBikeTrackerActivity;
import com.stratvave.biketracker.main.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Toast;

public class Extras extends Activity implements OnClickListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.extras);
		findViewById(R.id.licence_details).setOnClickListener(this);
		findViewById(R.id.road_tax_details).setOnClickListener(this);
		findViewById(R.id.insurance_details).setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.licence_details:
			
			startActivity(new Intent(Extras.this, LIcenceDetails.class));
			break;
		case R.id.road_tax_details:
			startActivity(new Intent(Extras.this, RoadTaxDetails.class));
			break;
		case R.id.insurance_details:
			startActivity(new Intent(Extras.this, InsuranceDetails.class));
			break;
		}
//			LayoutInflater sms= LayoutInflater.from(Extras.this);
//			final View prompttouser =sms.inflate(R.layout.addcontactsviewalert,null);
//			
//			 alertDialog = new AlertDialog.Builder(Extras.this);
//	         alertDialog.setView(prompttouser);
//	        alertDialog.setIcon(R.drawable.aboutapp);
//	        alertDialog.show();
			/*final AlertDialog builder = new AlertDialog.Builder(Extras.this).create();
			 View  layout=new View(Extras.this);	
			 LayoutInflater inflater=LayoutInflater.from(Extras.this);
			 	//builder.requestWindowFeature(Window.FEATURE_NO_TITLE);
			 	builder.setCancelable(false);
			   // builder.getWindow().setBackgroundDrawable(new ColorDrawable(0));
			    // layout = inflater.inflate(R.layout.addextradetails, null);
			  
			    builder.setView(layout,0,0,0,0);
			   
		
				  layout.findViewById(R.id.ok_button).setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							
							//startActivity(new Intent(Extras.this, MainBikeTrackerActivity.class));
							DataHelper dh=new DataHelper(Extras.this);
							dh.open();
							ArrayList< String> al=dh.getBikeName();
							dh.close();
							Toast.makeText(Extras.this, "Ok clicked"+al, Toast.LENGTH_SHORT).show();
							builder.dismiss();
							
						}
					});
				    layout.findViewById(R.id.cancel_button).setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							Toast.makeText(Extras.this, "Cancel clicked", Toast.LENGTH_SHORT).show();
							builder.dismiss();
						}
					});
				   
				builder.show();
				
			}*/
			
		
		
	
	/*@Override
	protected Dialog onCreateDialog(int id) {
		
		// TODO Auto-generated method stub
		 AlertDialog.Builder builder = new AlertDialog.Builder(Extras.this);
		
		 LayoutInflater inflater = (LayoutInflater)Extras.this.getSystemService(LAYOUT_INFLATER_SERVICE);
		    
		    View layout = inflater.inflate(R.layout.custumalertextrasadd, null);
		    builder.setView(layout);
		switch (id) {
		case 0:
			  layout.findViewById(R.id.a_ok).setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Toast.makeText(Extras.this, "Ok clicked", Toast.LENGTH_SHORT).show();
					}
				});
			    layout.findViewById(R.id.a_cancel).setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Toast.makeText(Extras.this, "Cancel clicked", Toast.LENGTH_SHORT).show();
					}
				});
			
			break;

		default:
			break;
		}
		
		  
		   
		return builder.create();
	}*/
		
	}

}
