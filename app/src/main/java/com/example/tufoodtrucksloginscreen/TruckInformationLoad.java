package com.example.tufoodtrucksloginscreen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.example.tufoodtrucksloginscreen.model.backupDatabase.truck;

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

        int trucknum = TruckDisplayLoad.getTruck(); //Convert name to database name for reference
        truck truckView = new truck();

        truckName = (TextView) findViewById(R.id.TruckName);
        truckName.setText(truckView.getTruck(trucknum)); //Call to global name value here for pulling from the database
        truckLocation = (TextView) findViewById(R.id.locationName);
        truckLocation.setText(truckView.getLocations(trucknum));
        truckRating = (TextView) findViewById(R.id.ratingScore);
        truckRating.setText(truckView.getRating(trucknum));
        truckSchedule = (TextView) findViewById(R.id.scheduleFormat);
        truckSchedule.setText(truckView.getSchedule(trucknum));
        truckWebsite = (TextView) findViewById(R.id.websiteLink);
        truckWebsite.setText(truckView.getWebsite(trucknum));
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
