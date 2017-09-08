package com.example.patrickcaruso.activistwatch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

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
    }


    /**
     * Generates a request to the server to create a user
     * @param username the user's declared username
     * @param email the user's declared email
     * @param password the user's declared password
     */
    private void registerUser(String username,
                              String email,
                              String password) {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = generateRegisterUserUrl(username, email, password);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //ERROR
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
