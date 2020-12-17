package com.stratvave.biketracker.extras;



import java.util.ArrayList;

import com.stratvave.biketracker.databases.DataHelper;
import com.stratvave.biketracker.main.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Toast;


public class LIcenceDetails extends Activity implements OnClickListener{
	//RelativeLayout rl;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.licencedetails);
		findViewById(R.id.add_licence).setOnClickListener(this);
		findViewById(R.id.view_licence).setOnClickListener(this);
		findViewById(R.id.update_licence).setOnClickListener(this);
		findViewById(R.id.delete_licence).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		//rl.setVisibility(View.VISIBLE);
		switch (v.getId()) {
		case R.id.add_licence:
			startActivity(new Intent(LIcenceDetails.this, AddLicenceDetails.class));
			/*final AlertDialog builder = new AlertDialog.Builder(LIcenceDetails.this).create();
			 View  layout=new View(LIcenceDetails.this);	
			 LayoutInflater inflater=LayoutInflater.from(LIcenceDetails.this);
			 	builder.requestWindowFeature(Window.FEATURE_NO_TITLE);
			 	builder.setCancelable(false);
			    builder.getWindow().setBackgroundDrawable(new ColorDrawable(0));
			     layout = inflater.inflate(R.layout.addlicence, null);
			  
			    builder.setView(layout,0,0,0,0);
			   
		
				  layout.findViewById(R.id.ok_button).setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							
							//startActivity(new Intent(Extras.this, MainBikeTrackerActivity.class));
							DataHelper dh=new DataHelper(LIcenceDetails.this);
							dh.open();
							ArrayList< String> al=dh.getBikeName();
							dh.close();
							Toast.makeText(LIcenceDetails.this, "Ok clicked"+al, Toast.LENGTH_SHORT).show();
							builder.dismiss();
							
						}
					});
				    layout.findViewById(R.id.cancel_button).setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							Toast.makeText(LIcenceDetails.this, "Cancel clicked", Toast.LENGTH_SHORT).show();
							builder.dismiss();
						}
					});
				   
				builder.show();*/
			
			break;
		case R.id.view_licence:
			startActivity(new Intent(LIcenceDetails.this, ViewLicenceDetails.class));
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
		case R.id.update_licence:
			startActivity(new Intent(LIcenceDetails.this, UpdateLIcenceDetails.class));
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
		//case R.id.delete_licence:
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
			
			//break;
		case R.id.delete_licence:
			
			DataHelper datahelper=new DataHelper(this);
			datahelper.open();
			ArrayList<String> licencelist =datahelper.getlicencedetails();
			datahelper.close();
			
			if(!licencelist.isEmpty()){
				DataHelper dataHelper=new DataHelper(this);
				dataHelper.open();
				 boolean licencedeleted= dataHelper.deletelicenceDetails();
				dataHelper.close();
				if (licencedeleted) {
					Toast.makeText(LIcenceDetails.this, "licence details deleted ", Toast.LENGTH_SHORT).show();
				}else {
					Toast.makeText(LIcenceDetails.this, "licence details deleted ", Toast.LENGTH_SHORT).show();
				}
			}else {
				Toast.makeText(LIcenceDetails.this, "licence details not Added ", Toast.LENGTH_SHORT).show();
				//finish();
			}
			
			
			break;
	
		}
	}

}
