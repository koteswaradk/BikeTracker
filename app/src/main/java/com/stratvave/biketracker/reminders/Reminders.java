package com.stratvave.biketracker.reminders;

import com.stratvave.biketracker.main.R;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.AdapterView.AdapterContextMenuInfo;

public class Reminders extends ListActivity{
	
	  private static final int ACTIVITY_CREATE=0;
	  private static final int ACTIVITY_EDIT=1;
	    
	    private RemindersDbAdapter mDbHelper;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		ImageButton addreminder;
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.reminders);
		
		  mDbHelper = new RemindersDbAdapter(this);
	      mDbHelper.open();
	      fillData();
	      registerForContextMenu(getListView());
		
		addreminder=(ImageButton) findViewById(R.id.addreminder_button);
		
		addreminder.setOnClickListener(new OnClickListener() {
			
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				  //createReminder();
				
				Intent addreminder= new Intent(Reminders.this,AddReminder.class);
				startActivity(addreminder);
							}
		});
	}
	 @Override
	    public boolean onCreateOptionsMenu(Menu menu) {
	        super.onCreateOptionsMenu(menu);
	       /* MenuInflater mi = getMenuInflater();
	        mi.inflate(R.menu.list_menu, menu); */
	        return true;
	    }
	/*  @Override
	    public boolean onMenuItemSelected(int featureId, MenuItem item) {
	        switch(item.getItemId()) {
	        case R.id.menu_insert: 
	          
	            return true; 
	        case R.id.menu_settings: 
	        	Intent i = new Intent(this, TaskPreferences.class); 
	        	startActivity(i); 
	            return true;
	        }
	       
	        return super.onMenuItemSelected(featureId, item);
	    }*/
	  @Override
		public void onCreateContextMenu(ContextMenu menu, View v,
				ContextMenuInfo menuInfo) {
			super.onCreateContextMenu(menu, v, menuInfo);
			MenuInflater mi = getMenuInflater(); 
			mi.inflate(R.menu.list_menu_item_longpress, menu); 
		}
	  @Override
		public boolean onContextItemSelected(MenuItem item) {
			switch(item.getItemId()) {
	    	case R.id.menu_delete:
	    		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
		        mDbHelper.deleteReminder(info.id);
		        fillData();
		        return true;
			}
			return super.onContextItemSelected(item);
		}
		
	    private void createReminder() {
	        Intent i = new Intent(this, UpdateReminderActivity.class);
	        startActivityForResult(i, ACTIVITY_CREATE);
	    }
	  /*  @Override
	    protected void onListItemClick(ListView l, View v, int position, long id) {
	        super.onListItemClick(l, v, position, id);
	       
	        Intent i = new Intent(this, UpdateReminderActivity.class);
	        i.putExtra(RemindersDbAdapter.KEY_ROWID, id);
	        startActivityForResult(i, ACTIVITY_EDIT); 
	    }*/

	    @Override
	    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
	        super.onActivityResult(requestCode, resultCode, intent);
	        fillData();
	    }
	private void fillData() {
		// TODO Auto-generated method stub
		 Cursor remindersCursor = mDbHelper.fetchAllReminders();
	        startManagingCursor(remindersCursor);
	        
	        // Create an array to specify the fields we want to display in the list (only TITLE)
	        String[] from = new String[]{RemindersDbAdapter.VEHCLE_NAME,RemindersDbAdapter.REMIND_ABOUT,RemindersDbAdapter.KEY_DATE_TIME};
	        
	        // and an array of the fields we want to bind those fields to (in this case just text1)
	        int[] to = new int[]{R.id.text1,R.id.text2,R.id.text3,};
	        
	        // Now create a simple cursor adapter and set it to display
	        SimpleCursorAdapter reminders = 
	        	    new SimpleCursorAdapter(this, R.layout.reminder_row, remindersCursor, from, to);
	        setListAdapter(reminders);
		
	}
	/*@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		mDbHelper.close();
	}*/

}
