package com.stratvave.biketracker.settings;

import com.stratvave.biketracker.main.R;

import android.content.Context;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;

public class PreferenceActivitySettings extends PreferenceActivity{
@Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	addPreferencesFromResource(R.xml.preference_screen);
}
	


}
