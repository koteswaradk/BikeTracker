package com.stratvave.biketracker.expenses;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

import com.googlecode.android.widgets.DateSlider.DateSlider;
import com.googlecode.android.widgets.DateSlider.DateTimeSlider;
import com.googlecode.android.widgets.DateSlider.labeler.TimeLabeler;
import com.stratvave.biketracker.databases.DataHelper;
import com.stratvave.biketracker.main.MainBikeTrackerActivity;
import com.stratvave.biketracker.main.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class Expenses extends Activity implements OnClickListener, OnItemSelectedListener{

	static final int DATETIMESELECTOR_ID = 0;
	ImageButton submit_expense, cancel_expense,clear_expenxes;
	
	private DataHelper dh;
	ArrayList<String> bikename;
	//int k;
	//String abikename[];
	//boolean[] itemsChecked ;
	EditText datetime_exp,odo_exp,totalcost_exp,location_exp,expenses_notes_et;
	Spinner expenses_spinner,vehiclename_spinner,paymenttype_spinner;
	
	String datetime_exps, odo_exps, totalcost_exps,location_exps,expenses_notes_ets, expenses_ss,vehiclename_ss, paymenttype_ss,sbikename;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.expenses);
		
		
			dh= new DataHelper(this);
			dh.open();
			bikename=dh.getBikeName();
			dh.close();
			/*if(sbikename==null){
				abikename=	(String[]) bikename.toArray(new String[bikename.size()]);
				 itemsChecked= new boolean [abikename.length];
				showDialog(0);
				AlertDialog ad=new AlertDialog.Builder(this).create();
				ad.setTitle("you need to selec the bike name First");
				ad.setCancelable(false);
				ad.show();
			}*/
		 
			
		
	/*	submit_expense=(ImageButton) findViewById(R.id.submit_expense_button);
		cancel_expense=(ImageButton) findViewById(R.id.cancel_expense_button);
		
		*/
		findViewById(R.id.submit_expense_button).setOnClickListener(this);
		findViewById(R.id.cancel_expense_button).setOnClickListener(this);
		
		
		datetime_exp=(EditText) findViewById(R.id.datetime_exp_et);
		odo_exp=(EditText) findViewById(R.id.odo_read_et);
		totalcost_exp=(EditText) findViewById(R.id.total_cost_et);
		location_exp=(EditText) findViewById(R.id.location_et);
		expenses_notes_et=(EditText) findViewById(R.id.expenses_notes_et);		
		
		expenses_spinner=(Spinner) findViewById(R.id.expenses_spinner);
		vehiclename_spinner=(Spinner) findViewById(R.id.vehiclename_spinner);
		paymenttype_spinner=(Spinner) findViewById(R.id.paymenttype_spinner);
		
		
		expenses_spinner.setOnItemSelectedListener(Expenses.this);
		
		datetime_exp.setFocusable(false);
		vehiclename_spinner.setOnItemSelectedListener(Expenses.this);
		
	    ArrayAdapter<String> aa = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, bikename);
	    aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    vehiclename_spinner.setAdapter(aa);
		datetime_exp.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				 showDialog(DATETIMESELECTOR_ID);
			}
		});
		
		vehiclename_spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			public void onItemSelected(AdapterView<?> parent, View arg1,
					int position, long arg3) {
				// TODO Auto-generated method stub
				vehiclename_ss=parent.getItemAtPosition(position).toString();
			}

			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		
		paymenttype_spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			public void onItemSelected(AdapterView<?> parent, View arg1,
					int position, long arg3) {
				// TODO Auto-generated method stub
				paymenttype_ss=parent.getItemAtPosition(position).toString();
			}

			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		
		
	}
	
	/*@Override
	protected Dialog onCreateDialog(int id, Bundle args) {
		// TODO Auto-generated method stub
		switch (id) {
		case 0:
			
			return new AlertDialog.Builder(this)
			
			.setTitle("YOU NEED TO SELECT BIKE FIRST")
			.setCancelable(false)
			.setPositiveButton("OK", new
			DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog,
			int whichButton)
			{
			Toast.makeText(getBaseContext(),
			"OK clicked!", Toast.LENGTH_SHORT).show();
			}
			})
			.setNegativeButton("Cancel", new
			DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog,
			int whichButton)
			{
			Toast.makeText(getBaseContext(),
			"Cancel clicked!", Toast.LENGTH_SHORT).show();
			}
			}).setSingleChoiceItems(abikename, k, new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					Toast.makeText(getBaseContext(),abikename[which] + (":"+"checked!"),Toast.LENGTH_SHORT).show();
					sbikename=abikename[which];
				}
			}).create();
			
			.setMultiChoiceItems(abikename, itemsChecked, new
					DialogInterface.OnMultiChoiceClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which,
				boolean isChecked) {
				
				}
				}
				)
				
				}
				
			//break;

			return null;
		
		
	}*/
		
	
	private DateSlider.OnDateSetListener mDateTimeSetListener =
	        new DateSlider.OnDateSetListener() {
	            public void onDateSet(DateSlider view, Calendar selectedDate) {
	                // update the dateText view with the corresponding date
	                int minute = selectedDate.get(Calendar.MINUTE) /
	                        TimeLabeler.MINUTEINTERVAL*TimeLabeler.MINUTEINTERVAL;
	                datetime_exp.setText(String.format("%te. %tB %tY %tH:%02d",selectedDate, selectedDate, selectedDate, selectedDate, minute));
	               // datetime_exps=(String.format("%te. %tB %tY%n%tH:%02d",selectedDate, selectedDate, selectedDate, selectedDate, minute));
	            }
	    };
	 @Override
	    protected Dialog onCreateDialog(int id) {
	        // this method is called after invoking 'showDialog' for the first time
	        // here we initiate the corresponding DateSlideSelector and return the dialog to its caller
	    	
	        final Calendar c = Calendar.getInstance();
	        switch (id) {
	        
	        case DATETIMESELECTOR_ID:
	            return new DateTimeSlider(this,mDateTimeSetListener,c);
	        }
	        return null;
	    }
	
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.submit_expense_button:
			if(datetime_exp.length()<=0)
			{
				datetime_exp.setError("Enter Date and Time");
			}else
			if(odo_exp.length()<=0)
			{
				odo_exp.setError("Enter odometer reading");
			}else
			
			if (totalcost_exp.length()<=0) {
				totalcost_exp.setError("Enter Total Cost");
			}else
			
			if (location_exp.length()<=0) {
				location_exp.setError("Enter the location");
			}else
			
			if (expenses_notes_et.length()<=0) {
				expenses_notes_et.setError("Enter note of your Expenses");
			}else{
				
				datetime_exps=datetime_exp.getText().toString();
				odo_exps=odo_exp.getText().toString();
				totalcost_exps=totalcost_exp.getText().toString();
				location_exps=location_exp.getText().toString();
				expenses_notes_ets=expenses_notes_et.getText().toString();
				
					AlertDialog.Builder alertDialog= new AlertDialog.Builder(Expenses.this);
					
					alertDialog.setTitle("Save");
					alertDialog.setMessage("Save to database");
					
					alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
						
						
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
						
							//Toast.makeText(getBaseContext(), "Saved to database", Toast.LENGTH_SHORT).show();
							
							dh.open();
							long check=dh.exp(datetime_exps, odo_exps, expenses_ss, totalcost_exps, vehiclename_ss,
									location_exps, paymenttype_ss, expenses_notes_ets);
							dh.close();
							if (check!=-1) {
								LayoutInflater inflater = getLayoutInflater();
						    	 View layout = inflater.inflate(R.layout.customtoast,
						    	                                (ViewGroup) findViewById(R.id.toast_layout_root));

						    	 TextView text = (TextView) layout.findViewById(R.id.text);
						    	 text.setText("Expences Details Are Inserted Sucessfull");
                                 text.setTextSize(20);
						    	 Toast toast = new Toast(getApplicationContext());
						    	 toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
						    	 toast.setDuration(Toast.LENGTH_LONG);
						    	 toast.setView(layout);
						    	 toast.show();
								
							           finish();
							}
							else{
								LayoutInflater inflater = getLayoutInflater();
						    	 View layout = inflater.inflate(R.layout.customtoast,
						    	                                (ViewGroup) findViewById(R.id.toast_layout_root));

						    	 TextView text = (TextView) layout.findViewById(R.id.text);
						    	 text.setText("Sorry Expences Details Are Not Inserted");
                                text.setTextSize(20);
						    	 Toast toast = new Toast(getApplicationContext());
						    	 toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
						    	 toast.setDuration(Toast.LENGTH_LONG);
						    	 toast.setView(layout);
						    	 toast.show();
								
								
							}
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
		case R.id.cancel_expense_button:

			Intent i= new Intent(Expenses.this,MainBikeTrackerActivity.class);
			startActivity(i);
			finish();
		/*	AlertDialog.Builder alertDialog2= new AlertDialog.Builder(Expenses.this);
			alertDialog2.setTitle("Cancel");
			alertDialog2.setMessage("Return to home page");
			
			alertDialog2.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
				
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
				
				
				}
			});

			alertDialog2.setNegativeButton("Cnacel", new DialogInterface.OnClickListener() {
				
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					finish();
				}
			});
					alertDialog2.show();*/
			break;
			
			
		/*case R.id.clear_expences_button:
			AlertDialog.Builder alertDialog1= new AlertDialog.Builder(Expenses.this);
			alertDialog1.setTitle("Alert");
			alertDialog1.setMessage("Do you want to clear all the data");
			alertDialog1.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
				
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					datetime_exp.setText("");
					odo_exp.setText("");
					totalcost_exp.setText("");
					location_exp.setText("");
					expenses_notes_et.setText("");
					
						}
			});
			
			
			alertDialog1.setNegativeButton("No", new DialogInterface.OnClickListener() {
				
					public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
				
					Toast.makeText(getApplicationContext(), "Cancelled", Toast.LENGTH_SHORT).show();		
				}
			});
			
			alertDialog1.show();
	
	break;
*/
		
		}
	}

	public void onItemSelected(AdapterView<?> parent, View arg1, int position,
			long arg3) {
		// TODO Auto-generated method stub
		expenses_ss=parent.getItemAtPosition(position).toString();
	}

	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}
}
