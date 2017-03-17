package com.turnup.turnup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

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

    public EditText firstNameEditText, lastNameEditText, studentIdEditText, studentEmailIdEditText,
            studentCardIdEditText, passwordEditText;

    public Spinner courseNameSpinner, levelSpinner;

    public String firstNameString, lastNameString, studentIdString, studentEmailIdString,
            studentCardIdString, passwordString, courseNameString, courseLevelString;

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

        courseNameSpinner = (Spinner)findViewById(R.id.courseNameSpinner);
        levelSpinner = (Spinner)findViewById(R.id.levelSpinner);

        String[] courseNameItems = new String[]{"Computer Science"};
        ArrayAdapter<String> courseNameAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, courseNameItems);
        courseNameSpinner.setAdapter(courseNameAdapter);

        String[] levelItems = new String[]{"Level 2"};
        ArrayAdapter<String> levelAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, levelItems);
        levelSpinner.setAdapter(levelAdapter);

        //UserRegistration();
    }

    // Executes when Sign Up button is clicked
    public void SignUpMethod(View view)
    {
        System.out.println("Reached Here");
        studentEmailIdString = studentEmailIdEditText.getText().toString();
        passwordString = passwordEditText.getText().toString();
        studentIdString = studentIdEditText.getText().toString();
        studentCardIdString = studentCardIdEditText.getText().toString();
        courseNameString = courseNameSpinner.getSelectedItem().toString();
        courseLevelString = levelSpinner.getSelectedItem().toString();
        firstNameString = firstNameEditText.getText().toString();
        lastNameString = lastNameEditText.getText().toString();

        UserRegistration();
        Intent logInScreenIntent = new Intent(getApplicationContext(), loginActivity.class);
        startActivity(logInScreenIntent);
    }

    // Executes when Log In button is clicked
    public void LogInMethod(View view) {
        Intent logInActivityIntent = new Intent(getApplicationContext(), loginActivity.class);
        startActivity(logInActivityIntent);
    }


    //---------------------------------------------------------
    //This method will register user
    public void UserRegistration(){

        //String Names according to names in server "Users" Page, for convenient use, in case of server changes in future
        String email = "email";
        String studentID = "studentID";
        String cardID = "cardID";
        String courseName = "courseName";
        String level = "level";
        String name = "name";
        //---------------------------------------------

        BackendlessUser user = new BackendlessUser();   // Creating Backendless user.

        //---------Storing User entered data to server in "Users" Page-------------

        //user.setProperty(email, studentEmailIdString);
        user.setEmail(studentEmailIdString);
        user.setPassword(passwordString);
        user.setProperty(studentID, studentIdString);
        user.setProperty(cardID, studentCardIdString);
        user.setProperty(courseName, courseNameString);
        user.setProperty(level, courseLevelString);
        user.setProperty(name, firstNameString + lastNameString);


        //-------------------------------------------------------------------------


        // User Registration Call Back.
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
