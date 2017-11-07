package com.example.patrickcaruso.activistwatch;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.patrickcaruso.activistwatch.Adapter.UserAdapter;
import com.example.patrickcaruso.activistwatch.Database.Database;
import com.example.patrickcaruso.activistwatch.User.ThisUser;
import com.example.patrickcaruso.activistwatch.User.User;

import java.io.IOException;

public class LoginScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        setContentView(R.layout.activity_login_screen);

        Button loginButton = (Button) findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText usernameTextArea = (EditText) findViewById(R.id.registerUsername);
                EditText passwordTextArea = (EditText) findViewById(R.id.registerPasswordAgain);

                String username = usernameTextArea.getText().toString();
                String password = passwordTextArea.getText().toString();

                try {
                    int loginResponse = Database.login(username, password);
                    if (loginResponse > 0) {
                        ThisUser.setId(loginResponse);
                        Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
                        startActivity(intent);
                    } else {
                        displayLoginErrorMessage(username, password);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.topbar_menu, menu);
        return true;
    }

    /**
     * Displays the login error message
     * @param username
     * @param password
     */
    private void displayLoginErrorMessage(String username,
                                          String password) {
        Context context = getApplicationContext();
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(LoginScreenActivity.this);
        mBuilder.setIcon(R.drawable.activist_watch_logo_small);
        mBuilder.setTitle("Login Denied");
        mBuilder.setMessage("Invalid username and/or password! ");
        mBuilder.setCancelable(false);

        mBuilder.setPositiveButton("DISMISS", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        AlertDialog alertDialog = mBuilder.create();
        alertDialog.show();
    }
}