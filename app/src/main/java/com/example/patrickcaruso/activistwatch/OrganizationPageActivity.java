package com.example.patrickcaruso.activistwatch;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.patrickcaruso.activistwatch.Adapter.OrganizationAdapter;
import com.example.patrickcaruso.activistwatch.Database.Database;
import com.example.patrickcaruso.activistwatch.Organization.Organization;
import com.example.patrickcaruso.activistwatch.Organization.ThisOrganization;

import java.io.IOException;

public class OrganizationPageActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organization_page);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        //see if organization exists in database
        Organization org;
        int orgId = ThisOrganization.getId();
        String response = "";
        try {
            response = Database.lookupOrganization(orgId);
        } catch (IOException e) {
            e.printStackTrace();
        }
        org = OrganizationAdapter.adapt(response);

        //if yes then get its name, description, picture, events, keywords(tags)
        String orgNameStr = org.getName();
        String orgDescription = org.getDescription();
        String orgEvents = org.getEvents();
        String orgTags = org.getKeywords();

        TextView orgNameText = new TextView(this);
        TextView orgDescriptionText = new TextView(this);
        TextView orgEventsText = new TextView(this);
        TextView orgTagsText = new TextView(this);

        orgNameText.setText(orgNameStr);
        orgDescriptionText.setText(orgDescription);
        orgEventsText.setText(orgEvents);
        orgTagsText.setText(orgTags);

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