package com.example.patrickcaruso.activistwatch;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.EditText;
import android.view.MenuInflater;

import com.example.patrickcaruso.activistwatch.Database.Database;
import com.example.patrickcaruso.activistwatch.User.ThisUser;
import com.example.patrickcaruso.activistwatch.User.User;

import java.io.IOException;

public class EditProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        /*
        User user;
        try {
            user = Database.lookupUserObject(ThisUser.getId());
        } catch (IOException e) {
            user = null;
        }

        EditText myTextBox = (EditText) findViewById(R.id.edit_name);
        myTextBox.setText("User's name here");
        */
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.topbar_menu, menu);
        return true;
    }
}
