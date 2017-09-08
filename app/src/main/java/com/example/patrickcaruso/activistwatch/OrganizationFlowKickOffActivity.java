package com.example.patrickcaruso.activistwatch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class OrganizationFlowKickOffActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organization_flow_kick_off_activity);

        Button yes_button = (Button) findViewById(R.id.YesOrganizationKickOffButton);
        Button no_button = (Button) findViewById(R.id.NoOrganizationKickOffButton);

        //wants to create an organization
        yes_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(R.layout.activity_create_organization);
            }
        });

        //doesn't want to create an organization
        no_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(R.layout.activity_dashboard);
            }
        });
    }
}