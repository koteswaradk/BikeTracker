package com.stratvave.biketracker.charts;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/*import org.achartengine.chartdemo.demo.chart.AverageTemperatureChart;
import org.achartengine.chartdemo.demo.chart.SalesGrowthChart;
import org.achartengine.chartdemo.demo.chart.SensorValuesChart;*/

import com.stratvave.biketracker.databases.DataHelper;
import com.stratvave.biketracker.main.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Charts extends Activity{
	
	RadioGroup radiogroup;
	double doubleArrayodometer[],doubleArrayup_amount[];
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.charts);
		
		radiogroup= (RadioGroup) findViewById(R.id.chart_rg);
		milageChart();
		  radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			// TODO Auto-generated method stub
			switch (checkedId) {
			case R.id.mileage_chart_rb:
				Toast.makeText(Charts.this, "Milage chart", Toast.LENGTH_LONG).show();
				milageChart();
				break;

			case R.id.fuel_chart_rb:
				Toast.makeText(Charts.this, "Fuel Chart", Toast.LENGTH_LONG).show();
				fuelChart();	
									break;
			case R.id.expense_chart_rb:
				Toast.makeText(Charts.this, "Expences Chart", Toast.LENGTH_LONG).show();
				ExpencesChart();
				break;
			}
			
		}

		
	});
	}
	private void ExpencesChart() {
		// TODO Auto-generated method stub
	
		DataHelper dh = new DataHelper(this);
		dh.open();

		ArrayList<Integer> odometer_list = DataHelper.getOdometerFromFillUpDouble();
		ArrayList<Integer> price_list = DataHelper.getpriceFillUp();
		dh.close();
		double[] doublearrayodometer= convertDoubles(odometer_list);
		
		double[] doublearrayprice= convertDoubles(price_list);
		SalesGrowthChart sg=new SalesGrowthChart();
		Intent i=sg.expencesExecute(Charts.this,doublearrayodometer,doublearrayprice);
		startActivity(i);
	}

	private void fuelChart() {
		// TODO Auto-generated method stub
		DataHelper dh=new DataHelper(Charts.this);
		 dh.open();
			List<Integer> allodometer= DataHelper.getOdometerFromFillUpDouble();
			List<Integer> allfillup_amount=DataHelper.getLiterAllFillUpINDouble();
			 dh.close();
			 
			double[] allfillupvolume= convertDoubles(allfillup_amount);
			
			double[] allodometervalue= convertDoubles(allodometer);

		SalesGrowthChart sg=new SalesGrowthChart();
		Intent i=sg.fuelExecute(Charts.this,allodometervalue,allfillupvolume);
		startActivity(i);
			
			
		
	}

	private void milageChart() {
		// TODO Auto-generated method stub
		DataHelper dh=new DataHelper(Charts.this);
		 dh.open();
			List<Integer> allodometer= DataHelper.getOdometerFromFillUpDouble();
			List<Integer> allfillup_amount=DataHelper.getLiterAllFillUpINDouble();
			 dh.close();
			 Log.i("sample", ""+allodometer+""+allfillup_amount);
			 if ((allodometer.size()==0)&&(allfillup_amount.size()==0)) {
				 Toast.makeText(Charts.this, "you needto add odometer readingsfirst", Toast.LENGTH_LONG).show();
			}else{
			double[] allfillupvolume= convertDoubles(allfillup_amount);
			
			double[] allodometervalue= convertDoubles(allodometer);

		SalesGrowthChart sg=new SalesGrowthChart();
		Intent i=sg.milageExecute(Charts.this,allfillupvolume,allodometervalue);
		startActivity(i);
			}
			 
		
	}
	public static double[] convertDoubles(List<Integer> doubles)
	{
	    double[] ret = new double[doubles.size()];
	    Iterator<Integer> iterator = doubles.iterator();
	    for (int i = 0; i < ret.length; i++)
	    {
	        ret[i] = iterator.next().doubleValue();
	        
	    }
	    return ret;
	   
	}

}
