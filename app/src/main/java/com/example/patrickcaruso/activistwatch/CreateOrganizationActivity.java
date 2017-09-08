package com.example.patrickcaruso.activistwatch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class CreateOrganizationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_organization);

        EditText editOrg =  (EditText) findViewById(R.id.orgName);
        EditText editDes =  (EditText) findViewById(R.id.description);
    }
}
