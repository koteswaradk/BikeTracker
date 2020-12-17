package com.stratvave.autoanswer;




import com.stratvave.biketracker.main.R;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceActivity; 

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class AutoAnswerPreferenceActivity extends PreferenceActivity implements OnSharedPreferenceChangeListener { 
	private AutoAnswerNotifier mNotifier;
	@Override
	protected void onCreate(Bundle savedInstanceState) { 
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.preference);
		setTitleColor(Color.WHITE);
		setTitle(R.string.calltitle);
		setTheme(R.style.PreferencesTheme);
		mNotifier = new AutoAnswerNotifier(this); 
		mNotifier.updateNotification();
		SharedPreferences sharedPreferences = getPreferenceManager().getSharedPreferences();
		sharedPreferences.registerOnSharedPreferenceChangeListener(this);
		
		}
	@Override 
	protected void onDestroy() { 
		getPreferenceManager().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
		super.onDestroy();
		}
	@Override 
	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) { 
		if (key.equals("enabled")) { 
			mNotifier.updateNotification();
			}  
		} 
	} 