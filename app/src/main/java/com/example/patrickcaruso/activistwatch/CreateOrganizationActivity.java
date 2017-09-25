package com.example.patrickcaruso.activistwatch;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.patrickcaruso.activistwatch.Database.Database;

import java.io.IOException;

public class CreateOrganizationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_organization);



        Button enterButton = (Button) findViewById(R.id.enterButton);
        enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editOrg =  (EditText) findViewById(R.id.orgName);
                EditText editDes =  (EditText) findViewById(R.id.description);

                String orgName = editOrg.getText().toString();
                String orgDes = editDes.getText().toString();

                try {
                    //not done yet
                    int createOrgResponse = Database.createOrganization(1, "name", "", "","");
                    if (createOrgResponse > 0) {

                        //Go to Dashboard
                        Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
                        startActivity(intent);
                    } else {
                        displayCreateOrgErrorMessage(orgName, orgDes);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
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
        CharSequence text = "Left Organization name and/or description blank";
        Toast.makeText(context, text, Toast.LENGTH_LONG).show();
    }
}
