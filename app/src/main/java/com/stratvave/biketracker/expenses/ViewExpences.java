package com.stratvave.biketracker.expenses;

import java.util.ArrayList;

import com.stratvave.biketracker.addvehicle.DisplayBikeDetails;
import com.stratvave.biketracker.addvehicle.ViewMyBikeDetails;
import com.stratvave.biketracker.databases.DataHelper;
import com.stratvave.biketracker.main.R;
import com.stratvave.biketracker.main.R.id;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ViewExpences extends ListActivity{
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
		tv.setText("Seelect Your Bike To See Its Expences ");
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
		Intent bikename=new Intent(ViewExpences.this, ExpencesDateDisplay.class);
		bikename.putExtra("bikename", vename.get(position));
		
		startActivity(bikename);
		
		 
		/* final AlertDialog builder = new AlertDialog.Builder(ViewExpences.this).create();
		 View  layout=new View(ViewExpences.this);	
		 LayoutInflater inflater=LayoutInflater.from(ViewExpences.this);
		 	builder.requestWindowFeature(Window.FEATURE_NO_TITLE);
		 	builder.setCancelable(false);
		    builder.getWindow().setBackgroundDrawable(new ColorDrawable(0));
		   
		    layout = inflater.inflate(R.layout.viewexpences, null);
		 
		  
		    builder.setView(layout,0,0,0,0);
		    DataHelper db=new DataHelper(ViewExpences.this);
			 db.open();
		      al= db.getBikExpencesDetails(bname);
			 db.close();
		   System.out.println(al);
		      TextView ti0=  (TextView) layout.findViewById(R.id.expencesbikename_set);
			  TextView ti1= (TextView)layout. findViewById(R.id.expencefor_set);
			  TextView ti2= (TextView) layout.findViewById(R.id.expenceodo_set);
			  TextView ti3= (TextView)layout. findViewById(R.id.expencetotalcpst_set);
			  TextView ti4= (TextView)layout. findViewById(R.id.expencelocation_set);
			  TextView ti5= (TextView) layout.findViewById(R.id.expencepaymenttype_set);
			  TextView ti6= (TextView) layout.findViewById(R.id.expencesbikenote_set);
			 if(!al.isEmpty()){
				  ti0.setText( al.get(4).toString());
				  ti1.setText( al.get(2).toString());
				  ti2.setText( al.get(1).toString());
				  ti3.setText( al.get(3).toString());
				  ti4.setText( al.get(5).toString());
				  ti5.setText( al.get(6).toString());
				  ti6.setText( al.get(0).toString());
			 }else{
				 Toast.makeText(ViewExpences.this, "Expence setails is not added", Toast.LENGTH_SHORT).show();
					builder.dismiss();
			 }
			  layout.findViewById(R.id.ok_button).setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						
						//startActivity(new Intent(Extras.this, MainBikeTrackerActivity.class));
						
						 
							 
						//}
						DataHelper dh=new DataHelper(ViewExpences.this);
						dh.open();
						ArrayList< String> al=dh.getBikeName();
						dh.close();
						Toast.makeText(ViewExpences.this, "Ok clicked"+al, Toast.LENGTH_SHORT).show();
						builder.dismiss();
						
					}
				});
			    layout.findViewById(R.id.cancel_button).setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Toast.makeText(ViewExpences.this, "Cancel clicked", Toast.LENGTH_SHORT).show();
						builder.dismiss();
					}
				});
			   
			builder.show();*/
		
		
	
	}
}
