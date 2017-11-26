package com.example.patrickcaruso.activistwatch;

import android.content.Context;
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
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.patrickcaruso.activistwatch.Adapter.UserAdapter;
import com.example.patrickcaruso.activistwatch.Database.Database;
import com.example.patrickcaruso.activistwatch.Organization.Organization;
import com.example.patrickcaruso.activistwatch.User.ThisUser;
import com.example.patrickcaruso.activistwatch.User.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class MyOrganizationsActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_organizations);
        TextView text = (TextView) findViewById(R.id.myOrgText);
        System.out.println("FUCKING WORK: " + text);
        //Add to ScrollView
        ScrollView scroll = (ScrollView) findViewById(R.id.orgScrollView);
        LinearLayout scrollViewLayout = (LinearLayout)scroll.findViewById(R.id.orgContainer);
        System.out.println("LinearLayout: " + scrollViewLayout);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        //Get organizations list
        User user;
        int userId = ThisUser.getId();
        String response = "";
        try {
            response = Database.lookupUser(userId);
        } catch (IOException e) {
            e.printStackTrace();
        }
        user = UserAdapter.adapt(response);
        System.out.print("TEST: " + user.getUsername());

        List<Organization> orgs = new ArrayList<>();


        try {
            orgs = Database.getOrganizationObjects(Integer.toString(ThisUser.getId()));
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("ORG SIZE: " + orgs.size());



        int i = 0;
        for (Organization org: orgs) {
            String orgNameStr = org.getName();
            System.out.println("ORGNAME: " + orgNameStr);
            DrawerLayout.LayoutParams lparams = new DrawerLayout.LayoutParams(
                    DrawerLayout.LayoutParams.WRAP_CONTENT, DrawerLayout.LayoutParams.WRAP_CONTENT);
            TextView orgNameText = new TextView(this);
            orgNameText.setLayoutParams(lparams);
            orgNameText.setText(orgNameStr);
            scrollViewLayout.addView(orgNameText);
            i++;
        }

        //Add Org Button press
        Button addButton = (Button) findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CreateOrganizationActivity.class);
                startActivity(intent);
            }
        });
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
                    case(R.id.organizations):
                        Intent in = new Intent(getApplicationContext(), MyOrganizationsActivity.class);
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
