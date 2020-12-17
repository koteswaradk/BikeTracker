package com.stratvave.biketracker.expenses;

import java.util.ArrayList;

import com.stratvave.biketracker.addvehicle.ViewMyBikeDetails;
import com.stratvave.biketracker.databases.DataHelper;
import com.stratvave.biketracker.main.R;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class DeletExpencesDetails extends ListActivity{
	ListView listview;
	ArrayList<String> vename;
	String bname,date;
	ArrayList<String> al;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.deleyexpenceswithdate);
		 listview=getListView();
		TextView tv=(TextView) findViewById(R.id.tv);
		tv.setText("select date to delete its content");
		 vename=new ArrayList<String>();
		 Intent data=getIntent();
		 bname=data.getStringExtra("bikename");
		 DataHelper db=new DataHelper(this);
		 db.open();
	     vename= db.getExpencesDate(bname);
		 db.close();
		// setListAdapter();	
		 listview.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,vename));
		/* listview.setOnCreateContextMenuListener(new OnCreateContextMenuListener() {
			
			@Override
			public void onCreateContextMenu(ContextMenu menu, View v,
					ContextMenuInfo menuInfo) {
			
				// TODO Auto-generated method stub
				menu.setHeaderTitle("select Options");
				MenuInflater infater=getMenuInflater();
				infater.inflate(R.menu.list_menu, menu);
				
				
			}
		});*/
	}
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.expencemenu_view:
			 
			
			break;	
		
		case R.id.expencemenu_delete:
			 DataHelper database=new DataHelper(DeletExpencesDetails.this);
			 database.open();
		      Boolean k= database.deleteBikeExpencesDetails(date);
		      database.close();
		      if (k) {
				Toast.makeText(DeletExpencesDetails.this, "Deleted expences details of of ate"+date, Toast.LENGTH_LONG).show();
			}
			finish();
			break;
		
		}
		return true;
	}
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		date=vename.get(position);
		System.out.println(date);
		 DataHelper database=new DataHelper(DeletExpencesDetails.this);
		 database.open();
	      Boolean k= database.deleteBikeExpencesDetails(date);
	      database.close();
	      if (k) {
			Toast.makeText(DeletExpencesDetails.this, "Deleted expences details of of ate"+date, Toast.LENGTH_LONG).show();
		}
		finish();
		/*//Toast.makeText(ViewMyBikeDetails.this, vename.get(position).toString(), Toast.LENGTH_SHORT).show();
		Intent bikename=new Intent(ExpencesDateDisplay.this, ExpencesDateDisplay.class);
		bikename.putExtra("date", vename.get(position));
		
		startActivity(bikename);
		
		 DataHelper db=new DataHelper(DeletExpencesDetails.this);
		 db.open();
	      al= db.getBikExpencesDetails(date);
		 db.close();
		 if (al.isEmpty()) {
			 Toast.makeText(DeletExpencesDetails.this, "expences for the is not added", Toast.LENGTH_SHORT).show();
			
		}else{
		 final AlertDialog builder = new AlertDialog.Builder(DeletExpencesDetails.this).create();
		 View  layout=new View(DeletExpencesDetails.this);	
		 LayoutInflater inflater=LayoutInflater.from(DeletExpencesDetails.this);
		 	builder.requestWindowFeature(Window.FEATURE_NO_TITLE);
		 	builder.setCancelable(false);
		    builder.getWindow().setBackgroundDrawable(new ColorDrawable(0));
		   
		    layout = inflater.inflate(R.layout.viewexpences, null);
		 
		  
		    builder.setView(layout,0,0,0,0);
		   
		   System.out.println(al);
		      TextView ti0=  (TextView) layout.findViewById(R.id.expencesbikename_set);
			  TextView ti1= (TextView)layout. findViewById(R.id.expencefor_set);
			  TextView ti2= (TextView) layout.findViewById(R.id.expenceodo_set);
			  TextView ti3= (TextView)layout. findViewById(R.id.expencetotalcpst_set);
			  TextView ti4= (TextView)layout. findViewById(R.id.expencelocation_set);
			  TextView ti5= (TextView) layout.findViewById(R.id.expencepaymenttype_set);
			  TextView ti6= (TextView) layout.findViewById(R.id.expencesbikenote_set);
			  TextView ti7= (TextView) layout.findViewById(R.id.note_set);
			 if(!al.isEmpty()){
				  ti0.setText( al.get(4).toString());
				  ti1.setText( al.get(2).toString());
				  ti2.setText( al.get(1).toString());
				  ti3.setText( al.get(3).toString());
				  ti4.setText( al.get(5).toString());
				  ti5.setText( al.get(6).toString());
				  ti6.setText( al.get(0).toString());
				  ti7.setText( al.get(7).toString());
			 }else{
				 Toast.makeText(DeletExpencesDetails.this, "Expence setails is not added", Toast.LENGTH_SHORT).show();
					builder.dismiss();
			 }
			  layout.findViewById(R.id.ok_button).setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						
						//startActivity(new Intent(Extras.this, MainBikeTrackerActivity.class));
						finish();		 
						//}
						DataHelper dh=new DataHelper(ExpencesDateDisplay.this);
						dh.open();
						ArrayList< String> al=dh.getBikeName();
						dh.close();
						Toast.makeText(ExpencesDateDisplay.this, "Ok clicked"+al, Toast.LENGTH_SHORT).show();
						builder.dismiss();
						
					}
				});
			    layout.findViewById(R.id.cancel_button).setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Toast.makeText(ExpencesDateDisplay.this, "Cancel clicked", Toast.LENGTH_SHORT).show();
						builder.dismiss();
					}
				});
			   
			builder.show();
		}*/
}
}