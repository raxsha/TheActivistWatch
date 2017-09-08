package com.example.patrickcaruso.activistwatch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        Button loginButton = (Button) findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = "Username";
                String password = "Password";
                if (login(username, password)) {
                    Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
                    startActivity(intent);
                } else {
                    displayLoginErrorMessage(username, password);
                }
            }
        });

        Button registerButton = (Button) findViewById(R.id.registerButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * Attempts to login using the given credentials.
     * @param username the user's username
     * @param password the user's password
     * @return true if login is successful and session is authenticated
     *         false if login is unsuccessful
     */
    private boolean login(String username,
                          String password) {
        // Lookup
        return true;
    }

    /**
     * Display
     * @param username
     * @param password
     */
    private void displayLoginErrorMessage(String username,
                                          String password) {

    }
}
