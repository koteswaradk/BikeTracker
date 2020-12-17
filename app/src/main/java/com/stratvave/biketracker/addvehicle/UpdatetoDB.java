package com.stratvave.biketracker.addvehicle;

import com.stratvave.biketracker.databases.DataHelper;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.util.Log;

public class UpdatetoDB extends AsyncTask<Integer, Integer, Void>{

	public static Context a_ctx;
	
	ProgressDialog progressDialog ; 
	String bikename;
	
	public UpdatetoDB(Context addVehicle) {
		// TODO Auto-generated constructor stub
		
		a_ctx=addVehicle;
		
		progressDialog=new ProgressDialog(a_ctx);
		progressDialog.setMessage("Updating to database");
		progressDialog.setMax(100);
		progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		//progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);		
	}
	public UpdatetoDB(UpdateBikeDetails updateBikeDetails, String bikename) {
		// TODO Auto-generated constructor stub
        a_ctx=updateBikeDetails;
		
		progressDialog=new ProgressDialog(a_ctx);
		progressDialog.setMessage("Updating to database");
		progressDialog.setMax(100);
		progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
	}
	private void updateDb() {
		// TODO Auto-generated method stub

		DataHelper helper=new DataHelper(a_ctx);
		helper.open();
		//helper.updateBikeDetails(bikename);
		helper.close();
	}
	@Override
	protected Void doInBackground(Integer... params) {
		// TODO Auto-generated method stub
			
			int start=params[0];
			
			for(int i=start;i<=100;i+=25)
			{
				
				try {
				
					updateDb();
					boolean cancelled=isCancelled();
					if(!cancelled)
					{
						publishProgress(i);
						SystemClock.sleep(1000);
					}
					
				} 
				
				catch (Exception e) {
					// TODO: handle exception
					Log.e("Error", e.toString());
				}
			}
		
		return null;
	}
	
	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
		progressDialog.setMax(100);
	}
	
	@Override
	protected void onPostExecute(Void result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		progressDialog.cancel();
		Log.v("Progress", "Finished");
	}
	
	@Override
	protected void onProgressUpdate(Integer... values) {
		// TODO Auto-generated method stub
		super.onProgressUpdate(values);
		progressDialog.setProgress(values[0]);
		progressDialog.show();
	}

	@Override
	protected void onCancelled() {
		// TODO Auto-generated method stub
		super.onCancelled();
		progressDialog.setMax(0);
	}
	
}
