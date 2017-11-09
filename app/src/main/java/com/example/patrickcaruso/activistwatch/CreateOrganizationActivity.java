package com.example.patrickcaruso.activistwatch;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.patrickcaruso.activistwatch.Database.Database;
import com.example.patrickcaruso.activistwatch.User.ThisUser;

import java.io.IOException;

public class CreateOrganizationActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_organization);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        Button enterButton = (Button) findViewById(R.id.enterButton);
        enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editOrg =  (EditText) findViewById(R.id.orgName);
                EditText editDes =  (EditText) findViewById(R.id.description);
                EditText editKeywords = (EditText) findViewById(R.id.keywords);

                String orgName = editOrg.getText().toString();
                String orgDes = editDes.getText().toString();
                String orgKeywords = editKeywords.getText().toString();

                try {
                    //not done yet
                    System.out.println("Attempting to save org: " + orgName + ", " + orgDes + ", " + orgKeywords);
                    int createOrgResponse = Database.createOrganization(ThisUser.getId(), orgName, orgDes, orgKeywords, "" + ThisUser.getId());
                    System.out.println(createOrgResponse);
                    if (orgName.equals("") || orgDes.equals("") ) {
                        displayCreateOrgErrorMessage(orgName, orgDes);

                    } else {

                        //Go to Dashboard
                        Intent intent = new Intent(getApplicationContext(), MyOrganizationsActivity.class);
                        startActivity(intent);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
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
                    case(R.id.createEvent):
                        Intent in = new Intent(getApplicationContext(), MyOrganizationsActivity.class);
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

    /**
     * Displays the org name error message
     * @param orgName
     * @param description
     */
    private void displayCreateOrgErrorMessage(String orgName,
                                          String description) {
        Context context = getApplicationContext();

        AlertDialog.Builder mBuilder = new AlertDialog.Builder(CreateOrganizationActivity.this);
        mBuilder.setIcon(R.drawable.activist_watch_logo_small);
        mBuilder.setTitle("Oops!");
        mBuilder.setMessage("Organization Name and/or Description left blank!");
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