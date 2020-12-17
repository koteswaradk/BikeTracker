
package com.stratvave.biketracker.reminders;



import com.stratvave.biketracker.main.R;

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
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.Toast;

public class ReminderListActivity extends ListActivity {
    private static final int ACTIVITY_CREATE=0;
    private static final int ACTIVITY_EDIT=1;
    
    private RemindersDbAdapter mDbHelper;
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reminder_list);
        mDbHelper = new RemindersDbAdapter(this);
        mDbHelper.open();
        fillData();
        registerForContextMenu(getListView());
      
       

    }
    

	private void fillData() {
        Cursor remindersCursor = mDbHelper.fetchAllReminders();
        startManagingCursor(remindersCursor);
        
        // Create an array to specify the fields we want to display in the list (only TITLE)
        String[] from = new String[]{RemindersDbAdapter.REMIND_ABOUT,RemindersDbAdapter.VEHCLE_NAME,RemindersDbAdapter.KEY_DATE_TIME};
      
        // and an array of the fields we want to bind those fields to (in this case just text1)
        int[] to = new int[]{R.id.text2,R.id.text1,R.id.text3};
        
        // Now create a simple cursor adapter and set it to display
        SimpleCursorAdapter reminders = new SimpleCursorAdapter(this, R.layout.reminder_row, remindersCursor, from, to);
        setListAdapter(reminders);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater mi = getMenuInflater();
        mi.inflate(R.menu.list_menu, menu); 
        return true;
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        switch(item.getItemId()) {
        case R.id.expencemenu_view: 
            createReminder();
            return true; 
        case R.id.expencemenu_delete: 
        	Intent i = new Intent(this, TaskPreferences.class); 
        	startActivity(i); 
            return true;
        }
       
        return super.onMenuItemSelected(featureId, item);
    }
	
    @Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		menu.add(0, v.getId(),0, "Delete");
		menu.add(1, v.getId(),0, "View");
//		MenuInflater mi = getMenuInflater(); 
//		mi.inflate(R.menu.list_menu_item_longpress, menu); 
	}

    @Override
	public boolean onContextItemSelected(MenuItem item) {
		switch(item.getItemId()) {
   	case R.id.menu_delete:
   		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
	        mDbHelper.deleteReminder(info.id);
	        fillData();
	        return true;
	       
    /*	case R.id.:
    		AdapterContextMenuInfo info1 = (AdapterContextMenuInfo) item.getMenuInfo();
	        mDbHelper.deleteReminder(info1.id);
	       // fillData();
    		Toast.makeText(this, " hbhbgjhgjghjj", Toast.LENGTH_SHORT).show();
    		  Intent i = new Intent(this, UpdateReminderActivity.class);
   	        i.putExtra(RemindersDbAdapter.KEY_ROWID, info1.id);
   	        startActivityForResult(i, ACTIVITY_EDIT); 
   	        return true;*/
    	       
	}
    	if(item.getTitle()=="Delete"){
    		Toast.makeText(this, " Deleted", Toast.LENGTH_SHORT).show();
    	}
    	else if(item.getTitle()=="View"){
    		Toast.makeText(this, " view", Toast.LENGTH_SHORT).show();
    	}
		return super.onContextItemSelected(item);
	}
	
    private void createReminder() {
        Intent i = new Intent(this, UpdateReminderActivity.class);
        startActivityForResult(i, ACTIVITY_CREATE);
    }
    
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Intent i = new Intent(this, UpdateReminderActivity.class);
        i.putExtra(RemindersDbAdapter.KEY_ROWID, id);
        startActivityForResult(i, ACTIVITY_EDIT); 
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        fillData();
    }
}
