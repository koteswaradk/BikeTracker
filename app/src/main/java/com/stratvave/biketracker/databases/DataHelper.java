package com.stratvave.biketracker.databases;


import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;


public class DataHelper {

	static final String DATABASE_NAME="BikeTrackerDatabase.db";
	static final int DATABASE_VERSION=1;
	
	static final String TABLENAME_1="vehicle"; // ADD NEW BIKE DETAILS
	static final String TABLENAME_2="purchase"; // ADD BIKE PURCHASE DETAILS
	static final String TABLENAME_3="sale"; // ADD BIKE SALE DETAILS
	static final String TABLENAME_4="fillup"; //ADD FILL UP DETAILS
	static final String TABLENAME_5="expense";//ADD EXPENSES
	static final String TABLENAME_6="departure";//ADD DEPARTURE DETAILS
	static final String TABLENAME_7="arrival";//ADD ARRIVAL DETAILS
	static final String TABLENAME_8="reminder";//ADD REMINDER DETAILS
	
	static final String TABLENAME_9="licencedetails";
	static final String TABLENAME_10="roadtaxdetails";
	static final String TABLENAME_11="insurancedetails";
	static final String TABLENAME_12="settings";
	
	public static final String KEY_BikeId="bike_id";
	public static final String KEY_BikeName="bike_name";
	public static final String KEY_BikeRegNo="bike_regno";
	public static final String KEY_BikeManuf="bike_manuf";
	public static final String KEY_BikeType="type";
	public static final String KEY_BikeColor="bike_color";
	public static final String KEY_BikeModel="bike_model";
	public static final String KEY_BikeYOM="bike_yearofmanu";
	public static final String KEY_BikeRegDate="bike_regdate";
	public static final String KEY_BikeChassisNo="bike_chassis";
	public static final String KEY_BikeEngineNo="bike_engineno";
	public static final String KEY_BikeFuelType="bike_fueltype";
	public static final String KEY_BikeCC="bike_cc";
	public static final String KEY_BikeFCupto="bike_fc";
	
	public static final String KEY_PurchaseId="purchase_id";
	public static final String KEY_PurchaseDate="purchase_date";
	public static final String KEY_PurchasePrice="purchase_price";
	public static final String KEY_PurchaseNotes="purchase_notes";
	
	public static final String KEY_SaleId="sale_id";
	public static final String KEY_SaleDate="sale_date";
	public static final String KEY_SalePrice="sale_price";
	public static final String KEY_SaleNotes="sale_notes";
	
	public static final String KEY_FillupId="fillup_id";
	public static final String KEY_FillupType="fillup_type";
	public static final String KEY_FillupDate="fillup_date";
	public static final String KEY_FillupVolume="fillup_vol";
	public static final String KEY_FillupPPL="fillup_ppl";
	public static final String KEY_FillupOdo="fillup_odo";
	public static final String KEY_FillupTotalCost="fillup_totalcost";
	public static final String KEY_FillupVehName="fillup_vehname";
	public static final String KEY_FillupFuelcat="fillup_fuelcat";
	public static final String KEY_FillupFueltype="fillup_fueltype";
	public static final String KEY_FillupFuelbrand="fillup_fuelbrand";
	public static final String KEY_FillupFuelstation="fillup_fuelstation";
	public static final String KEY_FillupPaymentType="fillup_paymentType";
	public static final String KEY_FillupFueladditive="fillup_fueladditive";
	public static final String KEY_FillupNotes="fillup_notes";
	

	public static final String KEY_ExpId="exp_id";
	public static final String KEY_ExpDate="exp_date";
	public static final String KEY_ExpOdo="exp_odo";
	public static final String KEY_ExpType="exp_type";
	public static final String KEY_ExpTotalCost="exp_totalcost";
	public static final String KEY_ExpVehiclename="exp_vehiclename";
	public static final String KEY_ExpLocation="exp_location";
	public static final String KEY_ExpPayment="exp_payment";
	public static final String KEY_ExpNotes="exp_notes";
	
	public static final String KEY_DepId="dep_id";
	public static final String KEY_DepType="dep_type";
	public static final String KEY_DepPurpose="dep_purpose";
	public static final String KEY_DepClient="dep_client";
	public static final String KEY_DepVehicle="dep_vehicle";
	public static final String KEY_DepDatetime="dep_datetime";
	public static final String KEY_DepOdo="dep_odo";
	public static final String KEY_DepLocation="dep_location";
	public static final String KEY_DepNotes="dep_notes";
	
	public static final String KEY_ArrId="arr_id";
	public static final String KEY_ArrOdo="arr_odo";
	public static final String KEY_ArrLocation="arr_location";
	public static final String KEY_ArrNotes="arr_notes";
	
	public static final String KEY_ReminderId="reminder_id";
	public static final String KEY_ReminderName="reminder_name";
	public static final String KEY_ReminderDatetime="reminder_datetime";
	public static final String KEY_ReminderOdo="reminder_odo";
	public static final String KEY_ReminderNotes="reminder_notes";
	
	
	public static final String KEY_LicenceID="licence_id";
	public static final String KEY_Name="name";
	public static final String KEY_Father_s_w_d_name="father_s_w_d_name";
	public static final String KEY_Date_of_birth="date_of_birth";
	public static final String KEY_Blood_group="blood_group";
	public static final String KEY_Addrerss="addrerss";
	public static final String KEY_Licence_number="licence_number";
	public static final String KEY_Licence_issue_date="licence_issue_date";
	public static final String KEY_Phone_number="phone_number";
	public static final String KEY_Catogory="catogory";
	public static final String KEY_Licence_valid_from="licence_valid_from";
	public static final String KEY_Licence_valid_upto="licence_valid_upto";
	public static final String KEY_Class_vehicle="class_vehicle";
	public static final String KEY_BadgeNumber="badge_number";
	public static final String KEY_Licence_effect_from_date="licence_effect_from_date";

	public static final String KEY_Roadtax_id="roadtax_id";
	public static final String KEY_Bike_Name="bike_name";
	public static final String KEY_Serial_number="serial_number";
	public static final String KEY_Date_of_roadtaxpay="date_of_roadtaxpay";
	public static final String KEY_Office_of_taxpay="roadtax_paid_office";
	public static final String KEY_Amountpayed="amountpayed";
	public static final String KEY_Period_from="period_from";
	public static final String KEY_Period_to="period_to";
	
	public static final String KEY_Insured_bike_name="insured_bike_name";
	public static final String KEY_Insurance_id="insurance_id";
	public static final String KEY_Insurance_company_name_address="insurance_company_name_address";
	public static final String KEY_Certificate_policyno="certificate_policyno";
	public static final String KEY_Policy_issue_office="policy_issue_office";
	public static final String KEY_Policy_issue_date="policy_issue_date";
	public static final String KEY_Policy_from_date="policy_from_date";
	public static final String KEY_Policy_upto_date="policy_upto_date";
	public static final String KEY_Insured_name="insured_name";
	public static final String KEY_Insured_address="insured_address";
	public static final String KEY_Total_value="total_value";
	public static final String KEY_Total_premium="total_premium";
	
	public static final String KEY_Settings="settings_id";
	public static final String KEY_Fuel_Unit="fuel_unit";
	public static final String KEY_Distance_Unit="distance_unit";
	

	
	public static final String ADD_VEHICLE= "CREATE TABLE IF NOT EXISTS " +TABLENAME_1+ "("+
											"bike_id INTEGER PRIMARY KEY AUTOINCREMENT, "+"bike_name VARCHAR, "+"bike_regno VARCHAR UNIQUE, "+
											"bike_manuf INTEGER, "+"type INTEGER, "+"bike_color VARCHAR, "+"bike_model INTEGER, "+
											"bike_yearofmanu INTEGER, "+"bike_regdate DATETIME, "+"bike_chassis VARCHAR UNIQUE, "+
											"bike_engineno VARCHAR UNIQUE, "+"bike_fueltype INTEGER, "+"bike_cc FLOAT, "+"bike_fc DATETIME);";
	

	public static final String PURCHASE_DETAILS= "CREATE TABLE " +TABLENAME_2+ "("+
												 "purchase_id INTEGER primary key, "+"bike_name VARCHAR, "+"purchase_date DATETIME, "+
												 "purchase_price FLOAT, "+" purchase_notes VARCHAR)";
									
	public static final String SALE_DETAILS= "CREATE TABLE " +TABLENAME_3+ "("+
											 " sale_id INTEGER primary key, "+"bike_name VARCHAR, "+"sale_date DATETIME, "+
											 " sale_price FLOAT, "+" sale_notes VARCHAR)";

	public static final String FILL_UP= "CREATE TABLE " +TABLENAME_4+ "("+
										"fillup_id INTEGER primary key, "+"fillup_type INTEGER, "+ "fillup_date DATETIME, "+" fillup_vol FLOAT, "+"fillup_ppl FLOAT, "+
										"fillup_odo FLOAT, "+"fillup_totalcost FLOAT, "+"fillup_vehname VARCHAR NOT NULL, "+
										"fillup_fuelcat INTEGER NOT NULL, "+"fillup_fueltype INTEGER NOT NULL, "+
										"fillup_fuelbrand INTEGER NOT NULL, "+"fillup_fuelstation VARCHAR, "+
										"fillup_paymentType INTEGER, "+"fillup_fueladditive VARCHAR, "+"fillup_notes VARCHAR)";
	
	public static final String EXPENSE="CREATE TABLE "+TABLENAME_5+ "("+
										"exp_id INTEGER primary key, "+"exp_date VARCHAR, "+"exp_odo FLOAT, "+
										"exp_type INTEGER NOT NULL, "+"exp_totalcost FLOAT NOT NULL, "+
										"exp_vehiclename VARCHAR NOT NULL, "+"exp_location VARCHAR, "+
										"exp_payment INTGER, "+"exp_notes VARCHAR)";
	
	public static final String DEPARTURE="CREATE TABLE "+TABLENAME_6+ "("+
										 "dep_id INTEGER primary key, "+"dep_type INTEGER, "+"dep_purpose VARCHAR, "+"dep_client VARCHAR, "+
										 "dep_vehicle INTEGER NOT NULL, "+"dep_datetime DATETIME, "+"dep_odo FLOAT, "+
										 "dep_location VARCHAR, "+"dep_notes VARCHAR)";
	
	public static final String ARRIVAL="CREATE TABLE "+TABLENAME_7+"("+
										"arr_id INTEGER primary key, "+"arr_odo FLOAT, "+"arr_location VARCHAR, "+"arr_notes VARCHAR)";
										
	public static final String REMINDER="CREATE TABLE "+TABLENAME_8+"("+
										"reminder_id INTEGER primary key, "+"reminder_name VARCHAR, "+"reminder_datetime DATETIME, "+
										"reminder_odo FLOAT, "+"reminder_notes VARCHAR)";
	
	
	public static final String LICENCE="CREATE TABLE "+TABLENAME_9+"("+
											"licence_id INTEGER PRIMARY KEY AUTOINCREMENT, "+"name VARCHAR,"+"father_s_w_d_name VARCHAR,"+
											"date_of_birth INTEGER, "+"catogory VARCHAR, "+"blood_group VARCHAR, "+"addrerss VARCHAR, "+"phone_number VARCHAR, "+"licence_number VARCHAR, "+
											"licence_issue_date DATETIME, "+"licence_valid_from DATETIME, "+"class_vehicle VARCHAR, "+"licence_valid_upto DATETIME, "+"badge_number VARCHAR, "+"licence_effect_from_date DATETIME);";
	
	public static final String ROADTAX="CREATE TABLE "+TABLENAME_10+"("+"roadtax_id INTEGER PRIMARY KEY AUTOINCREMENT, "+"bike_name VARCHAR,"+"serial_number VARCHAR,"+"roadtax_paid_office VARCHAR,"+
											"date_of_roadtaxpay DATETIME, "+"amountpayed INTEGER, "+"period_from DATETIME, "+"period_to DATETIME);";
	
	public static final String INSURANCE="CREATE TABLE "+TABLENAME_11+"("+"inurance_id INTEGER PRIMARY KEY AUTOINCREMENT, "+"insured_bike_name VARCHAR, "+
	                                         "insurance_company_name_address VARCHAR,"+"certificate_policyno INTEGER, "+"policy_issue_office VARCHAR, "+"policy_issue_date DATETIME, "+
			                                 "policy_from_date DATETIME, "+"policy_upto_date DATETIME, "+"insured_name VARCHAR, "+
											"insured_address VARCHAR, "+"total_value INTEGER, "+
											"total_premium INTEGER);";
	
	public static final String SETTINGS="CREATE TABLE "+TABLENAME_12+"("+
											"settings_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "+
											"fuel_unit VARCHAR not null, "+"distance_unit VARCHAR not null)";
	
	
	
	
	public static Context ourcontext;
	private static SQLiteDatabase mydatabase;
	private static BikeTrackermydatabase biketrackermydatabase;
	
	
	public DataHelper(Context context)
	{
		ourcontext=context;
		biketrackermydatabase=new BikeTrackermydatabase(context);
	}
	
	public DataHelper open() throws SQLException{
		// TODO Auto-generated method stub
		
		mydatabase=biketrackermydatabase.getWritableDatabase();	
		return this;
		
	}
	
	public void close() {
		// TODO Auto-generated method stub
		biketrackermydatabase.close();		
	}
	
	private static class BikeTrackermydatabase extends SQLiteOpenHelper
	{

		public BikeTrackermydatabase(Context context) {
			
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
			// TODO Auto-generated constructor stub
		}
		
		@Override
		public void onCreate(SQLiteDatabase mydatabase) {
			// TODO Auto-generated method stub
		
			mydatabase.execSQL(ADD_VEHICLE);
			mydatabase.execSQL(PURCHASE_DETAILS);
			mydatabase.execSQL(SALE_DETAILS);
			mydatabase.execSQL(FILL_UP);
			mydatabase.execSQL(EXPENSE); 
			mydatabase.execSQL(DEPARTURE);
			mydatabase.execSQL(ARRIVAL);
			mydatabase.execSQL(REMINDER);
			mydatabase.execSQL(LICENCE);
			mydatabase.execSQL(ROADTAX);
			mydatabase.execSQL(INSURANCE);
			mydatabase.execSQL(SETTINGS);
			
			
		}

		@Override
		public void onUpgrade(SQLiteDatabase mydatabase, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			Log.w("Example", "Upgrading database, this will drop tables and recreate");
			
			mydatabase.execSQL("DROP TABLE IF EXISTS "+ TABLENAME_1);
			mydatabase.execSQL("DROP TABLE IF EXISTS "+ TABLENAME_2);
			mydatabase.execSQL("DROP TABLE IF EXISTS "+ TABLENAME_3);
			mydatabase.execSQL("DROP TABLE IF EXISTS "+ TABLENAME_4);
			mydatabase.execSQL("DROP TABLE IF EXISTS "+ TABLENAME_5);
			mydatabase.execSQL("DROP TABLE IF EXISTS "+ TABLENAME_6);
			mydatabase.execSQL("DROP TABLE IF EXISTS "+ TABLENAME_7);
			mydatabase.execSQL("DROP TABLE IF EXISTS "+ TABLENAME_8);
			mydatabase.execSQL("DROP TABLE IF EXISTS "+ TABLENAME_9);
			mydatabase.execSQL("DROP TABLE IF EXISTS "+ TABLENAME_10);
			mydatabase.execSQL("DROP TABLE IF EXISTS "+ TABLENAME_11);
			mydatabase.execSQL("DROP TABLE IF EXISTS "+ TABLENAME_12);
			
			onCreate(mydatabase);	
	}
	}
	public long settings(String fuel_unit,String distance_unit)
	{
		
		ContentValues cv= new ContentValues();
	    cv.put(KEY_Fuel_Unit, fuel_unit);
		cv.put(KEY_Distance_Unit, distance_unit);
		return mydatabase.insert(TABLENAME_12, null, cv);
	}
	public ArrayList<String> getsettings()
	{
      ArrayList<String> alsettings= new ArrayList<String>();
		
		
		Cursor c= mydatabase.query(TABLENAME_12,new String[] {KEY_Fuel_Unit,KEY_Distance_Unit}, null, null, null, null, null);
		
		 int ifuel_unit=c.getColumnIndex(KEY_Fuel_Unit);
		 int idistance_unit=c.getColumnIndex(KEY_Distance_Unit);		
		 if (c.moveToFirst())
	        {
	            do {      
	            	alsettings.add(c.getString(ifuel_unit));
	            	alsettings.add(c.getString(idistance_unit));
	    				                
	            } while (c.moveToNext());
	           
	        }
	        c.close();
		
	 	return alsettings;	
	}
	
	/*ADD NEW BIKE INFORMATION PAGE OF BIKE TRACKER APPLICATION*/
	
	public long addvehicle(String name_et,String a_regno,String a_manu, String a_vehicletype, String a_bcolor,String a_bmodel,
							String a_yom, String a_regdate, String a_chassis, String a_engineno, String a_fueltype,
							String a_cc, String a_fcupto)throws SQLiteConstraintException
	{
			
		ContentValues cv= new ContentValues();
		try {
		cv.put(KEY_BikeName, name_et);
		cv.put(KEY_BikeRegNo, a_regno);
		cv.put(KEY_BikeManuf, a_manu);
		cv.put(KEY_BikeType,a_vehicletype);
		cv.put(KEY_BikeColor, a_bcolor);
		cv.put(KEY_BikeModel, a_bmodel);
		cv.put(KEY_BikeYOM, a_yom);
		cv.put(KEY_BikeRegDate, a_regdate);
		cv.put(KEY_BikeChassisNo,a_chassis);
		cv.put(KEY_BikeEngineNo,a_engineno);
		cv.put(KEY_BikeFuelType, a_fueltype);
		cv.put(KEY_BikeCC,a_cc);
		cv.put(KEY_BikeFCupto,a_fcupto);
		
		
		} catch (SQLiteConstraintException e) {
			// TODO: handle exception
			
		}
		return mydatabase.insert(TABLENAME_1, null, cv);
	}
	
	
	/*ADD PURCHASE DETAILS PAGE OF BIKE TRACKER APPLICATION*/
	
	public long purchase_details(String bikename,String purchase_date, String purchase_price, String purchase_notes)
	{
		
		ContentValues cv= new ContentValues();
	    cv.put(KEY_BikeName, bikename);
		cv.put(KEY_PurchaseDate, purchase_date);
		cv.put(KEY_PurchasePrice, purchase_price);
		cv.put(KEY_PurchaseNotes,purchase_notes);
		
		return mydatabase.insert(TABLENAME_2,null,cv);
	}
	
	
	/*ADD SALE DETAILS PAGE OF BIKE TRACKER APPLICATION*/
	
	public long sale_details(String bikename,String sale_date, String sale_price, String sale_notes)
	{
		
		ContentValues cv= new ContentValues();
		cv.put(KEY_BikeName, bikename);
		cv.put(KEY_SaleDate, sale_date);
		cv.put(KEY_SalePrice, sale_price);
		cv.put(KEY_SaleNotes, sale_notes);
		
		return mydatabase.insert(TABLENAME_3, null, cv);
	}

	/*FILL UP DETAILS PAGE OF BIKE TRACKER APPLICATION*/
	
	public void fill_up(String fillup_type_spinner, String date_time_et, String fuel_vol_et, String price_lit_et,
						String odo_read_et, String total_cost_et, String vehicle_name_spinner, String fuelcat_spinner,
						String fueltype_spinner, String fuelbrand_spinner, String fuel_station_et, String paymenttype_spinner,
						String fuel_additive_et, String fillup_notes)
	{
		ContentValues cv= new ContentValues();
		
		cv.put(KEY_FillupType, fillup_type_spinner);
		cv.put(KEY_FillupDate, date_time_et);
		cv.put(KEY_FillupVolume,fuel_vol_et);
		cv.put(KEY_FillupPPL, price_lit_et);
		cv.put(KEY_FillupOdo, odo_read_et);
		cv.put(KEY_FillupTotalCost, total_cost_et);
		cv.put(KEY_FillupVehName, vehicle_name_spinner);
		cv.put(KEY_FillupFuelcat, fuelcat_spinner);
		cv.put(KEY_FillupFueltype, fueltype_spinner);
		cv.put(KEY_FillupFuelbrand, fuelbrand_spinner);
		cv.put(KEY_FillupFuelstation, fuel_station_et);
		cv.put(KEY_FillupPaymentType, paymenttype_spinner);
		cv.put(KEY_FillupFueladditive, fuel_additive_et);
		cv.put(KEY_FillupNotes, fillup_notes);
		
		mydatabase.insert(TABLENAME_4, null, cv);
	}
	public static ArrayList<String> getOdometerFromFillUp() {
		
		ArrayList<String> alsettings= new ArrayList<String>();
		
		Cursor c= mydatabase.query(TABLENAME_4, new String[] {KEY_FillupOdo}, null, null, null, null, KEY_FillupOdo+" DESC Limit 2");
		 int ifuel_odo=c.getColumnIndex(KEY_FillupOdo);
			
		 if (c.moveToFirst())
	        {
	            do {      
	            	alsettings.add(c.getString(ifuel_odo));
	            
	    				                
	            } while (c.moveToNext());
	           
	        }
	        c.close();
		
	 	return alsettings;
	
	}
	public static ArrayList<Integer> getpriceFillUp() {
			
		ArrayList<Integer> alsettings= new ArrayList<Integer>();
		
		Cursor c= mydatabase.query(TABLENAME_4, new String[] {KEY_FillupTotalCost}, null, null, null, null, KEY_FillupTotalCost);
		 int ifuel_odo=c.getColumnIndex(KEY_FillupTotalCost);
			
		 if (c.moveToFirst())
	        {
	            do {      
	            	alsettings.add(Integer.parseInt(c.getString(ifuel_odo)));
	            	System.out.println(alsettings);
	            
	    				                
	            } while (c.moveToNext());
	           
	        }
	        c.close();
		
	 	return alsettings;
			//return FILL_UP;
		}
	public static ArrayList<String> getLiterFillUp() {
		
		ArrayList<String> alsettings= new ArrayList<String>();
		
		Cursor c= mydatabase.query(TABLENAME_4, new String[] {KEY_FillupVolume}, null, null, null, null, KEY_FillupVolume+" Limit 1");
		 int ifuel_odo=c.getColumnIndex(KEY_FillupVolume);
			
		 if (c.moveToFirst())
	        {
	            do {      
	            	alsettings.add(c.getString(ifuel_odo));
	            	System.out.println(alsettings);
	            
	    				                
	            } while (c.moveToNext());
	           
	        }
	        c.close();
		
	 	return alsettings;
			//return FILL_UP;
		}
public static ArrayList<Integer> getLiterAllFillUp() {
		
		ArrayList<Integer> alsettings= new ArrayList<Integer>();
		
		Cursor c= mydatabase.query(TABLENAME_4, new String[] {KEY_FillupVolume}, null, null, null, null, KEY_FillupVolume);
		 int ifuel_odo=c.getColumnIndex(KEY_FillupVolume);
			
		 if (c.moveToFirst())
	        {
	            do {      
	            	alsettings.add(c.getInt(ifuel_odo));
	            	System.out.println(alsettings);
	            
	    				                
	            } while (c.moveToNext());
	           
	        }
	        c.close();
		
	 	return alsettings;
			//return FILL_UP;
		}
public static ArrayList<Integer> getLiterAllFillUpINDouble() {
	
	ArrayList<Integer> alsettings= new ArrayList<Integer>();
	
	Cursor c= mydatabase.query(TABLENAME_4, new String[] {KEY_FillupVolume}, null, null, null, null, KEY_FillupVolume);
	 int ifuel_odo=c.getColumnIndex(KEY_FillupVolume);
		int i=0;
	 if (c.moveToFirst())
        {
            do {      
            	alsettings.add((c.getInt(ifuel_odo)));
            	
            
    				                
            } while (c.moveToNext());
           
        }
        c.close();
	
 	return alsettings;
		//return FILL_UP;
	}
public static ArrayList<Double> getpriceFillUpDouble() {
	
	ArrayList<Double> alsettings= new ArrayList<Double>();
	
	Cursor c= mydatabase.query(TABLENAME_4, new String[] {KEY_FillupTotalCost}, null, null, null, null, KEY_FillupTotalCost);
	 int ifuel_odo=c.getColumnIndex(KEY_FillupTotalCost);
		
	 if (c.moveToFirst())
        {
            do {      
            	alsettings.add((double)Integer.parseInt(c.getString(ifuel_odo)));
            	System.out.println(alsettings);
            
    				                
            } while (c.moveToNext());
           
        }
        c.close();
	
 	return alsettings;
		//return FILL_UP;
	}
public static ArrayList<Integer> getOdometerFromFillUpDouble() {
	
	ArrayList<Integer> alsettings= new ArrayList<Integer>();
	
	Cursor c= mydatabase.query(TABLENAME_4, new String[] {KEY_FillupOdo}, null, null, null, null, KEY_FillupOdo);
	 int ifuel_odo=c.getColumnIndex(KEY_FillupOdo);
		
	 if (c.moveToFirst())
        {
            do {      
            	alsettings.add(Integer.parseInt(c.getString(ifuel_odo)));
            
    				                
            } while (c.moveToNext());
           
        }
        c.close();
	
 	return alsettings;

}
	
	
	/*EXPENSES PAGE OF THE BIKE TRACKER APPLICATION*/
	
	public long exp(String datetime_exp, String odo_exp, String expenses_spinner,
					String totalcost_exp, String vehiclename_spinner, String location_exp,
					String paymenttype_spinner, String expenses_notes_et)
	{
	
		ContentValues cv= new ContentValues();
		
		cv.put(KEY_ExpDate, datetime_exp);
		cv.put(KEY_ExpOdo, odo_exp);
		cv.put(KEY_ExpType, expenses_spinner);
		cv.put(KEY_ExpTotalCost, totalcost_exp);
		cv.put(KEY_ExpVehiclename, vehiclename_spinner);
		cv.put(KEY_ExpLocation, location_exp);
		cv.put(KEY_ExpPayment, paymenttype_spinner);
		cv.put(KEY_ExpNotes, expenses_notes_et);
		
		return mydatabase.insert(TABLENAME_5, null, cv);
	}
	
	//ADD DEPARTURE DETAILS OF THE BIKE TRACKER APPLICATION
	
	public long departure(String dep_type,String purpose_et, String client_et, String dep_vehicle, String dep_dt_et, String dep_odo_et,
						  String dep_loc_et, String dep_notes_et)
	{
		
		ContentValues cv= new ContentValues();
		cv.put(KEY_DepType,dep_type);
		cv.put(KEY_DepPurpose,purpose_et);
		cv.put(KEY_DepClient, client_et);
		cv.put(KEY_DepVehicle, dep_vehicle);
		cv.put(KEY_DepDatetime, dep_dt_et);
		cv.put(KEY_DepOdo,dep_odo_et);
		cv.put(KEY_DepLocation, dep_loc_et);
		cv.put(KEY_DepNotes, dep_notes_et);
		
		return mydatabase.insert(TABLENAME_6, null, cv);
		
	}
	
	public long arrival(String arr_odo_et, String arr_loc_et, String arr_notes_et)
	{
		
		ContentValues cv= new ContentValues();
		
		cv.put(KEY_ArrOdo,arr_odo_et);
		cv.put(KEY_ArrLocation, arr_loc_et);
		cv.put(KEY_ArrNotes, arr_notes_et);
		
		return mydatabase.insert(TABLENAME_7, null, cv);
	}
	
	public void reminder(String rem_name, String rem_date, String rem_odo, String rem_notes)
	{
	
		ContentValues cv= new ContentValues();
		
		cv.put(KEY_ReminderName, rem_name);
		cv.put(KEY_ReminderDatetime, rem_date);
		cv.put(KEY_ReminderOdo, rem_odo);
		cv.put(KEY_ReminderNotes,rem_notes);
		
		mydatabase.insert(TABLENAME_8, null, cv);
	}
	
	public ArrayList<String> getBikeName() throws SQLException
	{
		System.out.println("Getting user details");
		ArrayList<String> al=new ArrayList<String>();
		
		 	Cursor c=mydatabase.query(TABLENAME_1, new String[] {KEY_BikeName}, null, null, null, null, null );
		 	 int iname=c.getColumnIndex(KEY_BikeName);
		 	for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext())
			{
					al.add(c.getString(iname));
					//System.out.println(al+"inside equal check  Ater....66666666666......................");
					
			}
				
		 	c.close();
		 	return al;
	}
	
	public  ArrayList<String> getBikeDetails(String Pbikename){
		
	
		ArrayList<String> albikedetails= new ArrayList<String>();
		
		
		Cursor c= mydatabase.query(TABLENAME_1,new String[] {KEY_BikeName,KEY_BikeRegNo,KEY_BikeManuf,
				KEY_BikeType,KEY_BikeColor,KEY_BikeModel,KEY_BikeYOM,KEY_BikeRegDate,KEY_BikeChassisNo,
				KEY_BikeEngineNo,KEY_BikeFuelType,KEY_BikeCC,KEY_BikeFCupto}, KEY_BikeName + "= '" + Pbikename + "'", null, null, null, null);
		
		 int inamid=c.getColumnIndex(KEY_BikeName);
		 int iregnoid=c.getColumnIndex(KEY_BikeRegNo);
		 int imaniid=c.getColumnIndex(KEY_BikeManuf);
		 int ibiketypeid=c.getColumnIndex(KEY_BikeType);
		 int ibikecolorid=c.getColumnIndex(KEY_BikeColor);
		 int ibikemodelid=c.getColumnIndex(KEY_BikeModel);
		 int ibikeyom=c.getColumnIndex(KEY_BikeYOM);
		 int iregdateid=c.getColumnIndex(KEY_BikeRegDate);
		 int ichasinoid=c.getColumnIndex(KEY_BikeChassisNo);
		 int ibikeenginoid=c.getColumnIndex(KEY_BikeEngineNo);
		 int ibikefeltypeid=c.getColumnIndex(KEY_BikeFuelType);
		 int ibikeccid=c.getColumnIndex(KEY_BikeCC);
		 int ibikefcuptoid=c.getColumnIndex(KEY_BikeFCupto);
		
		 if (c.moveToFirst())
	        {
	            do {      
	            	albikedetails.add(c.getString(inamid));
	    			albikedetails.add(c.getString(iregnoid));
	    			albikedetails.add(c.getString(imaniid));
	    			albikedetails.add(c.getString(ibiketypeid));
	    			albikedetails.add(c.getString(ibikecolorid));
	    			albikedetails.add(c.getString(ibikemodelid));
	    			albikedetails.add(c.getString(ibikeyom));
	    			albikedetails.add(c.getString(iregdateid));
	    			albikedetails.add(c.getString(ichasinoid));
	    			albikedetails.add(c.getString(ibikeenginoid));
	    			albikedetails.add(c.getString(ibikefeltypeid));
	    			albikedetails.add(c.getString(ibikeccid));
	    			albikedetails.add(c.getString(ibikefcuptoid));	                
	            } while (c.moveToNext());
	           
	        }
	        c.close();
		
	 	return albikedetails;	
	}

	public Boolean deleteBikeDetails(String bikename) {
		
		// TODO Auto-generated method stub
		
		 int k= mydatabase.delete(TABLENAME_1, KEY_BikeName + "='"+bikename+"'", null);
		if (k==1) {
			return true;
		}
		  
		return false;
		
	}
	public boolean entryloginCheck(String name) 
	{
		boolean check=false;
		System.out.println("Getting user details");
		ArrayList<String> al=new ArrayList<String>();
		
		 	Cursor c=mydatabase.query(TABLENAME_1, new String[] {KEY_BikeName}, null, null, null, null, null );
		 	 int iname=c.getColumnIndex(KEY_BikeName);
		 	
			for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext())
			{
				
				
				if((c.getString(iname).equalsIgnoreCase(name))){
					check=true;
					System.out.println(check+"inside equal check  Ater....66666666666......................");
					
					
				}
				
			}
			c.close();
		 
		return check;
	}

	public Boolean updateBikeDetails(String bikename, String a_regnos, String a_manus, String a_vehicletypes, String a_bcolors, String a_bmodels, String a_yoms, String a_regdates, String a_chassiss, String a_enginenos, String a_fueltypes, String a_ccs, String a_fcuptos) {
		// TODO Auto-generated method stub
		ContentValues cv=new ContentValues();
		//cv.put(KEY_BikeName, name_et);
		cv.put(KEY_BikeRegNo, a_regnos);
		cv.put(KEY_BikeManuf, a_manus);
		cv.put(KEY_BikeType,a_vehicletypes);
		cv.put(KEY_BikeColor, a_bcolors);
		cv.put(KEY_BikeModel, a_bmodels);
		cv.put(KEY_BikeYOM, a_yoms);
		cv.put(KEY_BikeRegDate, a_regdates);
		cv.put(KEY_BikeChassisNo,a_chassiss);
		cv.put(KEY_BikeEngineNo,a_enginenos);
		cv.put(KEY_BikeFuelType, a_fueltypes);
		cv.put(KEY_BikeCC,a_ccs);
		cv.put(KEY_BikeFCupto,a_fcuptos);
		
		int columnnumber=mydatabase.update(TABLENAME_1, cv, KEY_BikeName + "='"+bikename+"'", null);
		
		if(columnnumber!=0){
		System.out.println("number of rows Afected  ="+" "+columnnumber);
		return true;
		}
		return false;
	}

	public ArrayList<String> getBikPurchaseeDetails(String name) {
		// TODO Auto-generated method stub
		
		ArrayList<String> albikedetails= new ArrayList<String>();
		
		Cursor c= mydatabase.query(TABLENAME_2,new String[] {KEY_PurchaseDate,KEY_PurchasePrice,KEY_PurchaseNotes}, KEY_BikeName + "= '" + name + "'", null, null, null, null);
		
		 int iPurchaseDate=c.getColumnIndex(KEY_PurchaseDate);
		 int iPurchasePrice=c.getColumnIndex(KEY_PurchasePrice);
		 int iPurchaseNotes=c.getColumnIndex(KEY_PurchaseNotes);
		
		
		 if (c.moveToFirst())
	        {
	            do {      
	            	albikedetails.add(c.getString(iPurchaseDate));
	    			albikedetails.add(c.getString(iPurchasePrice));
	    			albikedetails.add(c.getString(iPurchaseNotes));
	    			                
	            } while (c.moveToNext());
	           
	        }
	        c.close();
		
	 	return albikedetails;
	}

	public Boolean deleteBikepurchaseDetails(String bikename) {
		// TODO Auto-generated method stub
		 int k= mydatabase.delete(TABLENAME_2, KEY_BikeName + "='"+bikename+"'", null);
			if (k==1) {
				return true;
			}
			  
			return false;
	}
	
	public Boolean deleteBikeExpencesDetails(String bikename) {
		// TODO Auto-generated method stub
		 int k= mydatabase.delete(TABLENAME_5, KEY_ExpDate + "='"+bikename+"'", null);
			if (k==1) {
				return true;
			}
			  
			return false;
	}

	public Boolean deleteBikeDepartureDetails(String bikename) {
		// TODO Auto-generated method stub
		 int k= mydatabase.delete(TABLENAME_6, KEY_DepVehicle + "='"+bikename+"'", null);
			if (k==1) {
				return true;
			}
			  
			return false;
	}

	public ArrayList<String> getBikdepartureDetails(String name) {
		// TODO Auto-generated method stub
ArrayList<String> albikedetails= new ArrayList<String>();
		
		
			
		Cursor c= mydatabase.query(TABLENAME_6,new String[] {KEY_DepType,KEY_DepPurpose,KEY_DepClient,KEY_DepVehicle,KEY_DepDatetime,KEY_DepOdo,KEY_DepLocation,KEY_DepNotes}, 
				KEY_DepVehicle + "= '" +name+ "'", null, null, null, null);
		
		 int inamid=c.getColumnIndex(KEY_DepType);
		 int iregnoid=c.getColumnIndex(KEY_DepPurpose);
		 int imaniid=c.getColumnIndex(KEY_DepClient);
		 int ibiketypeid=c.getColumnIndex(KEY_DepVehicle);
		 int ibikecolorid=c.getColumnIndex(KEY_DepDatetime);
		 int ibikemodelid=c.getColumnIndex(KEY_DepOdo);
		 int ibikeyom=c.getColumnIndex(KEY_DepLocation);
		 int iregdateid=c.getColumnIndex(KEY_DepNotes);
		 
		
		 if (c.moveToFirst())
	        {
	            do {      
	            	albikedetails.add(c.getString(inamid));
	    			albikedetails.add(c.getString(iregnoid));
	    			albikedetails.add(c.getString(imaniid));
	    			albikedetails.add(c.getString(ibiketypeid));
	    			albikedetails.add(c.getString(ibikecolorid));
	    			albikedetails.add(c.getString(ibikemodelid));
	    			albikedetails.add(c.getString(ibikeyom));
	    			albikedetails.add(c.getString(iregdateid));
	    			                
	            } while (c.moveToNext());
	           
	        }
	        c.close();
		
	 	return albikedetails;	
	}

	public ArrayList<String> getBikExpencesDetails(String name) {
		// TODO Auto-generated method stub
       ArrayList<String> albikedetails= new ArrayList<String>();
		
		
			     
		Cursor c= mydatabase.query(TABLENAME_5,new String[] {KEY_ExpDate,KEY_ExpOdo,KEY_ExpType,KEY_ExpTotalCost,KEY_ExpVehiclename,KEY_ExpLocation,KEY_ExpPayment,KEY_ExpNotes},
				KEY_ExpDate + "= '" + name + "'", null, null, null, null);
		
		 int inamid=c.getColumnIndex(KEY_ExpDate);
		 int iregnoid=c.getColumnIndex(KEY_ExpOdo);
		 int imaniid=c.getColumnIndex(KEY_ExpType);
		 int ibiketypeid=c.getColumnIndex(KEY_ExpTotalCost);
		 int ibikecolorid=c.getColumnIndex(KEY_ExpVehiclename);
		 int ibikemodelid=c.getColumnIndex(KEY_ExpLocation);
		 int ibikeyom=c.getColumnIndex(KEY_ExpPayment);
		 int iregdateid=c.getColumnIndex(KEY_ExpNotes);
		 if (c.moveToFirst())
	        {
	            do {      
	            	albikedetails.add(c.getString(inamid));
	    			albikedetails.add(c.getString(iregnoid));
	    			albikedetails.add(c.getString(imaniid));
	    			albikedetails.add(c.getString(ibiketypeid));
	    			albikedetails.add(c.getString(ibikecolorid));
	    			albikedetails.add(c.getString(ibikemodelid));
	    			albikedetails.add(c.getString(ibikeyom));
	    			albikedetails.add(c.getString(iregdateid));
	    			                
	            } while (c.moveToNext());
	           
	        }
	        c.close();
	        System.out.println(albikedetails);
		
	 	return albikedetails;	
	}

	public ArrayList<String> getExpencesDate(String bikename) {
		// TODO Auto-generated method stub
		System.out.println("Getting expences dates details");
		ArrayList<String> al=new ArrayList<String>();
		
		 	Cursor c=mydatabase.query(TABLENAME_5, new String[] {KEY_ExpDate}, KEY_ExpVehiclename + "= '" + bikename + "'", null, null, null, null );
		 	 int iname=c.getColumnIndex(KEY_ExpDate);
		 	for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext())
			{
					al.add(c.getString(iname));
					//System.out.println(al+"inside equal check  Ater....66666666666......................");
					
			}
				
		 	c.close();
		 	return al;

	}

	public boolean updatesettings(String fuelunit_spinner_s,String distanceunit_spinner_s, int row_id) {
		// TODO Auto-generated method stub
		ContentValues cv=new ContentValues();
		//cv.put(KEY_BikeName, name_et);
		
		 cv.put(KEY_Fuel_Unit, fuelunit_spinner_s);
		 cv.put(KEY_Distance_Unit, distanceunit_spinner_s);
		
		int columnnumber=mydatabase.update(TABLENAME_12, cv, KEY_Settings + "='"+row_id+"'", null);
		
		if(columnnumber!=0){
		System.out.println("number of rows Afected  ="+" "+columnnumber);
		return true;
		}
		return false;
		
	}

	public long addlicencedetails(String owner_name_et_s,
			String father_name_s_w_d_et_s, String date_of_birth_et_s,
			String catogory_s, String blood_group_et_s, String addres_et_s,
			String phonenumber_et_s, String licence_number_et_s,
			String licence_issue_date_et_s, String valide_from_date_s,
			String valid_uptodate_et_s, String calss_of_vehcle_spinner_s,
			String registrationUpto_et_s, String licence_with_effect_date_et_s) {
		ContentValues cv= new ContentValues();
		// TODO Auto-generated method stub
		cv.put(KEY_Name, owner_name_et_s);
		cv.put(KEY_Father_s_w_d_name, father_name_s_w_d_et_s);
		cv.put(KEY_Date_of_birth, date_of_birth_et_s);
		cv.put(KEY_Catogory,catogory_s);
		cv.put(KEY_Blood_group, blood_group_et_s);
		cv.put(KEY_Addrerss, addres_et_s);
		cv.put(KEY_Phone_number, phonenumber_et_s);
		cv.put(KEY_Licence_number, licence_number_et_s);
		cv.put(KEY_Licence_issue_date,licence_issue_date_et_s);
		cv.put(KEY_Licence_valid_from,valide_from_date_s);
		cv.put(KEY_Licence_valid_upto,valid_uptodate_et_s);
		cv.put(KEY_Class_vehicle, calss_of_vehcle_spinner_s);
		cv.put(KEY_BadgeNumber,registrationUpto_et_s);
		cv.put(KEY_Licence_effect_from_date,licence_with_effect_date_et_s);
		
		return mydatabase.insert(TABLENAME_9, null, cv);
	}

	public long addRoadTaxDetails(String bike_name_et_s,
			String serialnumber_et_s, String date_tax_pay_et_s,
			String office_of_tax_payed_et_s, String amount_payed_as_tax_et_s,
			String period_from_et_s, String period_up_to_et_s) {
		// TODO Auto-generated method stub
		ContentValues cv= new ContentValues();
		// TODO Auto-generated method stub
		cv.put(KEY_Bike_Name, bike_name_et_s);
		cv.put(KEY_Serial_number, serialnumber_et_s);
		cv.put(KEY_Date_of_roadtaxpay, date_tax_pay_et_s);
		cv.put(KEY_Office_of_taxpay, office_of_tax_payed_et_s);
		cv.put(KEY_Amountpayed,amount_payed_as_tax_et_s);
		cv.put(KEY_Period_from, period_from_et_s);
		cv.put(KEY_Period_to, period_up_to_et_s);
		
		
		return mydatabase.insert(TABLENAME_10, null, cv);
	}

	public long addInsuranceDetails(String select_bikename_et_s,
			String incompany_name_address_et_s, String policy_certificate_et_s,
			String policy_issued_Office_et_s, String policy_issue_date_et_s,
			String policy_from_date_et_s, String policy_upto_date_et_s,
			String insured_name_et_s, String insured_addressr_et_s,
			String total_insured_value,String total_premimun_et_s) {
		// TODO Auto-generated method stub
		ContentValues cv= new ContentValues();
		
		cv.put(KEY_Insured_bike_name, select_bikename_et_s);
		cv.put(KEY_Insurance_company_name_address, incompany_name_address_et_s);
		cv.put(KEY_Certificate_policyno,policy_certificate_et_s);
		cv.put(KEY_Policy_issue_office, policy_issued_Office_et_s);	
		cv.put(KEY_Policy_issue_date,policy_issue_date_et_s);
		cv.put(KEY_Policy_from_date, policy_from_date_et_s);
		cv.put(KEY_Policy_upto_date, policy_upto_date_et_s);
		cv.put(KEY_Insured_name, insured_name_et_s);
		cv.put(KEY_Insured_address, insured_addressr_et_s);
		cv.put(KEY_Total_value,total_insured_value);
		cv.put(KEY_Total_premium,total_premimun_et_s);
		
		return mydatabase.insert(TABLENAME_11, null, cv);
	}

	public ArrayList<String> getlicencedetails() {
		// TODO Auto-generated method stub

		ArrayList<String> albikedetails= new ArrayList<String>();
		
	
		Cursor c= mydatabase.query(TABLENAME_9,new String[] {KEY_Name,KEY_Father_s_w_d_name,KEY_Date_of_birth,KEY_Catogory,KEY_Blood_group,KEY_Addrerss
				,KEY_Phone_number,KEY_Licence_number,KEY_Licence_issue_date,KEY_Licence_valid_from,KEY_Licence_valid_upto,KEY_Class_vehicle,KEY_BadgeNumber,KEY_Licence_effect_from_date},null, null, null, null, null);
		
		 int inamid=c.getColumnIndex(KEY_Name);
		 int iregnoid=c.getColumnIndex(KEY_Father_s_w_d_name);
		 int imaniid=c.getColumnIndex(KEY_Date_of_birth);
		 int ibiketypeid=c.getColumnIndex(KEY_Catogory);
		 int ibikecolorid=c.getColumnIndex(KEY_Blood_group);
		 int ibikemodelid=c.getColumnIndex(KEY_Addrerss);
		 int ibikeyom=c.getColumnIndex(KEY_Phone_number);
		 int iregdateid=c.getColumnIndex(KEY_Licence_number);
		 int ichasinoid=c.getColumnIndex(KEY_Licence_issue_date);
		 int ibikeenginoid=c.getColumnIndex(KEY_Licence_valid_from);
		 int ibikefeltypeid=c.getColumnIndex(KEY_Licence_valid_upto);
		 int ibikeccid=c.getColumnIndex(KEY_Class_vehicle);
		 int ibikefcuptoid=c.getColumnIndex(KEY_BadgeNumber);
		 int ibiliceffectfromdate=c.getColumnIndex(KEY_Licence_effect_from_date);
		
		 if (c.moveToFirst())
	        {
	            do {      
	            	albikedetails.add(c.getString(inamid));
	    			albikedetails.add(c.getString(iregnoid));
	    			albikedetails.add(c.getString(imaniid));
	    			albikedetails.add(c.getString(ibiketypeid));
	    			albikedetails.add(c.getString(ibikecolorid));
	    			albikedetails.add(c.getString(ibikemodelid));
	    			albikedetails.add(c.getString(ibikeyom));
	    			albikedetails.add(c.getString(iregdateid));
	    			albikedetails.add(c.getString(ichasinoid));
	    			albikedetails.add(c.getString(ibikeenginoid));
	    			albikedetails.add(c.getString(ibikeccid));
	    			albikedetails.add(c.getString(ibikefeltypeid));
	    			albikedetails.add(c.getString(ibikefcuptoid));
	    			albikedetails.add(c.getString(ibiliceffectfromdate));
	            } while (c.moveToNext());
	           
	        }
	        c.close();
		
	 	return albikedetails;
	}

	public boolean updatelicencedetails(String owner_name_et_s,
			String father_name_s_w_d_et_s, String date_of_birth_et_s,
			String catogory_s, String blood_group_et_s, String addres_et_s,
			String phonenumber_et_s, String licence_number_et_s,
			String licence_issue_date_et_s, String valide_from_date_s,
			String valid_uptodate_et_s, String calss_of_vehcle_spinner_s,
			String registrationUpto_et_s, String licence_with_effect_date_et_s) {
		// TODO Auto-generated method stub
		ContentValues cv= new ContentValues();
		// TODO Auto-generated method stub
		cv.put(KEY_Name, owner_name_et_s);
		cv.put(KEY_Father_s_w_d_name, father_name_s_w_d_et_s);
		cv.put(KEY_Date_of_birth, date_of_birth_et_s);
		cv.put(KEY_Catogory,catogory_s);
		cv.put(KEY_Blood_group, blood_group_et_s);
		cv.put(KEY_Addrerss, addres_et_s);
		cv.put(KEY_Phone_number, phonenumber_et_s);
		cv.put(KEY_Licence_number, licence_number_et_s);
		cv.put(KEY_Licence_issue_date,licence_issue_date_et_s);
		cv.put(KEY_Licence_valid_from,valide_from_date_s);
		cv.put(KEY_Licence_valid_upto,valid_uptodate_et_s);
		cv.put(KEY_Class_vehicle, calss_of_vehcle_spinner_s);
		cv.put(KEY_BadgeNumber,registrationUpto_et_s);
		cv.put(KEY_Licence_effect_from_date,licence_with_effect_date_et_s);
		int columnnumber= mydatabase.update(TABLENAME_9, cv, KEY_Name + "='"+owner_name_et_s+"'", null);
		if(columnnumber!=0){
			System.out.println("number of rows Afected  ="+" "+columnnumber);
			return true;
			}
			return false;
		
	}

	public ArrayList<String> getaRoadaTaxDetails(String bikeselected) {
		// TODO Auto-generated method stub
		ArrayList<String> albikedetails=new ArrayList<String>();
		Cursor c= mydatabase.query(TABLENAME_10,new String[] {
				KEY_Bike_Name,KEY_Serial_number,KEY_Date_of_roadtaxpay,
				KEY_Office_of_taxpay,KEY_Amountpayed,KEY_Period_from,KEY_Period_to},KEY_Bike_Name + "='"+bikeselected+"'", null, null, null, null);
		
		 int inamid=c.getColumnIndex(KEY_Bike_Name);
		 int iregnoid=c.getColumnIndex(KEY_Serial_number);
		 int imaniid=c.getColumnIndex(KEY_Date_of_roadtaxpay);
		 int ibiketypeid=c.getColumnIndex(KEY_Office_of_taxpay);
		 int ibikecolorid=c.getColumnIndex(KEY_Amountpayed);
		 int ibikemodelid=c.getColumnIndex(KEY_Period_from);
		 int ibikeyom=c.getColumnIndex(KEY_Period_to);
		
		 if (c.moveToFirst())
	        {
	            do {      
	            	albikedetails.add(c.getString(inamid));
	    			albikedetails.add(c.getString(iregnoid));
	    			albikedetails.add(c.getString(imaniid));
	    			albikedetails.add(c.getString(ibiketypeid));
	    			albikedetails.add(c.getString(ibikecolorid));
	    			albikedetails.add(c.getString(ibikemodelid));
	    			albikedetails.add(c.getString(ibikeyom));
	    			
	            } while (c.moveToNext());
	           
	        }
	        c.close();
		
	 	return albikedetails;
	}
	

	public boolean UpRoadTaxDetails(String bike_name_et_s,
			String serialnumber_et_s, String date_tax_pay_et_s,
			String office_of_tax_payed_et_s, String amount_payed_as_tax_et_s,
			String period_from_et_s, String period_up_to_et_s) {
		// TODO Auto-generated method stub
		ContentValues cv= new ContentValues();
		// TODO Auto-generated method stub
		cv.put(KEY_Bike_Name, bike_name_et_s);
		cv.put(KEY_Serial_number, serialnumber_et_s);
		cv.put(KEY_Date_of_roadtaxpay, date_tax_pay_et_s);
		cv.put(KEY_Office_of_taxpay, office_of_tax_payed_et_s);
		cv.put(KEY_Amountpayed,amount_payed_as_tax_et_s);
		cv.put(KEY_Period_from, period_from_et_s);
		cv.put(KEY_Period_to, period_up_to_et_s);
		int columnnumber= mydatabase.update(TABLENAME_10, cv, KEY_Bike_Name + "='"+bike_name_et_s+"'", null);
		if(columnnumber!=0){
			System.out.println("number of rows Afected  ="+" "+columnnumber);
			return true;
			}
			return false;
	}
	
	
	public Boolean deletelicenceDetails() {
		// TODO Auto-generated method stub
		 int k= mydatabase.delete(TABLENAME_9, null, null);
			if (k==1) {
				return true;
			}
			  
			return false;
	}
	public Boolean deleteRoadtaxDetails(String bikename) {
		// TODO Auto-generated method stub
		 int k= mydatabase.delete(TABLENAME_10, KEY_Bike_Name + "='"+bikename+"'", null);
			if (k==1) {
				return true;
			}
			  
			return false;
	}
	public Boolean deleteInsuranceDetails(String bikename) {
		// TODO Auto-generated method stub
		 int k= mydatabase.delete(TABLENAME_11, KEY_Bike_Name + "='"+bikename+"'", null);
			if (k==1) {
				return true;
			}
			  
			return false;
	}

	public ArrayList<String> getInsuranceDetails(String bikename) {
		// TODO Auto-generated method stub
		
		ArrayList<String> albikedetails=new ArrayList<String>();
		
	
		Cursor c= mydatabase.query(TABLENAME_11,new String[] {KEY_Insured_bike_name,KEY_Insurance_company_name_address,
				KEY_Certificate_policyno,KEY_Policy_issue_office,KEY_Policy_issue_date,KEY_Policy_from_date,KEY_Policy_upto_date
				,KEY_Insured_name,KEY_Insured_address,KEY_Total_value,KEY_Total_premium},KEY_Insured_bike_name + "='"+bikename+"'", null, null, null, null);
		
		 int ione=c.getColumnIndex(KEY_Insured_bike_name);
		 int itwo=c.getColumnIndex(KEY_Insurance_company_name_address);
		 int ithree=c.getColumnIndex(KEY_Certificate_policyno);
		 int ifour=c.getColumnIndex(KEY_Policy_issue_office);
		 int ifive=c.getColumnIndex(KEY_Policy_issue_date);
		 int isix=c.getColumnIndex(KEY_Policy_from_date);
		 int iseven=c.getColumnIndex(KEY_Policy_upto_date);
		 int ieight=c.getColumnIndex(KEY_Insured_name);
		 int inine=c.getColumnIndex(KEY_Insured_address);
		 int iten=c.getColumnIndex(KEY_Total_value);
		 int ieleven=c.getColumnIndex(KEY_Total_premium);
		
		 if (c.moveToFirst())
	        {
	            do {      
	            	albikedetails.add(c.getString(ione));
	    			albikedetails.add(c.getString(itwo));
	    			albikedetails.add(c.getString(ithree));
	    			albikedetails.add(c.getString(ifour));
	    			albikedetails.add(c.getString(ifive));
	    			albikedetails.add(c.getString(isix));
	    			albikedetails.add(c.getString(iseven));
	    			albikedetails.add(c.getString(ieight));
	    			albikedetails.add(c.getString(inine));
	    			albikedetails.add(c.getString(iten));
	    			albikedetails.add(c.getString(ieleven));
	    			
	    			
	            } while (c.moveToNext());
	           
	        }
	        c.close();
		
	 	return albikedetails;
	}

	public boolean updateInsuranceDetails(String select_bikename_et_s,
			String incompany_name_address_et_s, String policy_certificate_et_s,
			String policy_issued_Office_et_s, String policy_issue_date_et_s,
			String policy_from_date_et_s, String policy_upto_date_et_s,
			String insured_name_et_s, String insured_addressr_et_s,
			String total_premimun_et_s, String total_value_et_s) {
		// TODO Auto-generated method stub
		
		ContentValues cv= new ContentValues();
		cv.put(KEY_Insured_bike_name, select_bikename_et_s);
		cv.put(KEY_Insurance_company_name_address, incompany_name_address_et_s);
		cv.put(KEY_Certificate_policyno,policy_certificate_et_s);
		cv.put(KEY_Policy_issue_office, policy_issued_Office_et_s);	
		cv.put(KEY_Policy_issue_date,policy_issue_date_et_s);
		cv.put(KEY_Policy_from_date, policy_from_date_et_s);
		cv.put(KEY_Policy_upto_date, policy_upto_date_et_s);
		cv.put(KEY_Insured_name, insured_name_et_s);
		cv.put(KEY_Insured_address, insured_addressr_et_s);
		cv.put(KEY_Total_value,total_value_et_s);
		cv.put(KEY_Total_premium,total_premimun_et_s);
		int columnnumber= mydatabase.update(TABLENAME_11, cv, KEY_Insured_bike_name + "='"+select_bikename_et_s+"'", null);
		if(columnnumber!=0){
			System.out.println("number of rows Afected  ="+" "+columnnumber);
			return true;
			}
			return false;
	}

	public ArrayList<String> getabikesofRoadaTaxDetails() {
		// TODO Auto-generated method stub
		System.out.println("Getting user details");
		ArrayList<String> al=new ArrayList<String>();
		
		 	Cursor c=mydatabase.query(TABLENAME_10, new String[] {KEY_Bike_Name}, null, null, null, null, null );
		 	 int iname=c.getColumnIndex(KEY_Bike_Name);
		 	for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext())
			{
					al.add(c.getString(iname));
					//System.out.println(al+"inside equal check  Ater....66666666666......................");
					
			}
				
		 	c.close();
		 	return al;
	}
	public ArrayList<String> getabikesofInsuranceDetails() {
		// TODO Auto-generated method stub
		System.out.println("Getting user details");
		ArrayList<String> al=new ArrayList<String>();
		
		 	Cursor c=mydatabase.query(TABLENAME_11, new String[] {KEY_Insured_bike_name}, null, null, null, null, null );
		 	 int iname=c.getColumnIndex(KEY_Insured_bike_name);
		 	for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext())
			{
					al.add(c.getString(iname));
					//System.out.println(al+"inside equal check  Ater....66666666666......................");
					
			}
				
		 	c.close();
		 	return al;
	}
	
}	

	




	
	

