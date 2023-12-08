package com.example.tufoodtrucksloginscreen;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

public class TruckDisplayLoad extends AppCompatActivity{
    LinearLayout linearLayout;
    private static String truckGoTo = null;

    public static String getTruck(){
        if(truckGoTo == null){
            truckGoTo = new String();
        }
        return truckGoTo;
    }

    protected void OnCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_truckselection);

        linearLayout = findViewById(R.id.LinearLayout);

        for(int i=1; i <= 2; i++){ //change to go through the loop as many times as there are trucks in the database
            TextView truckName = new TextView(this);
            truckName.setText("Truck" + String.valueOf(i)); //Set text to name of truck from data base
            linearLayout.addView(truckName);
            Button truckView = new Button(this);
            truckView.setText("View");
            linearLayout.addView(truckView);
            truckView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //set global name value for later layouts?
                            truckGoTo = "here";
                            Intent i = new Intent(TruckDisplayLoad.this, TruckInformationLoad.class);
                            startActivity(i); //Transfer truck name here somehow for TruckInformationLoad, maybe try fragments?
                        }
                    }
            );
        }
    }
}
