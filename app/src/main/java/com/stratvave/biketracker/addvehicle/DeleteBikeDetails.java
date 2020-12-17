package com.stratvave.biketracker.addvehicle;

import java.util.ArrayList;

import com.stratvave.biketracker.databases.DataHelper;
import com.stratvave.biketracker.main.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class DeleteBikeDetails extends ListActivity{
	private DataHelper dh;
	String name,regno,bikename;
	ArrayList<String> vename;
	
	Boolean  returnvalue1,returnvalue,returnvalue2, returnvalue3,check1=false,check2=false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.deletebikedetails);
		 vename=new ArrayList<String>();
		 DataHelper db=new DataHelper(this);
		 db.open();
	     vename= db.getBikeName();
		 db.close();
		 setListAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,vename));	
		

		}
		 
	
	//}
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		
	     bikename=vename.get(position);
	    dh =new DataHelper(this);
	    AlertDialog ad=new AlertDialog.Builder(this).create();
	    ad.setTitle("Confirm To Delete Bike"+bikename+"Details Permanently");
	    ad.setButton("ConFirm", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				
				
				dh.open();
				returnvalue= dh.deleteBikeDetails(bikename);
				System.out.println("bike details deleted"+returnvalue);
			    ArrayList<String> bikepurchasedetails=dh.getBikPurchaseeDetails(bikename);
			    ArrayList<String> bikedeparturedetails=dh.getBikdepartureDetails(bikename);
			    ArrayList<String> bikeexpencesdetails=dh.getBikExpencesDetails(bikename);
				;
				 if(!bikepurchasedetails.isEmpty()){
						
						 returnvalue1= dh.deleteBikepurchaseDetails(bikename);
						 System.out.println("BikepurchaseDetails deleted");
						 
					}if(!bikedeparturedetails.isEmpty()) {
						//delete departure detiails
						 Boolean  returnvalue2= dh.deleteBikeDepartureDetails(bikename);
						 System.out.println("bike departure details deleted");
						
					}if(!bikeexpencesdetails.isEmpty()){
						//delete expences details
						returnvalue3= dh.deleteBikeExpencesDetails(bikename);
						System.out.println("bike expences details deleted");
						
					}
				 dh.close();
				 finish();
				 /*
			      
			   
			    // dh.open();
					
					//dh.close();
					if(!bikedetails1.isEmpty()){
						purchase_date_set.setText(":"+" "+bikedetails1.get(0).toString());
						purchase_price_set.setText(":"+" "+bikedetails1.get(1).toString());
						purchase_notes_set.setText(":"+" "+bikedetails1.get(2).toString());
						 returnvalue1= db.deleteBikepurchaseDetails(bikename);
						 returnvalue= db.deleteBikeDetails(bikename);
					}
					returnvalue= db.deleteBikeDetails(bikename);
				 if (((returnvalue)&&(returnvalue1))) {
					
					  returnvalue2= db.deleteBikeDepartureDetails(bikename);	
				}
				  
				 if (returnvalue2) {
					 check1=true;
					 returnvalue3= db.deleteBikeExpencesDetails(bikename);
					 
				}
				 db.close();
				 if (returnvalue3) {
					 check2=true;
					
				}
				 if (returnvalue&&returnvalue1&&check1&&check2) {
					 Toast.makeText(DeleteBikeDetails.this, "Your "+bikename +"Details Are Deleted", Toast.LENGTH_SHORT).show();
					 startActivity(new Intent(DeleteBikeDetails.this, Bike.class));
					 finish();
				}
				 else{
					System.out.println("deleted>>>>>>>>>>>>>>>>>>>>>>"+returnvalue+returnvalue1);
				}*/
				
			}
		});
	    ad.setButton2("Cancel", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				finish();
			}
		});
	    
	    ad.show();
		
	}
}
