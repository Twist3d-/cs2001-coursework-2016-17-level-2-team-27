package com.turnup.turnup;

/**
 * Created by Taqi on 2/26/2017.
 */

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.MotionEvent;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class TimetableActivity extends AppCompatActivity {

//    private int day;
//    private String activity;
//    private String description;
//    private int start;
//    private int end;
//    private String room;
//    private String staff;
//    private String moduleNo;
//    private String chipNo;

    private GestureDetectorCompat gestureObject;    // Initializing Gesture Detector to detect swipes

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetable);

        gestureObject = new GestureDetectorCompat(this, new LearnGesture());    // Gesture Detector for swipe detection

        init();
    }

//    public int getDay() {
//        return day;
//    }
//
//    public String getActivity() {
//        return activity;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public int getStart() {
//        return start;
//    }
//
//    public int getEnd() {
//        return end;
//    }
//
//    public String getRoom() {
//        return room;
//    }
//
//    public String getStaff() {
//        return staff;
//    }
//
//    public String getModuleNo() {
//        return moduleNo;
//    }
//
//    public String getChipNo() {
//        return chipNo;
//    }


    public void init() {

        TableLayout stk = (TableLayout) findViewById(R.id.table_main);
        TableRow tbrow0 = new TableRow(this);
        TextView tv0 = new TextView(this);
        tv0.setText(" Day ");
        tv0.setTextColor(Color.BLACK);
        tbrow0.addView(tv0);
        TextView tv1 = new TextView(this);
        tv1.setText(" Activity ");
        tv1.setTextColor(Color.BLACK);
        tbrow0.addView(tv1);
        TextView tv2 = new TextView(this);
        tv2.setText(" Description ");
        tv2.setTextColor(Color.BLACK);
        tbrow0.addView(tv2);
        TextView tv3 = new TextView(this);
        tv3.setText(" Start ");
        tv3.setTextColor(Color.BLACK);
        tbrow0.addView(tv3);
        TextView tv4 = new TextView(this);
        tv4.setText(" End ");
        tv4.setTextColor(Color.BLACK);
        tbrow0.addView(tv4);
        TextView tv5 = new TextView(this);
        tv5.setText(" Room ");
        tv5.setTextColor(Color.BLACK);
        tbrow0.addView(tv5);
        TextView tv6 = new TextView(this);
        tv6.setText(" Staff ");
        tv6.setTextColor(Color.BLACK);
        tbrow0.addView(tv6);
        stk.addView(tbrow0);
        for (int i = 0; i < 25; i++) {
            TableRow tbrow = new TableRow(this);
            TextView t1v = new TextView(this);
            t1v.setText("" + i);
            t1v.setTextColor(Color.BLACK);
            t1v.setGravity(Gravity.CENTER);
            tbrow.addView(t1v);
            TextView t2v = new TextView(this);
            t2v.setText(" " + i);
            t2v.setTextColor(Color.BLACK);
            t2v.setGravity(Gravity.CENTER);
            tbrow.addView(t2v);
            TextView t3v = new TextView(this);
            t3v.setText("" + i);
            t3v.setTextColor(Color.BLACK);
            t3v.setGravity(Gravity.CENTER);
            tbrow.addView(t3v);
            TextView t4v = new TextView(this);
            t4v.setText("" + i * 15 / 32 * 10);
            t4v.setTextColor(Color.BLACK);
            t4v.setGravity(Gravity.CENTER);
            tbrow.addView(t4v);
            stk.addView(tbrow);
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
                // Opens Main Screen
                Intent mainActivityIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(mainActivityIntent);

            }
            else if (e2.getX() < e1.getX()){

                // This Code executes Swipe From Right to Left
                // No Code required here, as we dont have any activty to open
            }
            return true;
        }
    }
}