package com.example.tufoodtrucksloginscreen;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

import com.alan.alansdk.AlanConfig;
import com.alan.alansdk.button.AlanButton;
import com.example.tufoodtrucksloginscreen.model.backupDatabase.truck;



public class TruckDisplayLoad extends AppCompatActivity{
    LinearLayout linearLayout;
    private static int truckGoTo = -1;
    private AlanButton alanButton;


    public static int getTruck(){
        if(truckGoTo == -1){
            truckGoTo = 0;
        }
        return truckGoTo;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_truckselection);

        AlanConfig config = AlanConfig.builder().setProjectId("2775d12cbe1b36019bba239f38a422b82e956eca572e1d8b807a3e2338fdd0dc/stage").build();
        alanButton = findViewById(R.id.alan_button);
        alanButton.initWithConfig(config);

        linearLayout = findViewById(R.id.LinearLayout);

        truck truckpile = new truck();

        for(int i=0; i <= truckpile.getNumTrucks(); i++){ //change to go through the loop as many times as there are trucks in the database
            int trucknum = i;
            TextView truckName = new TextView(this);
            truckName.setText(truckpile.getTruck(i)); //Set text to name of truck from data base
            Button truckView = new Button(this);
            truckView.setText("View");
            if(i % 2 == 0){
                truckName.setBackgroundColor(Color.parseColor("#FFDDDD"));
                truckView.setBackgroundColor(Color.parseColor("#fca9a9"));
            }
            linearLayout.addView(truckName);
            linearLayout.addView(truckView);
            truckView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //set global name value for later layouts?
                            truckGoTo = trucknum; //set call to truck in database
                            Intent i = new Intent(TruckDisplayLoad.this, TruckInformationLoad.class);
                            startActivity(i); //Transfer truck name here somehow for TruckInformationLoad, maybe try fragments?
                        }
                    }
            );
        }
    }
}
