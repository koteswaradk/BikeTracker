package com.stratvave.biketracker.expenses;

import java.util.ArrayList;

import com.stratvave.biketracker.addvehicle.ViewMyBikeDetails;
import com.stratvave.biketracker.databases.DataHelper;
import com.stratvave.biketracker.main.R;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class DeleteExpences extends ListActivity{
	
		private DataHelper dh;
		String name,regno;
		ListView lv1;
		ViewMyBikeDetails vbd;
		ListView listview;
		ArrayList<String> vename;
		 ArrayList<String> al;
		 String bname;
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);
			requestWindowFeature(Window.FEATURE_NO_TITLE);
			setContentView(R.layout.viewmybikedetails);
			TextView tv=(TextView) findViewById(R.id.tv);
			tv.setText("Seelect Your Bike To delete Its Expences ");
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
			 bname=vename.get(position);
			System.out.println(bname);
			//Toast.makeText(ViewMyBikeDetails.this, vename.get(position).toString(), Toast.LENGTH_SHORT).show();
			Intent bikename=new Intent(DeleteExpences.this, DeletExpencesDetails.class);
			bikename.putExtra("bikename", vename.get(position));
			
			startActivity(bikename);
	}

}
