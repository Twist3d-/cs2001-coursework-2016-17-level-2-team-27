package com.turnup.turnup;

import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NfcAdapter;
import android.os.Parcelable;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.BackendlessUser;
import com.backendless.UserService;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.BackendlessDataQuery;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // Initializing all the variables Needed

    String studentID, cardID, email, courseName, level, name, attendedTimeString;
    String chipNo, moduleNo, room;
    Date start,end, attendedTime;

    // Initializing Button
    Button readNfcButton;
    Button settingsButton;

    // Initializing Checkboxes
    CheckBox checkBoxCs2001;
    CheckBox checkBoxCs2002;
    CheckBox checkBoxCs2003;
    CheckBox checkBoxCs2004;
    CheckBox checkBoxCs2005;

    TextView nfcTextView;                // Initializing TextView
    ImageView brunelLogoImageView;       // Initializing ImageView for Brunel University Logo

    String nfcTagCode;                   // String to store NFC Tag Code
    NfcAdapter mNfcAdapter;              // Initializing NFC Adapter

    private GestureDetectorCompat gestureObject;    // Initializing Gesture Detector to detect swipes

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gestureObject = new GestureDetectorCompat(this, new LearnGesture());    // Gesture Detector for swipe detection

        //------------------------------------------------

        readNfcButton = (Button) findViewById(R.id.readNFCButton);  // Textview to read String from NFC, to test if the NFC is Working or not
        settingsButton = (Button) findViewById(R.id.settingsButton);
        nfcTextView = (TextView) findViewById(R.id.nfcTextView);
        //-------------------------------------------------

        //Checkboxes

        checkBoxCs2001 = (CheckBox) findViewById(R.id.checkBoxCs2001);
        checkBoxCs2002 = (CheckBox) findViewById(R.id.checkBoxCs2002);
        checkBoxCs2003 = (CheckBox) findViewById(R.id.checkBoxCs2003);
        checkBoxCs2004 = (CheckBox) findViewById(R.id.checkBoxCs2004);
        checkBoxCs2005 = (CheckBox) findViewById(R.id.checkBoxCs2005);

        //-------------------------------------------------

        brunelLogoImageView = (ImageView) findViewById(R.id.brunelLogoImageView);
        mNfcAdapter = NfcAdapter.getDefaultAdapter(this);


        //------------------------------------------------

        // Calling testNFC Method to test if NFC is Enabled or Disabled
        testNFC();
    }

    // This Method Verifies the chip no and start and end time, and then retrieves the lecture data
    public void verifyUsersPlace() {

        String whereClause = "chipNo = '" + nfcTagCode + "'" + " AND start <= '" + attendedTime + "' AND end > '" + attendedTime + "'";
        //+ " and start >= " + formattedCurrentTime + "and end <= " + formattedCurrentTime
        BackendlessDataQuery dataQuery = new BackendlessDataQuery();
        dataQuery.setWhereClause(whereClause);

        Backendless.Persistence.of(TimetableServer.class).find(dataQuery,
                new AsyncCallback<BackendlessCollection<TimetableServer>>() {
                    @Override
                    public void handleResponse(BackendlessCollection<TimetableServer> foundData) {

                        List<TimetableServer> similarData = foundData.getData();
                        for (int i = 0; i < similarData.size(); i++) {
                            Log.i("Time", similarData.get(i).getChipNo());
                            Log.i("Time", similarData.get(i).getModuleNo());
                            Log.i("Time", similarData.get(i).getRoom());
                            Log.i("Time", similarData.get(i).getStart().toString());
                            Log.i("Time", similarData.get(i).getEnd().toString());
                            Log.i("Time", "Current Time = " + attendedTime);

                            //Getting Module details
                            chipNo = similarData.get(i).getChipNo();
                            moduleNo = similarData.get(i).getModuleNo();
                            room = similarData.get(i).getRoom();
                            start = similarData.get(i).getStart();
                            end = similarData.get(i).getEnd();

                        }
                    }

                    @Override
                    public void handleFault(BackendlessFault fault) {
                        // an error has occurred, the error code can be retrieved with fault.getCode()
                        Log.i("Time", "Fault - " + fault);
                    }
                });

    }

    // This Method registers User by saving all user and lecture data to AttendanceListServer Page in server
    public void registerUser(){


        // Getting User details from Users page in server

        studentID = Backendless.UserService.CurrentUser().getProperty("studentID").toString();
        cardID = Backendless.UserService.CurrentUser().getProperty("cardID").toString();
        email = Backendless.UserService.CurrentUser().getEmail();
        courseName = Backendless.UserService.CurrentUser().getProperty("courseName").toString();
        level = Backendless.UserService.CurrentUser().getProperty("level").toString();
        name = Backendless.UserService.CurrentUser().getProperty("name").toString();

        // Printing all the above details to test
        //Log.i("Time","Current User - " + studentID + " " + cardID + " " + email + " " + courseName + " " + level + " " + name );

        AttendanceListServer attendanceListServer = new AttendanceListServer();

        // Sets all the values in AttendanceListServer page in server, which means student attended the lecture

        attendanceListServer.setStudentID(studentID);
        attendanceListServer.setCardID(cardID);
        attendanceListServer.setEmail(email);
        attendanceListServer.setCourseName(courseName);
        attendanceListServer.setLevel(level);
        attendanceListServer.setName(name);
        attendanceListServer.setRoom(room);
        attendanceListServer.setChipNo(chipNo);
        attendanceListServer.setStart(start);
        attendanceListServer.setEnd(end);
        attendanceListServer.setAttendedTime(attendedTime);

        Backendless.Persistence.save( attendanceListServer, new AsyncCallback<AttendanceListServer>() {
            public void handleResponse( AttendanceListServer response )
            {
                Toast.makeText(getApplicationContext(), "Registered Successfully", Toast.LENGTH_SHORT).show();
            }

            public void handleFault( BackendlessFault fault )
            {
                Log.i("Time", fault.toString());
            }
        });
    }

    public void clicked(View view){

        if(nfcTagCode == null)
        {
            Toast.makeText(getApplicationContext(), "NFC chip was not detected", Toast.LENGTH_SHORT).show();
        }
        else {
            Log.i("test", nfcTagCode);               // Printing NFC TAG Code for Verification when button is clicked
            Toast.makeText(getApplicationContext(), nfcTagCode, Toast.LENGTH_SHORT).show();

            // Once the button is clicked if verifies if user is in correct room and retrieves all the information of User from server
            verifyUsersPlace();

            // Once the information is retrieved, this stores all the data (Student Details and Lecture details) to AttendanceListServer page in server
            registerUser();
        }
    }

    public void settingsButton(View view){
        Intent settingsActivityIntent = new Intent(getApplicationContext(), SettingsActivity.class);
        startActivity(settingsActivityIntent);
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
        // Check if there is any any NDEF discovered to process the TAG
        if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(getIntent().getAction())) {
            processIntent(getIntent());     // If NDEF is discovered, It calls prcoessIntent method to get the NFC TAG Code
            getCurrentTime();
        }
    }


    // This method gets the current time when the phone was Tapped on NFC Chip
    public void getCurrentTime(){

        Calendar currentTime = Calendar.getInstance();

        // Calendar Format for Current Time
        SimpleDateFormat currentTimeFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        // Getting Current Time in the above format
        attendedTimeString = currentTimeFormat.format(currentTime.getTime());
        try {
            Date attendedTime = currentTimeFormat.parse(attendedTimeString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Toast.makeText(getApplicationContext(), attendedTimeString, Toast.LENGTH_SHORT).show();

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

    //This Method Checks if one checkbox is ticked, Other checkbox will be unticked
    public void onCheckBoxClicked(View view){

        switch(view.getId()) {
            case R.id.checkBoxCs2001:
                if (true)
                    checkBoxCs2002.setChecked(false);
                    checkBoxCs2003.setChecked(false);
                    checkBoxCs2004.setChecked(false);
                    checkBoxCs2005.setChecked(false);
                break;

            case R.id.checkBoxCs2002:
                if (true)
                    checkBoxCs2001.setChecked(false);
                    checkBoxCs2003.setChecked(false);
                    checkBoxCs2004.setChecked(false);
                    checkBoxCs2005.setChecked(false);
                break;

            case R.id.checkBoxCs2003:
                if (true)
                    checkBoxCs2001.setChecked(false);
                    checkBoxCs2002.setChecked(false);
                    checkBoxCs2004.setChecked(false);
                    checkBoxCs2005.setChecked(false);
                break;

            case R.id.checkBoxCs2004:
                if (true)
                    checkBoxCs2001.setChecked(false);
                    checkBoxCs2002.setChecked(false);
                    checkBoxCs2003.setChecked(false);
                    checkBoxCs2005.setChecked(false);
                break;

            case R.id.checkBoxCs2005:
                if (true)
                    checkBoxCs2001.setChecked(false);
                    checkBoxCs2002.setChecked(false);
                    checkBoxCs2003.setChecked(false);
                    checkBoxCs2004.setChecked(false);
                break;

        }

    }


    //-------------------------Swipe Detection---------------------------------

    // This method detects Motion and touch events
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.gestureObject.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    // This is executed when gesture or motion is detected
    class LearnGesture extends GestureDetector.SimpleOnGestureListener{

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

            if(e2.getX() > e1.getX()){

                // This Code executes Swipe From Left to Right
                // Opens History Screen
                Intent historyActivityIntent = new Intent(getApplicationContext(), HistoryActivity.class);
                startActivity(historyActivityIntent);

            }
            else if (e2.getX() < e1.getX()){

                // This Code executes Swipe From Right to Left
                // Opens TimeTable Screen
                Intent timeTableActivityIntent = new Intent(getApplicationContext(), TimetableActivity.class);
                startActivity(timeTableActivityIntent);

            }
            return true;
        }
    }
}
