package com.stratvave.biketracker.addvehicle;

import java.util.ArrayList;

import com.stratvave.biketracker.databases.DataHelper;
import com.stratvave.biketracker.main.R;

import android.R.integer;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ViewMyBikeDetails extends ListActivity {
	
	private DataHelper dh;
	String name,regno;
	ListView lv1;
	ViewMyBikeDetails vbd;
	ListView listview;
	ArrayList<String> vename;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.viewmybikedetails);
		listview=getListView();
		 vename=new ArrayList<String>();
		 DataHelper db=new DataHelper(this);
		 db.open();
	       vename= db.getBikeName();
		 db.close();
		 setListAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,vename));		
				
	}
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		vename.get(position);
		//Toast.makeText(ViewMyBikeDetails.this, vename.get(position).toString(), Toast.LENGTH_SHORT).show();
		Intent bikename=new Intent(ViewMyBikeDetails.this, DisplayBikeDetails.class);
		bikename.putExtra("bikename", vename.get(position));
		
		startActivity(bikename);
		
		
	
	}
	
}
