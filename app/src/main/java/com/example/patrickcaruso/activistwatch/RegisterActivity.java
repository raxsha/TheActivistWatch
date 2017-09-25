package com.example.patrickcaruso.activistwatch;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.patrickcaruso.activistwatch.Constants.URLConstants;
import com.example.patrickcaruso.activistwatch.Database.Database;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button loginButton = (Button) findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText usernameTextArea = (EditText) findViewById(R.id.registerUsername);
                EditText emailTextArea = (EditText) findViewById(R.id.loginEmail);
                EditText passwordTextArea = (EditText) findViewById(R.id.registerPassword);
                EditText passwordAgainTextArea = (EditText) findViewById(R.id.registerPasswordAgain);

                String username = usernameTextArea.getText().toString();
                String email = emailTextArea.getText().toString();
                String password = passwordTextArea.getText().toString();
                String passwordAgain = passwordAgainTextArea.getText().toString();

                if (validateRegisterInformation(email, username, password, passwordAgain)) {
                    try {
                        int registerResponse = Database.register(email, username, password);
                        if (registerResponse == -1) {
                            //TODO this is an error that needs to be addressed
                        } else {
                            Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
                            startActivity(intent);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private boolean validateRegisterInformation(String email,
                                                String username,
                                                String password,
                                                String passagain) {
        boolean faulty = false;
        if (!isValidEmail(email)) {
            displayEmailRegistrationErrorMessage(email);
            faulty = true;
        }
        if (!isValidUsername(username)) {
            displayUsernameRegistrationErrorMessage(username);
            faulty = true;
        }
        if (!isValidPassword(password)) {
            displayPasswordRegistrationErrorMessage();
            faulty = true;
        }
        if (!password.equals(passagain)) {
            displayPasswordMismatchRegistrationErrorMessage();
            faulty = true;
        }
        return !faulty;
    }

    private void displayPasswordMismatchRegistrationErrorMessage() {
        Context context = getApplicationContext();
        CharSequence text = "The passwords entered do not match.";
        Toast.makeText(context, text, Toast.LENGTH_LONG).show();
    }

    /**
     * Displays the registration error message for a bad
     * email
     */
    private void displayEmailRegistrationErrorMessage(String email) {
        Context context = getApplicationContext();
        CharSequence text = "The email " + email + " entered is not a valid email. Please verify your email.";
        Toast.makeText(context, text, Toast.LENGTH_LONG).show();
    }

    /**
     * Displays the registration error message for a bad
     * username
     */
    private void displayUsernameRegistrationErrorMessage(String username) {
        Context context = getApplicationContext();
        CharSequence text = "The username " + username + " must be all lowercase and over 8 character!";
        Toast.makeText(context, text, Toast.LENGTH_LONG).show();
    }

    /**
     * Displays the registration error message for a bad
     * password
     */
    private void displayPasswordRegistrationErrorMessage() {
        Context context = getApplicationContext();
        CharSequence text = "The password must be at least 8 alphanumeric characters. No symbols!";
        Toast.makeText(context, text, Toast.LENGTH_LONG).show();
    }

    private boolean isValidEmail(String string){
        final String EMAIL_PATTERN =
                "[a-z0-9]+@[a-z0-9.-]+\\.[a-z]{2,}";
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(string.toLowerCase());
        return matcher.matches();
    }

    private boolean isValidUsername(String string){
        String PATTERN = "[a-zA-Z0-9]{6,18}";
        Pattern pattern = Pattern.compile(PATTERN);
        Matcher matcher = pattern.matcher(string);
        return matcher.matches();
    }

    private boolean isValidPassword(String string){
        String PATTERN;
        PATTERN = "[0-9a-zA-Z]{5,20}";
        Pattern pattern = Pattern.compile(PATTERN);
        Matcher matcher = pattern.matcher(string);
        return matcher.matches();
    }
}