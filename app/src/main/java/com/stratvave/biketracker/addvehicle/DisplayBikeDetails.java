package com.stratvave.biketracker.addvehicle;

import java.util.ArrayList;

import com.stratvave.biketracker.databases.DataHelper;
import com.stratvave.biketracker.main.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

public class DisplayBikeDetails extends Activity implements OnClickListener{

	TextView text_bikedetails,etname_set,registration_set,manufacturer_set,vehicletype_set,BikeColor_set,BikeModel_set,YearOfManufacture_set,
	RegistrationDate_set,ChassisNumber_set,EngineNumber_set,fueltype_set,CC_set,FCupto_set,purchase_date_set,purchase_price_set,purchase_notes_set;;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.displaybikedetails);
		
				findViewById(R.id.ok_button).setOnClickListener(this);
				text_bikedetails=(TextView) findViewById(R.id.text_bikedetails);
			
				etname_set=(TextView) findViewById(R.id.name_set);
				registration_set=(TextView) findViewById(R.id.registration_set);
				manufacturer_set=(TextView) findViewById(R.id.manufacturer_set);
				vehicletype_set=(TextView) findViewById(R.id.vehicletype_set);
				BikeColor_set=(TextView) findViewById(R.id.BikeColor_set);
				BikeModel_set=(TextView) findViewById(R.id.BikeModel_set);
				YearOfManufacture_set=(TextView) findViewById(R.id.YearOfManufacture_set);
				RegistrationDate_set=(TextView) findViewById(R.id.RegistrationDate_set);
				ChassisNumber_set=(TextView) findViewById(R.id.ChassisNumber_set);
				EngineNumber_set=(TextView) findViewById(R.id.EngineNumber_set);
				fueltype_set=(TextView) findViewById(R.id.fueltype_set);
				CC_set=(TextView) findViewById(R.id.CC_set);
				FCupto_set=(TextView) findViewById(R.id.FCupto_set);
				
				purchase_date_set=(TextView) findViewById(R.id.purchase_date_set);
				purchase_price_set=(TextView) findViewById(R.id.purchase_price_set);
				purchase_notes_set=(TextView) findViewById(R.id.purchase_notes_set);
				
				
				Intent data=getIntent();
			    String name=data.getStringExtra("bikename");
	
				text_bikedetails.setText("Bike"+" "+name+" "+"Details");
				DataHelper databasehelper=new DataHelper(this);
				databasehelper.open();
				ArrayList<String> bikedetails=databasehelper.getBikeDetails(name);
				databasehelper.close();
				
				DataHelper databasehelper1=new DataHelper(this);
				databasehelper.open();
				ArrayList<String> bikedetails1=databasehelper.getBikPurchaseeDetails(name);
				databasehelper.close();
				if(!bikedetails1.isEmpty()){
					purchase_date_set.setText(":"+" "+bikedetails1.get(0).toString());
					purchase_price_set.setText(":"+" "+bikedetails1.get(1).toString());
					purchase_notes_set.setText(":"+" "+bikedetails1.get(2).toString());
				}
	
	
	//System.out.println(""+bikedetails);
				etname_set.setText(":"+" "+bikedetails.get(0).toString());
				registration_set.setText(":"+" "+bikedetails.get(1).toString());
				manufacturer_set.setText(":"+" "+bikedetails.get(2).toString());
				vehicletype_set.setText(":"+" "+bikedetails.get(3).toString());
				BikeColor_set.setText(":"+" "+bikedetails.get(4).toString());
				BikeModel_set.setText(":"+" "+bikedetails.get(5).toString());
				
				YearOfManufacture_set.setText(":"+" "+bikedetails.get(6).toString());
				RegistrationDate_set.setText(":"+" "+bikedetails.get(7).toString());
				
				ChassisNumber_set.setText(":"+" "+bikedetails.get(8).toString());
				EngineNumber_set.setText(":"+" "+bikedetails.get(9).toString());
				fueltype_set.setText(":"+" "+bikedetails.get(10).toString());
				CC_set.setText(":"+" "+bikedetails.get(11).toString());
				FCupto_set.setText(":"+" "+bikedetails.get(12).toString());
	//Toast.makeText(DisplayBikeDetails.this, bikedetails.toString(), Toast.LENGTH_LONG).show();
	
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.ok_button:
			 startActivity(new Intent(DisplayBikeDetails.this, Bike.class));
			 finish();
			break;

		
		}
		
	}
}
