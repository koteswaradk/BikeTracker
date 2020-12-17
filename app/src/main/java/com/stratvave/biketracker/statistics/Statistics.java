package com.stratvave.biketracker.statistics;

import java.text.DecimalFormat;
import java.util.ArrayList;

import com.stratvave.biketracker.databases.DataHelper;
import com.stratvave.biketracker.main.R;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Statistics extends Activity implements OnItemSelectedListener {

	private DataHelper db;
	ArrayList<String> vename;
	Spinner vehicle_spinner;
	String vehicle_name_s;
	RadioGroup radiogroup;
	Integer sum = 0;
	double efficiency;
	TextView _BestFuelEco, bsetfuel_milage_set, efficiency_set;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.statistics);

		vehicle_spinner = (Spinner) findViewById(R.id.vehicle_spinner);
		_BestFuelEco = (TextView) findViewById(R.id.BestFuelEco);
		bsetfuel_milage_set = (TextView) findViewById(R.id.bsetfuel_milage_set);
		efficiency_set = (TextView) findViewById(R.id.efficiency_set);
		_BestFuelEco = (TextView) findViewById(R.id.bsetfuel_economy_set);
		// radiogroup= (RadioGroup) findViewById(R.id.stat_rg);
		vename = new ArrayList<String>();
		db = new DataHelper(this);
		db.open();
		ArrayList<String> arraylist = db.getBikeName();
		db.close();

		vehicle_spinner.setOnItemSelectedListener(this);
		ArrayAdapter<String> aa = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, arraylist);
		aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		vehicle_spinner.setAdapter(aa);

		bestmilage();

		besteconomy();
		bestefficiency();

	}

	private void bestmilage() {
		// TODO Auto-generated method stub
		DataHelper dh = new DataHelper(this);
		dh.open();

		ArrayList<String> a = DataHelper.getOdometerFromFillUp();
		ArrayList<String> liters = DataHelper.getLiterFillUp();
		dh.close();

		int liters_int = Integer.parseInt(liters.get(0));
		int second = (Integer.parseInt(a.get(0)));
		int first = (Integer.parseInt(a.get(1)));
		int km_per_liter_milage = (second - first) / liters_int;
		System.out.println("second liters filled" + km_per_liter_milage);
		bsetfuel_milage_set.setText(Integer.toString(km_per_liter_milage)
				+ "kmpl");
	}

	private void besteconomy() {
		// TODO Auto-generated method stub
		DataHelper dh = new DataHelper(this);
		dh.open();
		ArrayList<String> a = DataHelper.getOdometerFromFillUp();

		ArrayList<Integer> fillup_amount = DataHelper.getpriceFillUp();
		dh.close();
		/*
		 * System.out.println("second odometer"+a.get(0));
		 * System.out.println("first odometer"+a.get(1));
		 */

		for (Integer i : fillup_amount)
			sum = sum + i;
		// System.out.println("fill up total cost"+sum);

		efficiency = Math
				.round((((double) Integer.parseInt(a.get(0)) / sum) * 100) * 100.0) / 100.0;
		_BestFuelEco.setText(Double.toString(efficiency) + "%");
		System.out.println("economy" + efficiency);

	}

	private void bestefficiency() {
		// TODO Auto-generated method stub

		DataHelper dh = new DataHelper(this);
		dh.open();
		ArrayList<String> a = DataHelper.getOdometerFromFillUp();
		ArrayList<Integer> fillup_amount = DataHelper.getLiterAllFillUp();
		dh.close();
		int sum1 = 0;
		System.out.println("second odometer" + a.get(0));
		for (Integer i : fillup_amount)
			sum1 = sum1 + i;
		System.out.println(a.get(0) + "fill up total cost for efficiency"
				+ sum1);
		double k = Math
				.round((((double) Integer.parseInt(a.get(0)) / sum1)) * 100.0) / 100.0;
		System.out.println(k);
		efficiency_set.setText(k + "%");

	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View arg1, int position,
			long arg3) {
		// TODO Auto-generated method stub
		vehicle_name_s = parent.getItemAtPosition(position).toString();

	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub

	}

}
