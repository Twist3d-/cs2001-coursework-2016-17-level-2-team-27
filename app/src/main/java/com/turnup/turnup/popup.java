package com.turnup.turnup;
import android.app.Activity;
import android.os.Bundle;
public class popup extends Activity {
    @Override
    //Basic popup screen used after multiple unsuccessful login attempts
    public void onBackPressed(){}
    static com.turnup.turnup.popup popup;
    protected void onCreate(Bundle savedInstanceState) {
        popup = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lockpopup);
    }

    public static com.turnup.turnup.popup getInstance(){
        return popup;
    }

}