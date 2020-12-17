package com.stratvave.biketracker.addvehicle;

import java.util.ArrayList;

import com.stratvave.biketracker.databases.DataHelper;
import com.stratvave.biketracker.main.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;


public class Bike extends Activity implements OnClickListener{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.bike);
		
	    findViewById(R.id.new_bike_button).setOnClickListener(this);
		findViewById(R.id.add_bike_purchasedetail).setOnClickListener(this);
		findViewById(R.id.add_bike_salesdetails).setOnClickListener(this);
		findViewById(R.id.view_bike_details).setOnClickListener(this);
		findViewById(R.id.update_bike_details).setOnClickListener(this);
		findViewById(R.id.delete_bike_details).setOnClickListener(this);
		
	}
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.new_bike_button:
			
			startActivity(new Intent(Bike.this,AddVehicle.class));
			break;
		case R.id.add_bike_purchasedetail:
			DataHelper db1=new DataHelper(this);
			 db1.open();
			ArrayList<String> arraylist1= db1.getBikeName();
			db1.close();
			if (arraylist1.isEmpty()) {
				LayoutInflater inflater = getLayoutInflater();
		    	 View layout = inflater.inflate(R.layout.customtoast,
		    	                                (ViewGroup) findViewById(R.id.toast_layout_root));

		    	 TextView text = (TextView) layout.findViewById(R.id.text);
		    	 text.setText("YOU NEED TO ADD VEHICLE DETAILS FIRST");
               text.setTextSize(20);
		    	 Toast toast = new Toast(getApplicationContext());
		    	 toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
		    	 toast.setDuration(Toast.LENGTH_LONG);
		    	 toast.setView(layout);
		    	 toast.show();
				
				
			
			} else{
			startActivity(new Intent(Bike.this,AddBikePurchaseData.class));
			}
			break;
		case R.id.add_bike_salesdetails:
			
			DataHelper db3=new DataHelper(this);
			 db3.open();
			ArrayList<String> arraylist3= db3.getBikeName();
			db3.close();
			if (arraylist3.isEmpty()) {
				LayoutInflater inflater = getLayoutInflater();
		    	 View layout = inflater.inflate(R.layout.customtoast,
		    	                                (ViewGroup) findViewById(R.id.toast_layout_root));

		    	 TextView text = (TextView) layout.findViewById(R.id.text);
		    	 text.setText("YOU NEED TO ADD VEHICLE DETAILS FIRST");
              text.setTextSize(20);
		    	 Toast toast = new Toast(getApplicationContext());
		    	 toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
		    	 toast.setDuration(Toast.LENGTH_LONG);
		    	 toast.setView(layout);
		    	 toast.show();
				;
				
			} else{
			startActivity(new Intent(Bike.this,AddBikeSalesData.class));
			}
			break;
		case R.id.view_bike_details:
			 DataHelper db=new DataHelper(this);
			 db.open();
			ArrayList<String> arraylist= db.getBikeName();
			db.close();
			if (arraylist.isEmpty()) {
				LayoutInflater inflater = getLayoutInflater();
		    	 View layout = inflater.inflate(R.layout.customtoast,
		    	                                (ViewGroup) findViewById(R.id.toast_layout_root));

		    	 TextView text = (TextView) layout.findViewById(R.id.text);
		    	 text.setText("YOU NEED TO ADD VEHICLE DETAILS FIRST");
              text.setTextSize(20);
		    	 Toast toast = new Toast(getApplicationContext());
		    	 toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
		    	 toast.setDuration(Toast.LENGTH_LONG);
		    	 toast.setView(layout);
		    	 toast.show();
				
			
			} else{
			startActivity(new Intent(Bike.this,ViewMyBikeDetails.class));
			}
			break;
		case R.id.update_bike_details:
			DataHelper db4=new DataHelper(this);
			 db4.open();
			ArrayList<String> arraylist4= db4.getBikeName();
			db4.close();
			if (arraylist4.isEmpty()) {
				LayoutInflater inflater = getLayoutInflater();
		    	 View layout = inflater.inflate(R.layout.customtoast,
		    	                                (ViewGroup) findViewById(R.id.toast_layout_root));

		    	 TextView text = (TextView) layout.findViewById(R.id.text);
		    	 text.setText("YOU NEED TO ADD VEHICLE DETAILS FIRST");
              text.setTextSize(20);
		    	 Toast toast = new Toast(getApplicationContext());
		    	 toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
		    	 toast.setDuration(Toast.LENGTH_LONG);
		    	 toast.setView(layout);
		    	 toast.show();
				
				
			} else{
				startActivity(new Intent(Bike.this,UpdateBikeDetails.class));
			}
			
			
			break;
		case R.id.delete_bike_details:
			DataHelper db2=new DataHelper(this);
			 db2.open();
			ArrayList<String> arraylist2= db2.getBikeName();
			db2.close();
			if (arraylist2.isEmpty()) {
				LayoutInflater inflater = getLayoutInflater();
		    	 View layout = inflater.inflate(R.layout.customtoast,
		    	                                (ViewGroup) findViewById(R.id.toast_layout_root));

		    	 TextView text = (TextView) layout.findViewById(R.id.text);
		    	 text.setText("YOU NEED TO ADD VEHICLE DETAILS FIRST");
              text.setTextSize(20);
		    	 Toast toast = new Toast(getApplicationContext());
		    	 toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
		    	 toast.setDuration(Toast.LENGTH_LONG);
		    	 toast.setView(layout);
		    	 toast.show();
				
			
			} else{
				startActivity(new Intent(Bike.this,DeleteBikeDetails.class));
			}
			break;

		
		}
	}

}
