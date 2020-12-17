package com.stratvave.biketracker.extras;

import java.util.ArrayList;

import com.stratvave.biketracker.databases.DataHelper;
import com.stratvave.biketracker.main.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

public class ViewLicenceDetails extends Activity implements OnClickListener{
	TextView owner_name_et,Father_Name_et,date_of_birth_et,vehiclecat_spinner,Blood_group_et,Addres_et,Phonenumber_et,Licence_number_et,
	licence_issue_date_et,EngineNumber_et,calss_of_vehcle_spinner,valid_uptodate_et,RegistrationUpto_et,licence_with_effect_date_et;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.view_licence);
		TextView tv= (TextView) findViewById(R.id.tv);
		tv.setText("Your Licence Details");
	
				owner_name_et=(TextView) findViewById(R.id.owner_name_et);
				Father_Name_et=(TextView) findViewById(R.id.Father_Name_et);
				date_of_birth_et=(TextView) findViewById(R.id.date_of_birth_et);
				vehiclecat_spinner=(TextView) findViewById(R.id.vehiclecat_spinner);
				Blood_group_et=(TextView) findViewById(R.id.Blood_group_et);
				Addres_et=(TextView) findViewById(R.id.Addres_et);
				Phonenumber_et=(TextView) findViewById(R.id.Phonenumber_et);
				Licence_number_et=(TextView) findViewById(R.id.Licence_number_et);
				licence_issue_date_et=(TextView) findViewById(R.id.licence_issue_date_et);
				EngineNumber_et=(TextView) findViewById(R.id.EngineNumber_et);
				calss_of_vehcle_spinner=(TextView) findViewById(R.id.calss_of_vehcle_spinner);
				valid_uptodate_et=(TextView) findViewById(R.id.valid_uptodate_et);
				RegistrationUpto_et=(TextView) findViewById(R.id.RegistrationUpto_et);
				licence_with_effect_date_et=(TextView) findViewById(R.id.licence_with_effect_date_et);
				
				findViewById(R.id.ok_button).setOnClickListener(this);
				findViewById(R.id.cancel_button).setOnClickListener(this);
				
				DataHelper datahelper=new DataHelper(this);
				datahelper.open();
				ArrayList<String> licencelist =datahelper.getlicencedetails();
				datahelper.close();
				if(!licencelist.isEmpty()){
				owner_name_et.setText(":"+" "+licencelist.get(0).toString());
				Father_Name_et.setText(":"+" "+licencelist.get(1).toString());
				date_of_birth_et.setText(":"+" "+licencelist.get(2).toString());
				vehiclecat_spinner.setText(":"+" "+licencelist.get(3).toString());
				Blood_group_et.setText(":"+" "+licencelist.get(4).toString());
				Addres_et.setText(":"+" "+licencelist.get(5).toString());
				Phonenumber_et.setText(":"+" "+licencelist.get(6).toString());
				
				Licence_number_et.setText(":"+" "+licencelist.get(7).toString());
				licence_issue_date_et.setText(":"+" "+licencelist.get(8).toString());
				
				EngineNumber_et.setText(":"+" "+licencelist.get(9).toString());
				calss_of_vehcle_spinner.setText(":"+" "+licencelist.get(10).toString());
				valid_uptodate_et.setText(":"+" "+licencelist.get(11).toString());
				RegistrationUpto_et.setText(":"+" "+licencelist.get(12).toString());
				licence_with_effect_date_et.setText(":"+" "+licencelist.get(13).toString());
		
				}
				else{
					Toast.makeText(ViewLicenceDetails.this, "licence detail not added", Toast.LENGTH_SHORT).show();
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
