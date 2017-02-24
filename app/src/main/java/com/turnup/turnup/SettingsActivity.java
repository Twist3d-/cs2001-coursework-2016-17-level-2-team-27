package com.turnup.turnup;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.preference.PreferenceFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Fragment fragment = new SettingsScreen();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        if(savedInstanceState == null)
        {
            fragmentTransaction.add(R.id.relative_layout, fragment, "settings_fragment");
            fragmentTransaction.commit();
        }
        else
        {
            fragment = getFragmentManager().findFragmentByTag("settings_fragment");
        }
    }
    public static class SettingsScreen extends PreferenceFragment
    {
        @Override
        public void onCreate(Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.settings_screen);
        }

    }

//    public boolean editTimetableClick (){
//        Intent editTCIntent = new Intent(this,someedittimetable.class);
//        startActivity(editTCIntent);
//        return true;
//    }
}
