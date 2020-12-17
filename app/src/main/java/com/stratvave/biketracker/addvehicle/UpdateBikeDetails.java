package com.stratvave.biketracker.addvehicle;

import java.util.ArrayList;

import com.stratvave.biketracker.databases.DataHelper;
import com.stratvave.biketracker.main.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class UpdateBikeDetails extends ListActivity{
	private DataHelper dh;
	String name,regno,bikename;
	ArrayList<String> vename;
	DataHelper db;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.updatebikedetails);
		 vename=new ArrayList<String>();
		 db=new DataHelper(this);
		 db.open();
	     vename= db.getBikeName();
		 db.close();
		 setListAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,vename));	
		 
				
	}
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
	     bikename=vename.get(position);
	    db=new DataHelper(this);
	    Intent i=new Intent(UpdateBikeDetails.this, UpdateBike.class);
	    i.putExtra("bikename", bikename);
	    startActivity(i);
//	    UpdatetoDB up=new UpdatetoDB(this,bikename);
//	    up.execute();
//	    
	  /*  AlertDialog ad=new AlertDialog.Builder(this).create();
	    ad.setTitle("Confirm To Delete Bike"+bikename+"Details Permanently");
	    ad.setButton("ConFirm", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				
				 db.open();
			     Boolean  returnvalue= db.deleteBikeDetails(bikename);
				 db.close();	
				 if (returnvalue) {
					 Toast.makeText(UpdateBikeDetails.this, "Your "+bikename +"Details Are Deleted", Toast.LENGTH_SHORT).show();
					 finish();
				}else{
					System.out.println("deleted>>>>>>>>>>>>>>>>>>>>>>"+returnvalue);
				}

			}
		});
	    ad.setButton2("Cancel", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				finish();
			}
		});
	    
	    ad.show();*/
		
	}
}
