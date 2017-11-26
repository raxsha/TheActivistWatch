package com.example.patrickcaruso.activistwatch;

import android.content.Intent;
import android.graphics.Point;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.support.annotation.NonNull;
import android.widget.TextView;

import com.example.patrickcaruso.activistwatch.Adapter.EventAdapter;
import com.example.patrickcaruso.activistwatch.Database.Database;
import com.example.patrickcaruso.activistwatch.Event.Event;
import com.example.patrickcaruso.activistwatch.Event.ThisEvent;
import com.example.patrickcaruso.activistwatch.User.User;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Ju-Hwan on 11/10/2017.
 */

public class EventPageActivity extends AppCompatActivity{
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);


        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        //see if organization exists in database
        int eventId = 0;
        eventId = savedInstanceState.getInt("orgId");
        Event event;
        String response = "";
        try {
            response = Database.lookupEvent(eventId);
        } catch (IOException e) {
            e.printStackTrace();
        }
        event = EventAdapter.adapt(response);
        SimpleDateFormat sdfr = new SimpleDateFormat("dd/MMM/yyy");
        //if yes then get its name, description, picture, events, keywords(tags)
        String eventNameStr = event.getName();
        String eventDescription = event.getDescription();
        Date eveDate = event.getTime();
        String eventDate = sdfr.format(eveDate);
        Point eventPoint = event.getLocation();
        String eventLocation = eventPoint.toString();
        TextView eventName = new TextView(this);
        TextView eventDes = new TextView(this);
        TextView eventTime = new TextView(this);
        TextView eventLoc = new TextView(this);

        eventName.setText(eventNameStr);
        eventDes.setText(eventDescription);
        eventTime.setText(eventDate);
        eventLoc.setText(eventLocation);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mToggle = new ActionBarDrawerToggle(this,mDrawerLayout,R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        NavigationView nv = (NavigationView)findViewById(R.id.nv1);
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case(R.id.createEvent):
                        Intent in = new Intent(getApplicationContext(), CreateEventActivity.class);
                        startActivity(in);
                        break;
                    case(R.id.organizations):
                        in = new Intent(getApplicationContext(), MyOrganizationsActivity.class);
                        startActivity(in);
                        break;
                    case(R.id.userprofile):
                        in = new Intent(getApplicationContext(), EditProfileActivity.class);
                        startActivity(in);
                }
                return true;
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        return mToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.topbar_menu, menu);
        return true;
    }
}
