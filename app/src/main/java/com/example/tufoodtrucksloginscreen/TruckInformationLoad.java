package com.example.tufoodtrucksloginscreen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class TruckInformationLoad extends AppCompatActivity {
    private TextView truckName;
    private TextView truckLocation;
    private TextView truckRating;
    private TextView truckSchedule;
    private TextView truckWebsite;
    private Button menu;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_truckdisplay);

        String name = TruckDisplayLoad.getTruck(); //Convert name to database name for reference

        truckName = (TextView) findViewById(R.id.TruckName);
        truckName.setText("Name Here"); //Call to global name value here
        truckLocation = (TextView) findViewById(R.id.locationName);
        truckLocation.setText("Location Here");
        truckRating = (TextView) findViewById(R.id.ratingScore);
        truckRating.setText("rating here");
        truckSchedule = (TextView) findViewById(R.id.scheduleFormat);
        truckSchedule.setText("schedule here");
        truckWebsite = (TextView) findViewById(R.id.websiteLink);
        truckWebsite.setText("Website here");
        menu = (Button) findViewById(R.id.menuViewButton);
        menu.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent i = new Intent(TruckInformationLoad.this, TruckMenuLoad.class);
                startActivity(i);
            }
        });
    }
}
