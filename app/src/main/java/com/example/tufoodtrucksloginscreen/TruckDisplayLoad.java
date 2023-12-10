package com.example.tufoodtrucksloginscreen;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;


public class TruckDisplayLoad extends AppCompatActivity{
    LinearLayout linearLayout;
    private static String truckGoTo = null;


    public static String getTruck(){
        if(truckGoTo == null){
            truckGoTo = new String();
        }
        return truckGoTo;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_truckselection);

        linearLayout = findViewById(R.id.LinearLayout);

        for(int i=1; i <= 2; i++){ //change to go through the loop as many times as there are trucks in the database
            TextView truckName = new TextView(this);
            truckName.setText("Truck" + String.valueOf(i)); //Set text to name of truck from data base
            Button truckView = new Button(this);
            truckView.setText("View");
            if(i % 2 == 0){
                truckName.setBackgroundColor(Color.parseColor("#FFDDDD"));
                truckView.setBackgroundColor(Color.parseColor("#FFDDDD"));
            }
            linearLayout.addView(truckName);
            linearLayout.addView(truckView);
            truckView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //set global name value for later layouts?
                            truckGoTo = "here"; //set call to truck in database
                            Intent i = new Intent(TruckDisplayLoad.this, TruckInformationLoad.class);
                            startActivity(i); //Transfer truck name here somehow for TruckInformationLoad, maybe try fragments?
                        }
                    }
            );
        }
    }
}
