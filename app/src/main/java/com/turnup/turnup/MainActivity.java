package com.turnup.turnup;

import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NfcAdapter;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // Initializing all the variables Needed

    // Initializing Button
    Button readNfcButton;
    Button timeTableButton;
    Button historyButton;
    Button settingsButton;

    TextView nfcTextView;                  // Initializing TextView
    ListView moduleListView;            // Initializing ListView to display upcoming modules lectures
    ImageView brunelLogoImageView;      // Initializing ImageView for Brunel University Logo

    String nfcTagCode;                  // String to store NFC Tag Code
    NfcAdapter mNfcAdapter;             // Initializing NFC Adapter

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //------------------------------------------------

        readNfcButton = (Button) findViewById(R.id.readNFCButton);  // Textview to read String from NFC, to test if the NFC is Working or not
        timeTableButton = (Button) findViewById(R.id.timeTableButton);
        historyButton = (Button) findViewById(R.id.historyButton);
        settingsButton = (Button) findViewById(R.id.settingsButton);
        nfcTextView = (TextView) findViewById(R.id.nfcTextView);
        //-------------------------------------------------

        moduleListView = (ListView) findViewById(R.id.moduleListView);
        brunelLogoImageView = (ImageView) findViewById(R.id.brunelLogoImageView);
        mNfcAdapter = NfcAdapter.getDefaultAdapter(this);


        //------------------------------------------------
        //Demo Array List to add module to ListView for testing
        ArrayAdapter<String> arrayAdapter;
        ArrayList<String> moduleList = new ArrayList<String>();
        moduleList.add("CS2001\n" + "05-Feb-2017, 01:00 PM - 02:00 PM");
        moduleList.add("CS2002\n" + "05-Feb-2017, 02:00 PM - 03:00 PM");
        moduleList.add("CS2003\n" + "05-Feb-2017, 03:00 PM - 04:00 PM");
        moduleList.add("CS2004\n" + "05-Feb-2017, 04:00 PM - 05:00 PM");
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, moduleList);
        moduleListView.setAdapter(arrayAdapter);

        // Calling testNFC Method to test if NFC is Enabled or Disabled
        testNFC();
    }

    public void clicked(View view){

        Log.i("test",nfcTagCode);               // Printing NFC TAG Code for Verification when button is clicked
    }

    public void settingsButton(View view){
        Intent settingsActivityIntent = new Intent(getApplicationContext(), SettingsActivity.class);
        startActivity(settingsActivityIntent);
    }
    public void historyButton(View view){
        Toast.makeText(getApplicationContext(), "Work in progress for History Screen", Toast.LENGTH_SHORT).show();
    }
    public void timeTableButton(View view){
        Toast.makeText(getApplicationContext(), "Work in progress for TimeTable Screen", Toast.LENGTH_SHORT).show();
        Intent timeTableActivityIntent = new Intent(getApplicationContext(), TimetableActivity.class);
        startActivity(timeTableActivityIntent);
    }


    //---------------------------------------------------------
    // This will test if the NFC is enabled or Disabled
    public void testNFC(){
        if(mNfcAdapter.isEnabled() && mNfcAdapter != null){
            Log.i("test","NFC is Enabled");
        }
        else{
            Log.i("test","NFC is Disabled, Enable NFC from Settings Screen");
            //finish();
        }
    }

    //---------------------------------------------------------
    //This will keep a check if NFC has any data, and NDEF is dicovered to transfer the Data from NFC TAG
    @Override
    public void onResume() {
        super.onResume();
        // Check if there is any any NDEF is discovered to process the TAG
        if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(getIntent().getAction())) {
            processIntent(getIntent());     // If NDEF is discovered, It calls prcoessIntent method to get the NFC TAG Code
        }
    }

    //---------------------------------------------------------
    //This will process the intent(data) from NFC tag to the User Device
    void processIntent(Intent intent) {
        Parcelable[] rawMsgs = intent.getParcelableArrayExtra(
                NfcAdapter.EXTRA_NDEF_MESSAGES);
        NdefMessage msg = (NdefMessage) rawMsgs[0];
        String s = new String(msg.getRecords()[0].getPayload());
        nfcTagCode = s.substring(3);

        nfcTextView.setText(nfcTagCode);                      // Sets the textview as the Text read from NFC TAG
    }
}
