package com.stratvave.biketracker.reminders;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.googlecode.android.widgets.DateSlider.DateSlider;
import com.googlecode.android.widgets.DateSlider.DateTimeSlider;
import com.googlecode.android.widgets.DateSlider.labeler.TimeLabeler;
import com.stratvave.biketracker.databases.DataHelper;
import com.stratvave.biketracker.main.MainBikeTrackerActivity;
import com.stratvave.biketracker.main.R;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class AddReminder extends Activity implements OnClickListener, OnItemSelectedListener{
	
	// 
		// Dialog Constants
		//
		private static final int DATE_PICKER_DIALOG = 0;
		private static final int TIME_PICKER_DIALOG = 1;
		
		// 
		// Date Format 
		//
		private static final String DATE_FORMAT = "yyyy-MM-dd"; 
		private static final String TIME_FORMAT = "kk:mm";
		public static final String DATE_TIME_FORMAT = "yyyy-MM-dd kk:mm:ss";
		
		private EditText mTitleText;
	    private EditText mBodyText;
	   
	    private Long mRowId;
	    private RemindersDbAdapter mDbHelper;
	    private Calendar mCalendar;  
	    Cursor reminder;
		static final int DATESELECTOR_ID = 0;
		static final int TIMESELECTOR_ID = 1;
		ImageButton submit_rem, cancel_rem;
		EditText rem_time,rem_date, rem_odo, rem_note;
		Spinner Reminder_spinner,vehiclename_spinner;
		String rem_names, rem_dates, rem_odos, rem_notes,vehicle_name_s,reminderfortype_s;
		 ArrayList<String> bikelist;
		private DataHelper dh;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.addnewreminder);
		
		try {
			this.dh= new DataHelper(this);
			dh.open();
		     bikelist= dh.getBikeName();
		     dh.close();
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error "+e);
		}
		  mDbHelper = new RemindersDbAdapter(this);
		  mCalendar = Calendar.getInstance(); 
		  
		  mRowId = savedInstanceState != null ? savedInstanceState.getLong(RemindersDbAdapter.KEY_ROWID) : null;

        //registerButtonListenersAndSetDefaultText();
		Reminder_spinner=(Spinner) findViewById(R.id.Reminder_spinner);
		vehiclename_spinner=(Spinner) findViewById(R.id.vehiclename_spinner);
		submit_rem= (ImageButton) findViewById(R.id.submit_rem_button);
		cancel_rem= (ImageButton) findViewById(R.id.cancel_rem_button);
		
		rem_date=(EditText) findViewById(R.id.remind_datetime_et);
		rem_time=(EditText) findViewById(R.id.remind_odo_et);
		/*rem_date=(EditText) findViewById(R.id.remind_datetime_et);*/
	
		rem_note=(EditText) findViewById(R.id.reminder_notes_et);
		
		vehiclename_spinner.setOnItemSelectedListener(this);
		Reminder_spinner.setOnItemSelectedListener(this);
		
		rem_date.setOnClickListener(this);
		rem_date.setFocusable(false);
	
		rem_time.setOnClickListener(this);
		rem_time.setFocusable(false);
		
		 submit_rem.setOnClickListener(this);
		 cancel_rem.setOnClickListener(this);
		
		
	    ArrayAdapter<String> aa = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, bikelist);
	    aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    vehiclename_spinner.setAdapter(aa);
	   
	    vehiclename_spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View arg1,
					int position, long arg3) {
				// TODO Auto-generated method stub
				vehicle_name_s=parent.getItemAtPosition(position).toString();
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	    Reminder_spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View arg1,
					int position, long arg3) {
				// TODO Auto-generated method stub
				
				reminderfortype_s=parent.getItemAtPosition(position).toString();
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	   /* private void registerButtonListenersAndSetDefaultText() {

	    	rem_date.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					showDialog(DATE_PICKER_DIALOG);  
				}
			}); 
			
			
	    	rem_time.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					showDialog(TIME_PICKER_DIALOG); 
				}
			}); 
	    }*/
	    private void setRowIdFromIntent() {
			if (mRowId == null) {
				Bundle extras = getIntent().getExtras();            
				mRowId = extras != null ? extras.getLong(RemindersDbAdapter.KEY_ROWID) 
										: null;
				
			}
		}
	    @Override
	    protected void onPause() {
	        super.onPause();
	        mDbHelper.close(); 
	    }
	    
	    @Override
	    protected void onResume() {
	        super.onResume();
	        mDbHelper.open(); 
	    	setRowIdFromIntent();
			//populateFields();
	    }
	    private void populateFields()  {
	    	
	      	
	    	
	    	// Only populate the text boxes and change the calendar date
	    	// if the row is not null from the database. 
	        if (mRowId != null) {
	        	 reminder = mDbHelper.fetchReminder(mRowId);
	            startManagingCursor(reminder);
	            mTitleText.setText(reminder.getString(
	    	            reminder.getColumnIndexOrThrow(RemindersDbAdapter.VEHCLE_NAME)));
	            mBodyText.setText(reminder.getString(
	                    reminder.getColumnIndexOrThrow(RemindersDbAdapter.REMIND_ABOUT)));
	            

	            // Get the date from the database and format it for our use. 
	            SimpleDateFormat dateTimeFormat = new SimpleDateFormat(DATE_TIME_FORMAT);
	            Date date = null;
				try {
					String dateString = reminder.getString(reminder.getColumnIndexOrThrow(RemindersDbAdapter.KEY_DATE_TIME)); 
					date = dateTimeFormat.parse(dateString);
		            mCalendar.setTime(date); 
				} catch (ParseException e) {
					Log.e("ReminderEditActivity", e.getMessage(), e); 
				} 
	        } else {
	        	// This is a new task - add defaults from preferences if set. 
	        	SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this); 
	        	String defaultTitleKey = getString(R.string.pref_task_title_key); 
	        	String defaultTimeKey = getString(R.string.pref_default_time_from_now_key); 
	        	
	        	String defaultTitle = prefs.getString(defaultTitleKey, null);
	        	String defaultTime = prefs.getString(defaultTimeKey, null); 
	        	
	        	if(defaultTitle != null)
	        		mTitleText.setText(defaultTitle); 
	        	
	        	if(defaultTime != null)
	        		mCalendar.add(Calendar.MINUTE, Integer.parseInt(defaultTime));
	        	
	        }
	        stopManagingCursor(reminder);
	       /* updateDateButtonText(); 
	        updateTimeButtonText(); */
	        
	        	
	    }
	    private void updateTimeButtonText() {
			// Set the time button text based upon the value from the database
	        SimpleDateFormat timeFormat = new SimpleDateFormat(TIME_FORMAT); 
	        String timeForButton = timeFormat.format(mCalendar.getTime());
	        
	     rem_time.setText(timeForButton);
		}

		private void updateDateButtonText() {
			// Set the date button text based upon the value from the database 
	        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT); 
	        String dateForButton = dateFormat.format(mCalendar.getTime()); 
	        rem_date.setText(dateForButton);
		}
		@Override
	    protected Dialog onCreateDialog(int id) {
	    	switch(id) {
	    		case DATE_PICKER_DIALOG: 
	    			return showDatePicker();
	    		case TIME_PICKER_DIALOG: 
	    			return showTimePicker(); 
	    	}
	    	return super.onCreateDialog(id);
	    }
	    
	 	private DatePickerDialog showDatePicker() {
			
			
			DatePickerDialog datePicker = new DatePickerDialog(AddReminder.this, new DatePickerDialog.OnDateSetListener() {
				
				@Override
				public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
					mCalendar.set(Calendar.YEAR, year);
					mCalendar.set(Calendar.MONTH, monthOfYear);
					mCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
					updateDateButtonText(); 
				}
			}, mCalendar.get(Calendar.YEAR), mCalendar.get(Calendar.MONTH), mCalendar.get(Calendar.DAY_OF_MONTH)); 
			return datePicker; 
		}

	   private TimePickerDialog showTimePicker() {
			
	    	TimePickerDialog timePicker = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
				
				@Override
				public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
					mCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
					mCalendar.set(Calendar.MINUTE, minute); 
					updateTimeButtonText(); 
				}
			}, mCalendar.get(Calendar.HOUR_OF_DAY), mCalendar.get(Calendar.MINUTE), true); 
			
	    	return timePicker; 
		}
	 	
		
		/*submit_rem.setOnClickListener(new OnClickListener() {
			
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				
				if(rem_name.length()<=0)
				{				
					rem_name.setError("Remainder About What");				
				}else
				if(rem_date.length()<=0)
				{	
					//rem_date.setError(null);
					rem_date.setError("Remainder Data And Time");
				}else
				
				if (rem_odo.length()<=0) {
				    //rem_odo.setError(null);
					rem_odo.setError("Odometer Reading For Remainder");
				}
				if (rem_note.length()<=0) {
				    //rem_odo.setError(null);
					rem_note.setError("Your Note For Reading");
				}else{
					rem_names=rem_name.getText().toString();
					rem_dates=rem_date.getText().toString();
					rem_odos=rem_odo.getText().toString();
					rem_notes=rem_note.getText().toString();
				AlertDialog.Builder alertDialog= new AlertDialog.Builder(AddReminder.this);
				
				alertDialog.setTitle("Save");
				alertDialog.setMessage("Save reminder");
				
				alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
					
					
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
					
						//Toast.makeText(getBaseContext(), "Reminder saved", Toast.LENGTH_SHORT).show();
						
						dh.open();
						dh.reminder(rem_names, rem_dates, rem_odos, rem_notes);
						dh.close();
						Calendar cal = Calendar.getInstance();       //for using this you need to import java.util.Calendar;
						 
						// add minutes to the calendar object
						Intent intent = new Intent(AddReminder.this,
		                        AlarmReceiver.class);

		              //  intent.putExtra("item_name", prescription.getItemName());
		              //  intent.putExtra("message",Reminders.this,"iiii");
		               // intent.putExtra("item_id", itemId);
		                intent.putExtra("activityToTrigg",
		                        "com.companyName.appName.main.Activity_Reminder");

		                PendingIntent mAlarmSender;

		                mAlarmSender = PendingIntent.getBroadcast(
		                		AddReminder.this, 0, intent, 0);

		               
		               // long alarmTime = dateMgmt.getTimeForAlarm();
		                long alarmTime =  Long.parseLong(rem_dates);
		                Calendar c = Calendar.getInstance();
		                c.setTimeInMillis(alarmTime);
		                // Schedule the alarm!
		                AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
		                am.set(AlarmManager.RTC_WAKEUP, alarmTime + 15000,
		                        mAlarmSender);
						
					}
				
				});
				
				alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
					
					
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
					
						Toast.makeText(getBaseContext(), "Cancelled", Toast.LENGTH_SHORT).show();
					}
				});
				alertDialog.show();
				}
				
			}
			
		});
		*/
		/*cancel_rem.setOnClickListener(new OnClickListener() {
			
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
			
				AlertDialog.Builder alertDialog2= new AlertDialog.Builder(AddReminder.this);
				alertDialog2.setTitle("Cancel");
				alertDialog2.setMessage("Return to home page");
				
				alertDialog2.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
					
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
					
						Intent i= new Intent(AddReminder.this,MainBikeTrackerActivity.class);
						startActivity(i);
					}
				});

				alertDialog2.setNegativeButton("No", new DialogInterface.OnClickListener() {
					
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						
					}
				});
						alertDialog2.show();
				
			}
		});*/
		
		
		
	
	/* @Override
	    protected Dialog onCreateDialog(int id) {
	        // this method is called after invoking 'showDialog' for the first time
	        // here we initiate the corresponding DateSlideSelector and return the dialog to its caller
	    	
	        final Calendar c = Calendar.getInstance();
	        
	        switch (id) {
	        case DATE_PICKER_DIALOG: 
	    			return showDatePicker();
	    		case TIME_PICKER_DIALOG: 
	    			return showTimePicker(); 
	        
	    			case DATETIMESELECTOR_ID:
	            return new DateTimeSlider(this,mDateTimeSetListener,c);
	        }
	        }
	    			return super.onCreateDialog(id);
	    }*/
	/* private DateSlider.OnDateSetListener mDateTimeSetListener =
		        new DateSlider.OnDateSetListener() {
		            public void onDateSet(DateSlider view, Calendar selectedDate) {
		                // update the dateText view with the corresponding date and time
		                int minute = selectedDate.get(Calendar.MINUTE) /
		                        TimeLabeler.MINUTEINTERVAL*TimeLabeler.MINUTEINTERVAL;
		                rem_date.setText(String.format("%te. %tB %tY%n%tH:%02d",
		                        selectedDate, selectedDate, selectedDate, selectedDate, minute));
		                
		                //display only date 
		              //  Toast.makeText(AddReminder.this, String.format("%te. %tB %tY%n",selectedDate, selectedDate, selectedDate), Toast.LENGTH_LONG).show();
		                //display only time
		                
		                //rem_date.setText(String.format("%tH:%02d",selectedDate, minute));
		            }
		            
		            
		    };*/

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		/*case R.id.remind_datetime_et:
			//showDialog(DATETIMESELECTOR_ID);
			//showDialog(DATETIMESELECTOR_ID);
			break;*/
		case R.id.submit_rem_button:
			if(rem_time.length()<=0)
			{				
				rem_time.setError("Remainder About What");				
			}else
			if(rem_date.length()<=0)
			{	
				//rem_date.setError(null);
				rem_date.setError("Remainder Data And Time");
			}else
			
			/*if (rem_odo.length()<=0) {
			    //rem_odo.setError(null);
				rem_odo.setError("Odometer Reading For Remainder");
			}*/
			if (rem_note.length()<=0) {
			    //rem_odo.setError(null);
				rem_note.setError("Your Note For Reading");
			}else{
				rem_names=rem_time.getText().toString();
				rem_dates=rem_date.getText().toString();
				/*rem_odos=rem_odo.getText().toString();*/
				rem_notes=rem_note.getText().toString();
			AlertDialog.Builder alertDialog= new AlertDialog.Builder(AddReminder.this);
			
			alertDialog.setTitle("Save");
			alertDialog.setMessage("Save reminder");
			
			alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
				
				
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
				
					//Toast.makeText(getBaseContext(), "Reminder saved", Toast.LENGTH_SHORT).show();
					
				/*	dh.open();
					dh.reminder(rem_names, rem_dates, rem_odos, rem_notes);
					dh.close();
					Calendar cal = Calendar.getInstance();       //for using this you need to import java.util.Calendar;
					 
					// add minutes to the calendar object
					Intent intent = new Intent(AddReminder.this,
	                        AlarmReceiver.class);*/

	              //  intent.putExtra("item_name", prescription.getItemName());
	              //  intent.putExtra("message",Reminders.this,"iiii");
	               // intent.putExtra("item_id", itemId);
	             /*   intent.putExtra("activityToTrigg",
	                        "com.companyName.appName.main.Activity_Reminder");

	                PendingIntent mAlarmSender;

	                mAlarmSender = PendingIntent.getBroadcast(
	                		AddReminder.this, 0, intent, 0);

	               
	               // long alarmTime = dateMgmt.getTimeForAlarm();
	                long alarmTime =  Long.parseLong(rem_dates);
	                Calendar c = Calendar.getInstance();
	                c.setTimeInMillis(alarmTime);
	                // Schedule the alarm!
	                AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
	                am.set(AlarmManager.RTC_WAKEUP, alarmTime + 15000,
	                        mAlarmSender);*/
					saveState(); 
	        		setResult(RESULT_OK);
	        	    Toast.makeText(AddReminder.this, getString(R.string.task_saved_message), Toast.LENGTH_SHORT).show();
	        	    finish(); 
					
				}
			
			});
			
			alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
				
				
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
				
					Toast.makeText(getBaseContext(), "Cancelled", Toast.LENGTH_SHORT).show();
				}
			});
			alertDialog.show();
			
			
		
	
			}


			break;
		case R.id.cancel_rem_button:
			finish();
			break;
		case R.id.remind_datetime_et:
			showDialog(DATE_PICKER_DIALOG);
			break;
		case R.id.remind_odo_et:
			showDialog(TIME_PICKER_DIALOG); 
			break;
		
		}
		
	}
	@Override
	public void onItemSelected(AdapterView<?> parent, View arg1,
			int position, long arg3) {
		// TODO Auto-generated method stub
		vehicle_name_s=parent.getItemAtPosition(position).toString();
	}
	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}
	 @Override
	    protected void onSaveInstanceState(Bundle outState) {
	        super.onSaveInstanceState(outState);
	        outState.putLong(RemindersDbAdapter.KEY_ROWID, mRowId);
	    }
	    

	    
	    private void saveState() {
	      /*  String title = mTitleText.getText().toString();
	        String body = mBodyText.getText().toString();*/

	        SimpleDateFormat dateTimeFormat = new SimpleDateFormat(DATE_TIME_FORMAT); 
	    	String reminderDateTime = dateTimeFormat.format(mCalendar.getTime());

	        if (mRowId == null) {
	        	
	        	long id = mDbHelper.createReminder(vehicle_name_s, reminderfortype_s, reminderDateTime,rem_notes);
	            if (id > 0) {
	                mRowId = id;
	            }
	        } else {
	            mDbHelper.updateReminder(mRowId, vehicle_name_s, reminderfortype_s, reminderDateTime,rem_notes);
	        }
	       
	        new ReminderManager(this).setReminder(mRowId, mCalendar); 
	    }
	    
	
	
}
