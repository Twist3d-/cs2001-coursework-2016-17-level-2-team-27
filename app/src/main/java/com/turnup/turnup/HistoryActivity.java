package com.turnup.turnup;

import android.content.Intent;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;

public class HistoryActivity extends AppCompatActivity {

    private GestureDetectorCompat gestureObject;    // Initializing Gesture Detector to detect swipes

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        gestureObject = new GestureDetectorCompat(this, new LearnGesture());    // Gesture Detector for swipe detection
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

                // This Code executes Swipe From Right to Left
                // No Code required here, as we dont have any activty to open

            }
            else if (e2.getX() < e1.getX()){

                // This Code executes Swipe From Left to Right
                // Opens Main Screen
                Intent mainActivityIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(mainActivityIntent);

            }
            return true;
        }
    }
}
