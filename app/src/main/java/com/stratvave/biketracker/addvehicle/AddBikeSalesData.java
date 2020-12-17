package com.stratvave.biketracker.addvehicle;



import java.util.ArrayList;
import java.util.Calendar;

import com.googlecode.android.widgets.DateSlider.DateSlider;
import com.googlecode.android.widgets.DateSlider.DefaultDateSlider;
import com.stratvave.biketracker.databases.DataHelper;
import com.stratvave.biketracker.main.MainBikeTrackerActivity;
import com.stratvave.biketracker.main.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


public class AddBikeSalesData extends Activity implements OnClickListener, OnItemSelectedListener{
	
	static final int DEFAULTDATESELECTOR_ID=0;
	EditText sale_date,sale_price,sale_note;
	String sale_dates,sale_prices,sale_notes, vehicle_name_s;
	Spinner vehicle_name_spinner;
	long value;
	private DataHelper dh;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.salesdetails);
		
		vehicle_name_spinner=(Spinner) findViewById(R.id.vehicle_spinner);
		
		 DataHelper db=new DataHelper(this);
		 db.open();
		ArrayList<String> arraylist= db.getBikeName();
		db.close();
		/*if (arraylist.isEmpty()) {
			Toast.makeText(AddBikeSalesData.this, "YOU NEED TO ADD VEHICLE DETAILS FIRST", Toast.LENGTH_SHORT).show();
			finish();
		} else{*/
		sale_date=(EditText) findViewById(R.id.sale_date_et);
		sale_price=(EditText) findViewById(R.id.sale_price_et);
		sale_note=(EditText) findViewById(R.id.sale_notes_et);
		
		/*sale_dates=sale_date.getText().toString();
		sale_prices=sale_price.getText().toString();
		sale_notes=sale_note.getText().toString();*/
		
		findViewById(R.id._ok).setOnClickListener(this);
		findViewById(R.id._cancel).setOnClickListener(this);
		 vehicle_name_spinner.setOnItemSelectedListener(this);
		 ArrayAdapter<String> aa = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, arraylist);
		    aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		    vehicle_name_spinner.setAdapter(aa);
		  
		
		sale_date.setFocusable(false);
		sale_date.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showDialog(DEFAULTDATESELECTOR_ID);
			}
		});
	
		
			//}
	}
	private DateSlider.OnDateSetListener mDateSetListener =
	        new DateSlider.OnDateSetListener() {
	            public void onDateSet(DateSlider view, Calendar selectedDate) {
	                // update the dateText view with the corresponding date
	            	//Toast.makeText(AddVehicle.this, String.format("The chosen date:%n%te. %tB %tY", selectedDate, selectedDate, selectedDate), Toast.LENGTH_SHORT).show();
	               // dateText.setText();
	            	sale_date.setText(String.format("%te. %tB %tY", selectedDate, selectedDate, selectedDate));
	            }
	    };
	    @Override
	    protected Dialog onCreateDialog(int id) {
	        // this method is called after invoking 'showDialog' for the first time
	        // here we initiate the corresponding DateSlideSelector and return the dialog to its caller
	    	
	    	// get today's date and time
	        final Calendar c = Calendar.getInstance();
	        
	        switch (id) {
	        
			case DEFAULTDATESELECTOR_ID:
	            return new DefaultDateSlider(this,mDateSetListener,c);
	        }
	        return null;
	    }
	
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id._ok:
			if(sale_date.length()<=0)
			{
				sale_date.setError("Enter the bike sale date");
			}else
			if(sale_price.length()<=0)
			{
				sale_price.setError("Enter the bike sale price");
			}else
			{
				AlertDialog.Builder alertDialog= new AlertDialog.Builder(AddBikeSalesData.this);
				alertDialog.setTitle("Save");
				alertDialog.setMessage("Save to sale details");
				
				alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
					
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
					
						sale_dates=sale_date.getText().toString();
						sale_prices=sale_price.getText().toString();
						sale_notes=sale_note.getText().toString();
				
						sales();
					}
				});
				
				alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
					
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
					
						Toast.makeText(getBaseContext(), "Cancelled", Toast.LENGTH_SHORT).show();
						finish();
					}
				});
				alertDialog.show();
			}
			break;
		case R.id._cancel:
			/*AlertDialog.Builder alertDialog2= new AlertDialog.Builder(AddBikeSalesData.this);
			alertDialog2.setTitle("Cancel");
			alertDialog2.setMessage("Return to home page");
			
			alertDialog2.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
				
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
				
					Intent i= new Intent(AddBikeSalesData.this,MainBikeTrackerActivity.class);
					startActivity(i);
				}
			});

			alertDialog2.setNegativeButton("No", new DialogInterface.OnClickListener() {
				
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					
				}
			});
					alertDialog2.show();
			break;*/
			finish();
		

		
		}
	
	}

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub
		  vehicle_name_s = arg0.getItemAtPosition(arg2).toString();
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}
	private void sales()
	{
		// TODO Auto-generated method stub
		dh=new DataHelper(this);
		dh.open();
	    value=	dh.purchase_details(vehicle_name_s,sale_dates, sale_prices, sale_notes);						
	    dh.close();
		if (value!=-1) {
			Toast.makeText(AddBikeSalesData.this, "Purchase Details Inserted Sucess Fully", Toast.LENGTH_LONG).show();
			finish();
			
		}
		else{
			Toast.makeText(AddBikeSalesData.this, "Sorry Purchase Details Not Inserted ", Toast.LENGTH_LONG).show();
			finish();
		}
		
	}

}
