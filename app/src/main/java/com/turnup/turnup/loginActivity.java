package com.turnup.turnup;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;


public class loginActivity extends AppCompatActivity {

    //Variables for Backendless Server
    public String applicationKey = "F07E3DE9-AC6E-BBCB-FFD0-126A34E1EA00";
    public String androidSecurityKey = "B22EE5C0-C8C4-20C8-FF6D-015481D89900";
    public String androidServerVersion = "v1";


    //Variables for Android activity
    public Button b1, b2;
    public EditText pass,usr;
    public String username, password;
    int counter=5;
    int multiplier=1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ///Initialising all content
        setContentView(R.layout.activity_login);
        String appVersion = "v1";
        Backendless.initApp(this, applicationKey, androidSecurityKey, androidServerVersion);
        //b1 is the loginButton b2 is sign up button
        b1 = (Button) findViewById(R.id.readNFCButton);
        b2 = (Button) findViewById(R.id.button2);

        //Password is editText2, username is editText
        pass = (EditText) findViewById(R.id.editText2);
        usr = (EditText) findViewById(R.id.editText);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                password = pass.getText().toString();
                username = usr.getText().toString();
                //Toast used to check login is working correctly
                Toast toast = Toast.makeText(getApplicationContext(), password + " " + username, Toast.LENGTH_SHORT);
                toast.show();
                Backendless.UserService.login(username, password, new AsyncCallback<BackendlessUser>() {
                    @Override
                    //Successful login will execute this
                    public void handleResponse(BackendlessUser response) {
                        Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
                        Intent mainActivityIntent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(mainActivityIntent);
                    }

                    @Override
                    //Unsucessful login attempt will excecute this
                    public void handleFault(BackendlessFault fault) {
                        counter--;

                        if (counter == 0) {
                            startActivity(new Intent(loginActivity.this, com.turnup.turnup.popup.class));
                            new Handler().postDelayed(new Runnable() {
                                public void run() {
                                    popup.getInstance().finish();
                                }
                            }, 10000 * multiplier);
                            counter = 5;
                            multiplier = multiplier * 2;
                        } else {
                            showError();
                        }

                    }
                });



            }

        });

        b2.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                Intent in = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(in);
            }

        });

    }
    private void showError(){
        usr.setError("Invalid username and password");
    }




}
