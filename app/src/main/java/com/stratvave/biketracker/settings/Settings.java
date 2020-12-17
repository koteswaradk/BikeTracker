package com.stratvave.biketracker.settings;

import java.util.ArrayList;

import com.stratvave.autoanswer.AutoAnswerPreferenceActivity;

import com.stratvave.biketracker.main.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Spinner;
import android.widget.Toast;

public class Settings extends Activity{
	
	Spinner fuelunit_spinner;
	Spinner distanceunit_spinner;
	String fuelunit_spinner_s,distanceunit_spinner_s;
	final int row_id=1;
	CheckBox utoanswer,smscheck;
	int requestcode=1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.settings);
		
		
		utoanswer=(CheckBox) findViewById(R.id.autoanswercheck);
		smscheck=(CheckBox) findViewById(R.id.smscheck);
		utoanswer.setOnCheckedChangeListener(listener);
		smscheck.setOnCheckedChangeListener(listener);
		
	}
	

	OnCheckedChangeListener listener = new OnCheckedChangeListener() {
		 
		public void onCheckedChanged(CompoundButton arg0, boolean isChecked) {
		if(isChecked){
		switch(arg0.getId())
		  {
		    case R.id.autoanswercheck:
		    	utoanswer.setChecked(true);
		    	smscheck.setChecked(false);
		    	utoanswer.setEnabled(true);
		    	Intent i=new Intent(Settings.this, AutoAnswerPreferenceActivity.class);
		    	Bundle b=new Bundle();
		    	b.putBoolean("enabled", true);
		    	i.putExtras(b);
		    	startActivityForResult(i, requestcode);
		    	
		         break;
		    case R.id.smscheck:
		    	utoanswer.setChecked(false);
		    	smscheck.setChecked(true);

		         
		         break;
		 
		  }
		}
		 
		}
		};
		protected void onActivityResult(int requestCode, int resultCode, Intent data) {
			 if (requestCode == requestcode) {
		    	    if (resultCode == RESULT_OK) {
		    	    	String text=data.getData().toString();
		    	    	if (text.equals("true")) {
		    	    		utoanswer.setEnabled(true);
						}else
						{
							utoanswer.setEnabled(false);
						}
		    	      
		            }            
		        }
		};
}
		/*
		DataHelper helper=new DataHelper(this);
		helper.open();
		ArrayList<String> arraylist=helper.getsettings();
		if (arraylist.isEmpty()) {
			long k=helper.settings("Lit", "Gal");
			
		}
		helper.close();*/
		
		/*findViewById(R.id.ok_settings_unit_button).setOnClickListener(this);
		
		
		fuelunit_spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parentfueltype, View arg1,
					int positionfueltype, long arg3) {
				// TODO Auto-generated method stub
				fuelunit_spinner_s=parentfueltype.getItemAtPosition(positionfueltype).toString();
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		distanceunit_spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parentfueltype, View arg1,
					int positionfueltype, long arg3) {
				// TODO Auto-generated method stub
				distanceunit_spinner_s=parentfueltype.getItemAtPosition(positionfueltype).toString();
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});*/
		/*utoanswer.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (utoanswer.isChecked()) {
					
				}
			}
		});*/
		
		
	
	/*@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.ok_settings_unit_button:
			AlertDialog ad=new AlertDialog.Builder(Settings.this).create();
			ad.setTitle("add settings");
			ad.setButton("Insert", new DialogInterface.OnClickListener() {
			
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					System.out.println(""+fuelunit_spinner_s+""+distanceunit_spinner_s);
					DataHelper dataHelper=new DataHelper(Settings.this);
					dataHelper.open();
					boolean k=dataHelper.updatesettings(fuelunit_spinner_s, distanceunit_spinner_s,row_id);
					dataHelper.close();
					if (k) {
						Toast.makeText(Settings.this, "settings inserted success full", Toast.LENGTH_LONG).show();
					}else{
						Toast.makeText(Settings.this, "settings not inserted ", Toast.LENGTH_LONG).show();
					}
				}
			});
       ad.setButton2("Cancel",new DialogInterface.OnClickListener() {
				
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					finish();
				}
			});
       ad.show();*/
      
		
	
	
		
	


