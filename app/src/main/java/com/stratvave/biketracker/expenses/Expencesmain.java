package com.stratvave.biketracker.expenses;

import com.stratvave.biketracker.main.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class Expencesmain extends Activity implements OnClickListener{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.expencesmain);
		findViewById(R.id.add_expences_button).setOnClickListener(this);
		findViewById(R.id.view_expences_button).setOnClickListener(this);
		findViewById(R.id.update_expences_button).setOnClickListener(this);
		findViewById(R.id.delete_expences_button).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.add_expences_button:
			startActivity(new Intent(Expencesmain.this, Expenses.class));
			break;
		case R.id.view_expences_button:
			startActivity(new Intent(Expencesmain.this, ViewExpences.class));
					
					break;
		case R.id.update_expences_button:
			startActivity(new Intent(Expencesmain.this, UpDateExpences.class));
			
			break;
		case R.id.delete_expences_button:
			startActivity(new Intent(Expencesmain.this, DeleteExpences.class));
			
			break;

		
		}
		
	}

}
