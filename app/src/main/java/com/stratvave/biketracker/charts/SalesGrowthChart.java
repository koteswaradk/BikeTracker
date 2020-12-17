/**
 * Copyright (C) 2009, 2010 SC 4ViewSoft SRL
 *  
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.stratvave.biketracker.charts;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.achartengine.ChartFactory;
import org.achartengine.chart.PointStyle;
import org.achartengine.renderer.XYMultipleSeriesRenderer;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint.Align;
import android.widget.Toast;

/**
 * Sales growth demo chart.
 */
public class SalesGrowthChart extends AbstractDemoChart {
	Context c;
  /**
   * Returns the chart name.
   * 
   * @return the chart name
   */
  public String getName() {
    return "Sales growth";
  }

  /**
   * Returns the chart description.
   * 
   * @return the chart description
   */
  public String getDesc() {
    return "The sales growth across several years (time chart)";
  }

  /**
   * Executes the chart demo.
   * 
   * @param context the context
   * @return the built intent
   */
  /*public Intent execute(Context context) {
    String[] titles = new String[] { "Fuel chart of your bike" };
    List<double[]> dates = new ArrayList<double[]>();
    List<double[]> values = new ArrayList<double[]>();
    double[] dateValues = new double[] {};
    dates.add(dateValues);
    

    values.add(new double[] { 4.9, 5.3, 3.2,7.9 });
    int[] colors = new int[] { Color.BLUE };
    PointStyle[] styles = new PointStyle[] { PointStyle.POINT };
    XYMultipleSeriesRenderer renderer = buildRenderer(colors, styles);
    setChartSettings(renderer, "Fuel Chart", "Fuel Used", "Odometer Reading", dateValues[0],
    dateValues[dateValues.length - 1],  0, 30, Color.GRAY, Color.LTGRAY);
    renderer.setYLabels(10);
   
    return ChartFactory.getTimeChartIntent(context, buildDataset(titles, dates, values),
        renderer, "MMM yyyy");
  }*/

public Intent fuelExecute(Charts charts, double[] doubleArrayodometer,
		double[] doubleArrayup_amount) {
	// TODO Auto-generated method stub
	 String[] titles = new String[] { "Fuel Chart Of your Bike" };
	    List<double[]> odometer = new ArrayList<double[]>();
	    List<double[]> values = new ArrayList<double[]>();
	    //double[] dateValues = new double[] {};
	    odometer.add(doubleArrayodometer);
	    

	    values.add( doubleArrayup_amount);
	    int[] colors = new int[] { Color.BLUE };
	    PointStyle[] styles = new PointStyle[] { PointStyle.POINT };
	    XYMultipleSeriesRenderer renderer = buildRenderer(colors, styles);
	    
	    setChartSettings(renderer, "Fuel Chart", "Odometer Reading in km", "Fuel Used in ltr", doubleArrayodometer[0],
	    		
	    		doubleArrayodometer[doubleArrayodometer.length-1], doubleArrayup_amount[0], doubleArrayup_amount[doubleArrayup_amount.length-1], Color.GRAY, Color.LTGRAY);
	    
	    renderer.setYLabels(10);
	    renderer.setXLabels(10);
	    
	    renderer.setXLabelsAlign(Align.RIGHT);
	    renderer.setYLabelsAlign(Align.RIGHT);
	    
	    return ChartFactory.getLineChartIntent(charts, buildDataset(titles, odometer, values),
	            renderer);
}
public Intent milageExecute(Charts charts, double[] doubleArrayodometer,
		double[] doubleArrayup_amount) {
	// TODO Auto-generated method stub
	 String[] titles = new String[] { "Milage Chart Of your Bike" };
	    List<double[]> odometer = new ArrayList<double[]>();
	    List<double[]> values = new ArrayList<double[]>();
	    //double[] dateValues = new double[] {};
	    odometer.add(doubleArrayodometer);
	    values.add( doubleArrayup_amount);
	    int[] colors = new int[] { Color.BLUE };
	    PointStyle[] styles = new PointStyle[] { PointStyle.POINT };
	    XYMultipleSeriesRenderer renderer = buildRenderer(colors, styles);
	   
	    setChartSettings(renderer, "Milage Chart", "fuel used in lts", "Odomete in km", doubleArrayodometer[0],
	    		
	    		doubleArrayodometer[doubleArrayodometer.length-1], doubleArrayup_amount[0], doubleArrayup_amount[doubleArrayup_amount.length-1], Color.GRAY, Color.LTGRAY);
	    
	    renderer.setYLabels(10);
	    renderer.setXLabels(10);
	    
	    renderer.setXLabelsAlign(Align.RIGHT);
	    renderer.setYLabelsAlign(Align.RIGHT);
	    
	    return ChartFactory.getLineChartIntent(charts, buildDataset(titles, odometer, values),
	            renderer);
}
public Intent expencesExecute(Charts charts, double[] doubleArrayodometer,
		double[] doubleArrayup_amount) {
	// TODO Auto-generated method stub
	 String[] titles = new String[] { "Expences Chart Of your Bike" };
	    List<double[]> odometer = new ArrayList<double[]>();
	    List<double[]> values = new ArrayList<double[]>();
	  
	    odometer.add(doubleArrayodometer);
	    

	    values.add( doubleArrayup_amount);
	    int[] colors = new int[] { Color.BLUE };
	    PointStyle[] styles = new PointStyle[] { PointStyle.POINT };
	    XYMultipleSeriesRenderer renderer = buildRenderer(colors, styles);
	    
	    setChartSettings(renderer, "Expences Chart", "Odometer Reading in km", "amount Used in rupee", doubleArrayodometer[0],
	    		
	    		doubleArrayodometer[doubleArrayodometer.length-1], doubleArrayup_amount[0], doubleArrayup_amount[doubleArrayup_amount.length-1], Color.GRAY, Color.LTGRAY);
	    
	    renderer.setYLabels(10);
	    renderer.setXLabels(10);
	    
	    renderer.setXLabelsAlign(Align.RIGHT);
	    renderer.setYLabelsAlign(Align.RIGHT);
	    
	    return ChartFactory.getLineChartIntent(charts, buildDataset(titles, odometer, values),
	            renderer);
}

@Override
public Intent execute(Context context) {
	// TODO Auto-generated method stub
	return null;
}

}
