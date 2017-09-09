package com.example.patrickcaruso.activistwatch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CreateOrganizationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_organization);

        EditText editOrg =  (EditText) findViewById(R.id.orgName);
        EditText editDes =  (EditText) findViewById(R.id.description);

        String orgName = editOrg.getText().toString();
        String orgDes = editDes.getText().toString();

        Button enterButton = (Button) findViewById(R.id.enterButton);

        if (!orgName.isEmpty() && !orgDes.isEmpty()) {
            enterButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(), CreateEventActivity.class);
                    startActivity(intent);
                }
            });
        }
    }
}
