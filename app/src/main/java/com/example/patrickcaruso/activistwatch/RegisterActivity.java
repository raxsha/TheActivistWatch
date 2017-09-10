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

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button loginButton = (Button) findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText usernameTextArea = (EditText) findViewById(R.id.loginUsername);
                EditText emailTextArea = (EditText) findViewById(R.id.loginEmail);
                EditText passwordTextArea = (EditText) findViewById(R.id.loginPassword);

                String username = usernameTextArea.getText().toString();
                String email = emailTextArea.getText().toString();
                String password = passwordTextArea.getText().toString();

                registerUser(username, email, password);
            }
        });
    }

    /**
     * Displays the registration error message
     * @param username the user's declared username
     * @param email the user's declared email
     * @param password the user's declared password
     */
    private void displayRegistrationErrorMessage(String username,
                                          String email, String password) {
        Context context = getApplicationContext();
        CharSequence text = "Username " + username + ", email " + email + ", or password"
                + password + " are not valid.";
        Toast.makeText(context, text, Toast.LENGTH_LONG).show();
    }

    /**
     * Generates a request to the server to create a user
     * @param username the user's declared username
     * @param email the user's declared email
     * @param password the user's declared password
     */
    private void registerUser(final String username,
                              final String email,
                              final String password) {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = generateRegisterUserUrl(username, email, password);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.equals("success")) {
                            Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
                            startActivity(intent);
                        } else {
                            displayRegistrationErrorMessage(username, email, password);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("RUH ROH");
            }
        });
        queue.add(stringRequest);
    }

    /**
     * Generates a URL to POST user information to in order
     * to add the user to the user database
     * @param username the user's declared username
     * @param email the user's declared email
     * @param password the user's declared password
     * @return an HTTPUrlConnection compatible POST URL
     */
    private String generateRegisterUserUrl(String username,
                                           String email,
                                           String password) {
        StringBuilder sb = new StringBuilder();
        sb.append(URLConstants.ADD_USER_URL_BASE);
        sb.append(URLConstants.POST_DELIMETER);
        sb.append(URLConstants.ADD_USER_USERNAME_ATTRIBUTE);
        sb.append(URLConstants.POST_EQUALS);
        sb.append(username);
        sb.append(URLConstants.POST_AND);
        sb.append(URLConstants.ADD_USER_EMAIL_ATTRIBUTE);
        sb.append(URLConstants.POST_EQUALS);
        sb.append(email);
        sb.append(URLConstants.POST_AND);
        sb.append(URLConstants.ADD_USER_PASSWORD_ATTRIBUTE);
        sb.append(URLConstants.POST_EQUALS);
        sb.append(password);
        return sb.toString();
    }
}
