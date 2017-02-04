package com.turnup.turnup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

public class SignUpActivity extends AppCompatActivity {

    // Variable for Backendless Server Application Details

    public String applicationKey = "F07E3DE9-AC6E-BBCB-FFD0-126A34E1EA00";
    public String androidSecurityKey = "B22EE5C0-C8C4-20C8-FF6D-015481D89900";
    public String androidServerVersion = "v1";

    //-------------------------------------------------------------------------

    // Variables for storing the user information

    public EditText firstNameEditText, lastNameEditText, studentIdEditText, studentEmailIdEditText, studentCardIdEditText, passwordEditText, courseNameEditText, courseLevelEditText;
    public String firstNameString, lastNameString, studentIdString, studentEmailIdString, studentCardIdString, passwordString, courseNameString, courseLevelString;
    public Button signUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        Backendless.initApp( this, applicationKey, androidSecurityKey, androidServerVersion );      // Initializing Backendless Server

        // Initializing all the content

        firstNameEditText = (EditText) findViewById(R.id.firstNameEditText);
        lastNameEditText = (EditText) findViewById(R.id.lastNameEditText);
        studentIdEditText = (EditText) findViewById(R.id.studentIdEditText);
        studentEmailIdEditText = (EditText) findViewById(R.id.emailIdEditText);
        studentCardIdEditText = (EditText) findViewById(R.id.studentCardIdEditText);
        passwordEditText = (EditText) findViewById(R.id.passwordEditText);
        signUpButton = (Button) findViewById(R.id.signUpButton);
        courseNameEditText = (EditText) findViewById(R.id.courseNameEditText);
        courseLevelEditText = (EditText) findViewById(R.id.courseLevelEditText);

        //UserRegistration();
    }

    public void SignUpMethod(View view)
    {
        System.out.println("Reached Here");
        Intent mainIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(mainIntent);
        firstNameString = firstNameEditText.getText().toString();
        lastNameString = lastNameEditText.getText().toString();
        studentIdString = studentIdEditText.getText().toString();
        studentEmailIdString = studentEmailIdEditText.getText().toString();
        studentCardIdString = studentCardIdEditText.getText().toString();
        passwordString = passwordEditText.getText().toString();
        courseNameString = courseNameEditText.getText().toString();
        courseLevelString = courseLevelEditText.getText().toString();
    }


    //---------------------------------------------------------
    //This method will register user
    public void UserRegistration(){
        BackendlessUser user = new BackendlessUser();
        user.setProperty("email", studentEmailIdString);
        user.setPassword(passwordString);

        Backendless.UserService.register( user, new AsyncCallback<BackendlessUser>(){
            public void handleResponse(BackendlessUser registeredUser){
                System.out.println("Successfully Registered");
            }

            public void handleFault(BackendlessFault fault){
                System.out.println("Error");
            }
        } );
    }
}
