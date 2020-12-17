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

public class RoadTaxDetails extends Activity implements OnClickListener{
	String selected_bike;String[] users;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.roadtaxdetails);
		findViewById(R.id.add_roadtax).setOnClickListener(this);
		findViewById(R.id.view_roadtax).setOnClickListener(this);
		findViewById(R.id.update_roadtax).setOnClickListener(this);
		findViewById(R.id.delete_roadtax).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.add_roadtax:
			startActivity(new Intent(RoadTaxDetails.this, AddRoadtaxDetails.class));
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
		case R.id.view_roadtax:
			startActivity(new Intent(RoadTaxDetails.this, ViewRoadTaxDetails.class));
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
		case R.id.update_roadtax:
			startActivity(new Intent(RoadTaxDetails.this, UpdateRoadTaxDetails.class));
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
		case R.id.delete_roadtax:
			

			DataHelper datahelper1=new DataHelper(this);
			datahelper1.open();
			final ArrayList<String> roadtaxdetails1=datahelper1.getabikesofRoadaTaxDetails();
			datahelper1.close();
			if (!roadtaxdetails1.isEmpty()) {
				
				users = new String[roadtaxdetails1.size()];
				users = roadtaxdetails1.toArray(users);
				
				AlertDialog.Builder builder3=new AlertDialog.Builder(RoadTaxDetails.this);
				//builder3.setCancelable(false);
				
				  builder3.setTitle("Select your Bike").setItems(users, new DialogInterface.OnClickListener() {
			
				  @Override
			
				  public void onClick(DialogInterface dialog, int which) {
			
				// TODO Auto-generated method stub
					  selected_bike=roadtaxdetails1.get(which);
					  DataHelper dataHelper=new DataHelper(RoadTaxDetails.this);
					  dataHelper.open();
					 boolean deleted= dataHelper.deleteRoadtaxDetails(selected_bike);
					  dataHelper.close();
					  if (deleted) {
						  Toast.makeText(RoadTaxDetails.this,"road tax details of bike  "+selected_bike+"  deleted success fully", Toast.LENGTH_SHORT).show();
					}else {
						Toast.makeText(RoadTaxDetails.this,"sorry road tax details of bike"+selected_bike+" not deleted", Toast.LENGTH_SHORT).show();
					}
				//Toast.makeText(getApplicationContext(), "U Selected "+roadtaxdetails1.get(which), Toast.LENGTH_LONG).show();
				 /* DataHelper datahelper=new DataHelper(ViewRoadTaxDetails.this);
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
					Period_up_to_et.setText(roadtaxdetails.get(6).toString());*/
	  
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
				
			}else {
				Toast.makeText(RoadTaxDetails.this,"road tax details not added", Toast.LENGTH_SHORT).show();
				//finish();
				
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
