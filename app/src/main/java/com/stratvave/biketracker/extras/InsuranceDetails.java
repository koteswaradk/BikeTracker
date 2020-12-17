package com.stratvave.biketracker.extras;

import java.util.ArrayList;

import com.stratvave.biketracker.databases.DataHelper;
import com.stratvave.biketracker.main.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class InsuranceDetails extends Activity implements OnClickListener{
	
	 String selected_bike;String[] users;
	 ArrayList<String> insurancebike;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.insurancedetails);
		findViewById(R.id.add_insurance).setOnClickListener(this);
		findViewById(R.id.view_insurance).setOnClickListener(this);
		findViewById(R.id.update_insurance).setOnClickListener(this);
		findViewById(R.id.delete_insurance).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.add_insurance:
			startActivity(new Intent(InsuranceDetails.this, AddInsuranceDetails.class));
			/*final AlertDialog builder = new AlertDialog.Builder(Extras.this).create();
			 View  layout=new View(Extras.this);	
			 LayoutInflater inflater=LayoutInflater.from(Extras.this);
			 	builder.requestWindowFeature(Window.FEATURE_NO_TITLE);
			 	builder.setCancelable(false);
			    builder.getWindow().setBackgroundDrawable(new ColorDrawable(0));
			     layout = inflater.inflate(R.layout.addextradetails, null);
			  
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
				   
				builder.show();*/
			
			break;
		case R.id.view_insurance:
			startActivity(new Intent(InsuranceDetails.this, ViewInsuranceDetails.class));
			/*final AlertDialog builder = new AlertDialog.Builder(Extras.this).create();
			 View  layout=new View(Extras.this);	
			 LayoutInflater inflater=LayoutInflater.from(Extras.this);
			 	builder.requestWindowFeature(Window.FEATURE_NO_TITLE);
			 	builder.setCancelable(false);
			    builder.getWindow().setBackgroundDrawable(new ColorDrawable(0));
			     layout = inflater.inflate(R.layout.addextradetails, null);
			  
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
				   
				builder.show();*/
					
					break;
		case R.id.update_insurance:
			startActivity(new Intent(InsuranceDetails.this, UpdateInsuranceDetails.class));
			/*final AlertDialog builder = new AlertDialog.Builder(Extras.this).create();
			 View  layout=new View(Extras.this);	
			 LayoutInflater inflater=LayoutInflater.from(Extras.this);
			 	builder.requestWindowFeature(Window.FEATURE_NO_TITLE);
			 	builder.setCancelable(false);
			    builder.getWindow().setBackgroundDrawable(new ColorDrawable(0));
			     layout = inflater.inflate(R.layout.addextradetails, null);
			  
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
				   
				builder.show();*/
			
			break;
		case R.id.delete_insurance:
			DataHelper datahelper=new DataHelper(this);
			datahelper.open();
			insurancebike=datahelper.getabikesofInsuranceDetails();
			datahelper.close();
			if(insurancebike.isEmpty()){
				Toast.makeText(InsuranceDetails.this, "insurance details not added", Toast.LENGTH_LONG).show();
				//finish();
			}
			else{
				users = new String[insurancebike.size()];
				users = insurancebike.toArray(users);
				
				AlertDialog.Builder builder3=new AlertDialog.Builder(InsuranceDetails.this);
				//builder3.setCancelable(false);
				
				  builder3.setTitle("Select your Bike").setItems(users, new DialogInterface.OnClickListener() {
			
				  @Override
			
				  public void onClick(DialogInterface dialog, int which) {
			
				// TODO Auto-generated method stub
					  selected_bike=insurancebike.get(which);
				//Toast.makeText(getApplicationContext(), "U Selected "+roadtaxdetails1.get(which), Toast.LENGTH_LONG).show();
		
							DataHelper delete=new DataHelper(InsuranceDetails.this);
							delete.open();
							boolean condition=delete.deleteInsuranceDetails(selected_bike);
							delete.close();
							if (condition) {
								Toast.makeText(InsuranceDetails.this, "insurance details deleted succesfully ", Toast.LENGTH_LONG).show();
							}else{
								Toast.makeText(InsuranceDetails.this, "sorry insurance details not deleted  ", Toast.LENGTH_LONG).show();
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
			/*final AlertDialog builder = new AlertDialog.Builder(Extras.this).create();
			 View  layout=new View(Extras.this);	
			 LayoutInflater inflater=LayoutInflater.from(Extras.this);
			 	builder.requestWindowFeature(Window.FEATURE_NO_TITLE);
			 	builder.setCancelable(false);
			    builder.getWindow().setBackgroundDrawable(new ColorDrawable(0));
			     layout = inflater.inflate(R.layout.addextradetails, null);
			  
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
				   
				builder.show();*/
			
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
