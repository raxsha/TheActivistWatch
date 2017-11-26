package com.example.patrickcaruso.activistwatch;

import android.content.Intent;
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
import android.view.View;
import android.widget.Button;

import com.example.patrickcaruso.activistwatch.Database.Database;
import com.example.patrickcaruso.activistwatch.Event.Event;

import java.io.IOException;
import java.util.LinkedList;

public class DashboardActivity extends AppCompatActivity {

    private LinkedList<Event> eventQueue = new LinkedList<>();
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private Button smashButton;
    private Button hellNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        try {
            eventQueue = loadAllEvents();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        smashButton = (Button) findViewById(R.id.smash);
        hellNo = (Button) findViewById(R.id.hellno);

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

        smashButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Event event = eventQueue.removeLast();
                if (event != null) {
                    System.out.println("We like: " + event.getName());
                }
            }
        });
    }

    private LinkedList<Event> loadAllEvents() throws IOException {
        LinkedList<Event> events = new LinkedList<>();
        for (Event event: Database.getAllEvents()) {
            events.addLast(event);
        }
        return events;
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