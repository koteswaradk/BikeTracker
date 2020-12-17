package com.stratvave.biketracker.main;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.stratvave.biketracker.addvehicle.AddBikePurchaseData;
import com.stratvave.biketracker.addvehicle.Bike;
import com.stratvave.biketracker.databases.DataHelper;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


public class MainBikeTrackerActivity extends Activity {
	
   	ConnectivityManager connec;
	 ConnectivityManager connectivity;
	 NetworkInfo wifiInfo, mobileInfo;
	 WifiManager wifimanager;
	 AlertDialog connectdialog;
	 boolean condition;
	 TextView tv;
	 private ArrayList<String> textfield;
     private ArrayList<Integer> imagefield;
     private ImageAdapter imageadapter;
	
	
	 class  MobileLoginAsynchTask extends AsyncTask<Void, ProgressBar, Boolean>{
		 Boolean mobil;
		 ProgressDialog dialog;
		@Override
		protected Boolean doInBackground(Void... params) {
			// TODO Auto-generated method stub
			 for(int i=0;i<20;i++){
				 setProgress(5);

	                try {
	                	 mobil=setMobileDataEnabled(MainBikeTrackerActivity.this,true);
	                	
	                    Thread.sleep(1000);// the timing set to a large value
	                } catch (InterruptedException e) {
	                    // TODO Auto-generated catch block
	                    e.printStackTrace();
	              
	            }
	                if (mobil) {
	                	dialog.dismiss();
	                	return true;
					}else{
						LayoutInflater inflater = getLayoutInflater();
				    	 View layout = inflater.inflate(R.layout.customtoast,(ViewGroup) findViewById(R.id.toast_layout_root));

				    	 TextView text = (TextView) layout.findViewById(R.id.text);
				    	 text.setText("not connected Check your Data Pack");
				    	 text.setTextSize(20);
				    	 Toast toast = new Toast(getApplicationContext());
				    	 toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
				    	 toast.setDuration(Toast.LENGTH_LONG);
				    	 toast.setView(layout);
				    	 toast.show();
						
					}
			 
	            
			 }
			
			 return false;
			
		}		
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			 
			    dialog = new ProgressDialog(MainBikeTrackerActivity.this);
	            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
	            dialog.setTitle(" Please Wait Data Pack is Connecting.....");
	            dialog.setMax(500);
	            dialog.setCancelable(false);
	            dialog.show();
		}

		
		
		
	}
	 
	class  WifiLoginAsynchTask extends AsyncTask<Void, ProgressBar, Boolean>{

		Boolean scucess=false;
		 ProgressDialog dialog;
		@Override
		protected Boolean doInBackground(Void... params) {
			// TODO Auto-generated method stub
			 for(int i=0;i<20;i++){
				 setProgress(5);

	                try {
	                	connectivity = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
	        		    wifiInfo = connectivity.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
	        		    wifimanager=(WifiManager) MainBikeTrackerActivity.this.getSystemService(Context.WIFI_SERVICE);
	        		    wifimanager.startScan();
	        		    scucess=wifimanager.setWifiEnabled(true);
	        		    
	                
	        		    if (scucess) {
	        		    	 System.out.println("returned true"+scucess);
	        		    	 return true;
	        		    	
						}
	                    Thread.sleep(1000);// the timing set to a large value
	                } catch (Exception e) {
	                    // TODO Auto-generated catch block
	                    e.printStackTrace();
	              
	            }
	             	            
			 }
			 System.out.println("returned false"+scucess);
			 return false;
			
			
			
		}
		
		
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			 dialog = new ProgressDialog(MainBikeTrackerActivity.this);
	            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
	            dialog.setTitle(" Please Wait WiFi is Connecting.....");
	            dialog.setMax(500);
	            dialog.setCancelable(false);
	            dialog.show();
	            System.out.println(" progress dislaed");
		}
		@Override
		protected void onPostExecute(Boolean result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			 System.out.println("onpost execute");
			dialog.dismiss();
		}

		
		
		
	}
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.mainpage);
         
         connectdialog=new AlertDialog.Builder(this).create();
       //  tv=(TextView)findViewById(R.id.gridtext);
         preparetext();
         prepareimage();
        GridView gv= (GridView) findViewById(R.id.gridv);
        imageadapter=new ImageAdapter(this, textfield, imagefield);
        gv.setAdapter(imageadapter);
       
        gv.setOnItemClickListener(new OnItemClickListener() {

        	
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) { 
				// TODO Auto-generated method stub
				
				
				switch (position) {
				
				case  0: Intent i1= new Intent(MainBikeTrackerActivity.this,com.stratvave.biketracker.addvehicle.Bike.class);
					     startActivity(i1);
					     break;
					     
				case  1: 
					DataHelper db1=new DataHelper(MainBikeTrackerActivity.this);
					 db1.open();
					ArrayList<String> arraylist1= db1.getBikeName();
					db1.close();
					if (arraylist1.isEmpty()) {
						LayoutInflater inflater = getLayoutInflater();
				    	 View layout = inflater.inflate(R.layout.customtoast,
				    	                                (ViewGroup) findViewById(R.id.toast_layout_root));

				    	 TextView text = (TextView) layout.findViewById(R.id.text);
				    	 text.setText("YOU NEED TO ADD VEHICLE DETAILS FIRST");
		                 text.setTextSize(20);
				    	 Toast toast = new Toast(getApplicationContext());
				    	 toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
				    	 toast.setDuration(Toast.LENGTH_LONG);
				    	 toast.setView(layout);
				    	 toast.show();
					
						//finish();
					} else{
						Intent i2= new Intent(MainBikeTrackerActivity.this,com.stratvave.biketracker.fillup.Fillup.class);
			     		startActivity(i2);
					}
					
					
					
			     		break;
			     
				case  2: 
					DataHelper db2=new DataHelper(MainBikeTrackerActivity.this);
					 db2.open();
					ArrayList<String> arraylist2= db2.getBikeName();
					db2.close();
					if (arraylist2.isEmpty()) {
						LayoutInflater inflater = getLayoutInflater();
				    	 View layout = inflater.inflate(R.layout.customtoast,
				    	                                (ViewGroup) findViewById(R.id.toast_layout_root));

				    	 TextView text = (TextView) layout.findViewById(R.id.text);
				    	 text.setText("YOU NEED TO ADD VEHICLE DETAILS FIRST");
		               text.setTextSize(20);
				    	 Toast toast = new Toast(getApplicationContext());
				    	 toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
				    	 toast.setDuration(Toast.LENGTH_LONG);
				    	 toast.setView(layout);
				    	 toast.show();
						//finish();
					} else{
						Intent i3= new Intent(MainBikeTrackerActivity.this,com.stratvave.biketracker.expenses.Expencesmain.class);
						startActivity(i3);
					}
					
					
					
						break;
			     
				case  3: 
					
					DataHelper db3=new DataHelper(MainBikeTrackerActivity.this);
					 db3.open();
					ArrayList<String> arraylist4= db3.getBikeName();
					db3.close();
					if (arraylist4.isEmpty()) {
						LayoutInflater inflater = getLayoutInflater();
				    	 View layout = inflater.inflate(R.layout.customtoast,
				    	                                (ViewGroup) findViewById(R.id.toast_layout_root));

				    	 TextView text = (TextView) layout.findViewById(R.id.text);
				    	 text.setText("YOU NEED TO ADD VEHICLE DETAILS FIRST");
		               text.setTextSize(20);
				    	 Toast toast = new Toast(getApplicationContext());
				    	 toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
				    	 toast.setDuration(Toast.LENGTH_LONG);
				    	 toast.setView(layout);
				    	 toast.show();
						//finish();
					} else{
						Intent i4= new Intent(MainBikeTrackerActivity.this,com.stratvave.biketracker.trip.Trip.class);
			     		startActivity(i4);
					}
					
			     		break;
			     
				case  4: 
					
					DataHelper db15=new DataHelper(MainBikeTrackerActivity.this);
					 db15.open();
					ArrayList<String> arraylist15= db15.getBikeName();
					db15.close();
					if (arraylist15.isEmpty()) {
						LayoutInflater inflater = getLayoutInflater();
				    	 View layout = inflater.inflate(R.layout.customtoast,
				    	                                (ViewGroup) findViewById(R.id.toast_layout_root));

				    	 TextView text = (TextView) layout.findViewById(R.id.text);
				    	 text.setText("YOU NEED TO ADD VEHICLE DETAILS FIRST");
		                 text.setTextSize(20);
				    	 Toast toast = new Toast(getApplicationContext());
				    	 toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
				    	 toast.setDuration(Toast.LENGTH_LONG);
				    	 toast.setView(layout);
				    	 toast.show();
					
						//finish();
					} else{
						Intent i5= new Intent(MainBikeTrackerActivity.this,com.stratvave.biketracker.reminders.Reminders.class);
			     		startActivity(i5);
					}
				/*	Intent i5= new Intent(MainBikeTrackerActivity.this,com.stratvave.biketracker.reminders.Reminders.class);
		     		startActivity(i5);*/
					
			     		break;
			     
				case  5: 
					DataHelper db6=new DataHelper(MainBikeTrackerActivity.this);
					 db6.open();
					ArrayList<String> arraylist6= db6.getBikeName();
					db6.close();
					if (arraylist6.isEmpty()) {
						LayoutInflater inflater = getLayoutInflater();
				    	 View layout = inflater.inflate(R.layout.customtoast,
				    	                                (ViewGroup) findViewById(R.id.toast_layout_root));

				    	 TextView text = (TextView) layout.findViewById(R.id.text);
				    	 text.setText("YOU NEED TO ADD VEHICLE DETAILS FIRST");
		               text.setTextSize(20);
				    	 Toast toast = new Toast(getApplicationContext());
				    	 toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
				    	 toast.setDuration(Toast.LENGTH_LONG);
				    	 toast.setView(layout);
				    	 toast.show();
						//finish();
					} else{
						DataHelper dh=new DataHelper(MainBikeTrackerActivity.this);
					    dh.open();
					  ArrayList<String> a= dh.getOdometerFromFillUp();
					  dh.close();
					 if (a.size()==2) {
						  /*System.out.println("second"+a.get(0));
						  System.out.println("first"+a.get(1));
						    int second=(Integer.parseInt(a.get(0)));
						    int first=(Integer.parseInt(a.get(1)));*/
						 Intent i6= new Intent(MainBikeTrackerActivity.this,com.stratvave.biketracker.statistics.Statistics.class);
				     		startActivity(i6);
					}else {
						Toast.makeText(MainBikeTrackerActivity.this, "You Are Not Added Second  FillUp Data", Toast.LENGTH_SHORT).show();
					}
						
					}
					
			     		break;
			     
				case  6: 
					DataHelper dh = new DataHelper(MainBikeTrackerActivity.this);
					dh.open();

					ArrayList<Integer> odometer_list = DataHelper.getOdometerFromFillUpDouble();
					ArrayList<Integer> price_list = DataHelper.getpriceFillUp();
					List<Integer> allodometer= DataHelper.getOdometerFromFillUpDouble();
					List<Integer> allfillup_amount=DataHelper.getLiterAllFillUpINDouble();

					dh.close();
					if (odometer_list.isEmpty()&&(price_list.isEmpty()&&(allodometer.isEmpty()&&(allfillup_amount.isEmpty())))) {
						Toast.makeText(MainBikeTrackerActivity.this, "Sorry No Data To Display Charts", Toast.LENGTH_SHORT).show();
					}
					else{
					Intent i7= new Intent(MainBikeTrackerActivity.this,com.stratvave.biketracker.charts.Charts.class);
			     		startActivity(i7);
					}
			     		break;
			     
				case  7: 
					
					 condition= CheckInternet();
					 if (condition) {
						
						/* Intent i=new Intent(Intent.ACTION_MAIN);

						 PackageManager manager = getPackageManager();

						 i = manager.getLaunchIntentForPackage("latify.apk");//apk name

						 i.addCategory(Intent.ACTION_DEFAULT);

						 startActivity(i);*/
						 Intent intent = new Intent(android.content.Intent.ACTION_VIEW,Uri.parse("http://m.google.com/latitude"));
							 		intent.setClassName("com.google.android.apps.maps","com.google.android.maps.MapsActivity");
							 		startActivity(intent);
							 		
							 		//Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse("latitude://latitude/friends/location/example@gmail.com"));
						 		/*	Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse("latitude://latitude"));
						 			intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
							 		intent.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
							 		startActivity(intent);*/
						
						
					 }
					
					 else  {
							/*LayoutInflater inflater = getLayoutInflater();
					    	 View layout = inflater.inflate(R.layout.customtoast,
					    	                                (ViewGroup) findViewById(R.id.toast_layout_root));

					    	 TextView text = (TextView) layout.findViewById(R.id.text);
					    	 text.setText("Not Connected To Internet Your Data Connection Unavilable ");
			               text.setTextSize(20);
					    	 Toast toast = new Toast(getApplicationContext());
					    	 toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
					    	 toast.setDuration(Toast.LENGTH_LONG);
					    	 toast.setView(layout);
					    	 toast.show();*/
						
						
						connectdialog.setTitle("No Network is Connected Connect to your WiFi Or Data Pack");
						connectdialog.setCanceledOnTouchOutside(false);
						
						connectdialog.setButton("ConnectTo Wifi", new DialogInterface.OnClickListener() {
							
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								WifiLoginAsynchTask wifienable=new WifiLoginAsynchTask();
								wifienable.execute();
								
								
				               
								
							}
						});
						connectdialog.setButton2( "Connect to Data Pack", new DialogInterface.OnClickListener() {
							
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub					
								MobileLoginAsynchTask mobcheck=new MobileLoginAsynchTask();
								mobcheck.execute();
								 
								
							}
						});
						connectdialog.setButton3( "Cancel", new DialogInterface.OnClickListener() {
							
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								Toast.makeText(MainBikeTrackerActivity.this, "Canceled", Toast.LENGTH_SHORT).show();
								
								
							}
						});
						connectdialog.show();
					}
						
						//startActivity(new Intent().setClass(MainBikeTrackerActivity.this,OAuthAccessTokenActivity.class));
						
					
					 
					
			     		break;
			     
				case  8: 
					 condition= CheckInternet();
					 if (condition) {
					
					Intent intent = new
						Intent(android.content.Intent.ACTION_VIEW,Uri.parse("http://maps.google.com/maps?saddr=&daddr="));
				 		intent.setClassName("com.google.android.apps.maps","com.google.android.maps.MapsActivity");
				 		startActivity(intent);
					 }else{
							LayoutInflater inflater = getLayoutInflater();
					    	 View layout = inflater.inflate(R.layout.customtoast,
					    	                                (ViewGroup) findViewById(R.id.toast_layout_root));

					    	 TextView text = (TextView) layout.findViewById(R.id.text);
					    	 text.setText("Not Connected To Internet Your Data Connection Unavilable ");
			               text.setTextSize(20);
					    	 Toast toast = new Toast(getApplicationContext());
					    	 toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
					    	 toast.setDuration(Toast.LENGTH_LONG);
					    	 toast.setView(layout);
					    	 toast.show();
					 }
			     		break;
			     
				case  9: 
					Intent i10= new Intent(MainBikeTrackerActivity.this,com.stratvave.autoanswer.AutoAnswerPreferenceActivity.class);
		     		startActivity(i10);
					
					/*Intent i10= new Intent(MainBikeTrackerActivity.this,com.stratvave.biketracker.settings.Settings.class);
			     		startActivity(i10);*/
			     		break;
				case  10: 
					
					DataHelper db9=new DataHelper(MainBikeTrackerActivity.this);
					 db9.open();
					ArrayList<String> arraylist9= db9.getBikeName();
					db9.close();
					if (arraylist9.isEmpty()) {
						LayoutInflater inflater = getLayoutInflater();
				    	 View layout = inflater.inflate(R.layout.customtoast,(ViewGroup) findViewById(R.id.toast_layout_root));

				    	 TextView text = (TextView) layout.findViewById(R.id.text);
				    	 text.setText("YOU NEED TO ADD VEHICLE DETAILS FIRST");
		               text.setTextSize(20);
				    	 Toast toast = new Toast(getApplicationContext());
				    	 toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
				    	 toast.setDuration(Toast.LENGTH_LONG);
				    	 toast.setView(layout);
				    	 toast.show();
					}else{
					
					Intent i11= new Intent(MainBikeTrackerActivity.this,com.stratvave.biketracker.extras.Extras.class);
 						startActivity(i11);
					}
 						break;
				case  11: Intent i12= new Intent(MainBikeTrackerActivity.this,com.stratvave.biketracker.aboutapp.AboutApp.class);
	     				startActivity(i12);
	     				break;

				
				}
			}
		});
    }
    
    
  private void prepareimage() {
		// TODO Auto-generated method stub
	 
	  imagefield=new ArrayList<Integer>();
	  
      imagefield.add( R.drawable.bikemanage);

      imagefield.add(R.drawable.fuelfillup);

      imagefield.add(R.drawable.expenses);

      imagefield.add(R.drawable.biketrip);

      imagefield.add(R.drawable.remainder);

      imagefield.add(R.drawable.statistics);

      imagefield.add(R.drawable.chart);
      
      imagefield.add(R.drawable.latitude);
      
      imagefield.add(R.drawable.routemap);
      
      imagefield.add(R.drawable.settings);
      
      imagefield.add(R.drawable.extras);
      
      imagefield.add(R.drawable.aboutapp);
	}


private void preparetext() {
		// TODO Auto-generated method stub
	  
	textfield=new ArrayList<String>();
      textfield.add("   Manage");

      textfield.add("    FillUP");

      textfield.add(" Expences");

      textfield.add("  BikeTrip");

      textfield.add(" Reminder");

      textfield.add("  Statistics");
      
      textfield.add("   Charts");

      textfield.add("  Latitude");
      
      textfield.add(" RouteMap");

      textfield.add("   Settings");

      textfield.add("    Extras");

      textfield.add("AboutApp");

	}


/*  private class Callback extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return(false);
        }
    } */ 
/*private int set() {
	// TODO Auto-generated method stub
	
}*/
    @Override
    public void onBackPressed() {
    	// TODO Auto-generated method stub
    	super.onBackPressed();
    	MainBikeTrackerActivity.this.finish();
    	System.exit(0);
    	
    }
  
    private Boolean setMobileDataEnabled(Context context, boolean enabled) {
	    final ConnectivityManager conman = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
	    try {
	    	final Class conmanClass = Class.forName(conman.getClass().getName());
	 	    final Field iConnectivityManagerField = conmanClass.getDeclaredField("mService");
	 	    iConnectivityManagerField.setAccessible(true);
	 	    final Object iConnectivityManager = iConnectivityManagerField.get(conman);
	 	    final Class iConnectivityManagerClass = Class.forName(iConnectivityManager.getClass().getName());
	 	    final Method setMobileDataEnabledMethod = iConnectivityManagerClass.getDeclaredMethod("setMobileDataEnabled", Boolean.TYPE);
	 	    setMobileDataEnabledMethod.setAccessible(true);

	 	    setMobileDataEnabledMethod.invoke(iConnectivityManager, enabled);
			
		} catch (Exception e) {
			// TODO: handle exception
			LayoutInflater inflater = getLayoutInflater();
	    	 View layout = inflater.inflate(R.layout.customtoast,
	    	                                (ViewGroup) findViewById(R.id.toast_layout_root));

	    	 TextView text = (TextView) layout.findViewById(R.id.text);
	    	 text.setText("SetUp Your Data Pack ");
          text.setTextSize(20);
	    	 Toast toast = new Toast(getApplicationContext());
	    	 toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
	    	 toast.setDuration(Toast.LENGTH_LONG);
	    	 toast.setView(layout);
	    	 toast.show();
		}
		return true;
	   
	}
	
	public boolean CheckInternet() 
	{
	    ConnectivityManager connec = (ConnectivityManager) MainBikeTrackerActivity.this.getSystemService(Context.CONNECTIVITY_SERVICE);
	    android.net.NetworkInfo wifi = connec.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
	    android.net.NetworkInfo mobile = connec.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
	    if (wifi.isConnected()||mobile.isConnected()) {
	        return true;
	    } 
	    return false;
	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.splashscreen, menu);
        return true;
    }
}


class ImageAdapter extends ArrayAdapter<Object>{
	
	

	 public ImageAdapter(Context c,ArrayList<String> name,ArrayList<Integer> image) {
		 super(c, 0);
		// TODO Auto-generated constructor stub
		     mcontext=c;
			this.textfield=name;
			 
	        this.imagefield=image;
	}

	private Context mcontext;
	 ArrayList<String> textfield;
	 ArrayList<Integer> imagefield;
	public int getCount() {
		// TODO Auto-generated method stub
		return icons.length;
	}

	
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
	

	
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		//ImageView ib;
		ImageView imageView;
		 TextView tv;
		 tv=new TextView(mcontext);
		int k=set(mcontext);
		if(convertView==null)
		{
			LayoutInflater inflator = ((Activity) mcontext).getLayoutInflater(); 

            convertView = inflator.inflate(R.layout.customgridview,null); 
            imageView= (ImageView) convertView.findViewById(R.id.cgimage);
			// imageView = new ImageView(mcontext);
            tv=(TextView) convertView.findViewById(R.id.cgtext);
		    imageView.setImageResource(imagefield.get(position));
		    tv.setText(textfield.get(position));
		    imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
		   /* imageView.setLayoutParams(new GridView.LayoutParams(
		            (int) mcontext.getResources().getDimension(R.dimen.width),                                                                                   
		            (int) mcontext.getResources().getDimension(R.dimen.height)));*/
		   //imageView.setLayoutParams(new GridView.LayoutParams( GridView.LayoutParams.WRAP_CONTENT,GridView.LayoutParams.WRAP_CONTENT));
		  //  imageView.setLayoutParams(new GridView.LayoutParams( 60,60));
		  // imageView.setLayoutParams(new GridView.LayoutParams(k,k));
		   
		   return convertView;
		    //imageView.setPadding(5, 5, 5, 5);
		}
		return convertView;
		    
			/*ib= new ImageView(mcontext);
			ib.setLayoutParams(new GridView.LayoutParams(k,k));
			ib.setScaleType(ImageButton.ScaleType.CENTER_CROP);
			ib.setPadding(8, 8, 8, 8);
			imageView.setImageResource(mThumbIds[position]);
	        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
	        imageView.setLayoutParams(new GridView.LayoutParams(imgSize,imgSize ));

			
		}*/
		/*}
		else
		{
			imageView=(ImageView) convertView;
		}
		
		//imageView.setImageResource(icons[position]);
		
		//return imageView;
		    
		
		return imageView;*/
	
		

	}
	
	private int set(Context c) {
		// TODO Auto-generated method stub
		float scale;
		//int imgSize;
		 
		 final int scaleImage= (int) mcontext.getResources().getDimension(R.dimen.size);
		
		 scale= c.getResources().getDisplayMetrics().density;
	   
	    return  (int) (scaleImage * scale + 0.5f);
		
	}

	private Integer[] icons=
		{
			R.drawable.bikemanage,R.drawable.fuelfillup,
			R.drawable.expenses,R.drawable.biketrip,
			R.drawable.remainder,R.drawable.statistics,
			R.drawable.chart,R.drawable.latitude,
			R.drawable.routemap,R.drawable.settings,
			R.drawable.extras,R.drawable.aboutapp
					
		};
	private String[] names={"Managebike","FillUP","Expences","Road Trip","Reminders","Statistics","Charts","Settings","Latitude","Extras","AboutApp","routemap"};


	
}
