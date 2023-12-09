package com.example.tufoodtrucksloginscreen;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class TruckMenuLoad extends AppCompatActivity {
    //This class will dynamically add the menu options from the chosen food truck
    LinearLayout linearLayout;
    Button toTrucks;
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.fragment_menuview);

            linearLayout = (LinearLayout) findViewById(R.id.linearlayout2);
            TextView entree = new TextView(this);
            entree.setText("Entrees:");
            entree.setTextSize(24);
            entree.setBackgroundColor(Color.parseColor("#FBEA4E"));
            linearLayout.addView(entree);
            for (int i = 1; i <= 2; i++) { //number of foods in the truck
                //if(food is a Side){
                //skip
                //}
                //else{
                TextView foodName = new TextView(this);
                foodName.setText("food name pull here");
                foodName.setTextSize(18);
                TextView cost = new TextView(this);
                cost.setText("cost pull here");
                cost.setTextSize(10);
                TextView veganorno = new TextView(this);
                veganorno.setText("Either say 'Vegan Option' or leave blank");
                veganorno.setTextSize(10);
                linearLayout.addView(foodName);
                linearLayout.addView(cost);
                linearLayout.addView(veganorno);
                if(veganorno.equals("Vegan Option")){
                    TextView blank = new TextView(this);
                    blank.setText("");
                    blank.setTextSize(10);
                    linearLayout.addView(blank);
                }
                //}
            }
            TextView sides = new TextView(this);
            sides.setText("Sides:");
            sides.setTextSize(24);
            sides.setBackgroundColor(Color.parseColor("#FBEA4E"));
            linearLayout.addView(sides);
            for (int i = 1; i <= 2; i++) { //number of foods in the truck
                //if(food is an entree){
                //skip
                //}
                //else{
                TextView foodName = new TextView(this);
                foodName.setText("food name pull here");
                foodName.setTextSize(18);
                TextView cost = new TextView(this);
                cost.setText("cost pull here");
                cost.setTextSize(10);
                TextView veganorno = new TextView(this);
                veganorno.setText("Either say 'Vegan Option' or leave blank");
                veganorno.setTextSize(10);
                linearLayout.addView(foodName);
                linearLayout.addView(cost);
                linearLayout.addView(veganorno);
                if(veganorno.equals("Vegan Option")){
                    TextView blank = new TextView(this);
                    blank.setText("");
                    blank.setTextSize(10);
                    linearLayout.addView(blank);
                }
                //}
            }
            TextView bev = new TextView(this);
            bev.setText("Beverages:");
            bev.setTextSize(24);
            bev.setBackgroundColor(Color.parseColor("#FBEA4E"));
            linearLayout.addView(bev);
            for (int i = 1; i <= 2; i++) { //number of beverages in the truck
                TextView bevName = new TextView(this);
                bevName.setText("drink name pull here");
                bevName.setTextSize(18);
                TextView cost = new TextView(this);
                cost.setText("cost pull here");
                cost.setTextSize(10);
                TextView blank = new TextView(this);
                blank.setText("");
                blank.setTextSize(10);
                linearLayout.addView(bevName);
                linearLayout.addView(cost);
                linearLayout.addView(blank);
            }
        }
}
