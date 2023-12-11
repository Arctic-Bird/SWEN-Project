package com.example.tufoodtrucksloginscreen;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.tufoodtrucksloginscreen.model.backupDatabase.truck;
import com.example.tufoodtrucksloginscreen.model.backupDatabase.food;
import com.example.tufoodtrucksloginscreen.model.backupDatabase.beverages;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;


public class TruckMenuLoad extends AppCompatActivity {
    //This class will dynamically add the menu options from the chosen food truck
    LinearLayout linearLayout;


    Button toTrucks;

        public void createFoodShort(food foodList, int i) {
            TextView foodName = new TextView(this);
            foodName.setText(foodList.getFood(i));
            foodName.setTextSize(18);
            TextView cost = new TextView(this);
            cost.setText("$" + foodList.getCost(i));
            cost.setTextSize(14);
            TextView veganorno = new TextView(this);
            if(foodList.getVegi(i) || foodList.getVeg(i)){
                veganorno.setText("Vegan Option");
            }
            else{
                veganorno.setText(" ");
            }
            veganorno.setTextSize(14);
            linearLayout.addView(foodName);
            linearLayout.addView(cost);
            linearLayout.addView(veganorno);
            if(veganorno.equals("Vegan Option")){
                TextView blank = new TextView(this);
                blank.setText("");
                blank.setTextSize(14);
                linearLayout.addView(blank);
            }
        }

        public void createBevShort(beverages bevList, int i){
            TextView bevName = new TextView(this);
            bevName.setText(bevList.getBev(i));
            bevName.setTextSize(18);
            TextView cost = new TextView(this);
            cost.setText("$" + bevList.getCost(i));
            cost.setTextSize(14);
            TextView blank = new TextView(this);
            blank.setText("");
            blank.setTextSize(14);
            linearLayout.addView(bevName);
            linearLayout.addView(cost);
            linearLayout.addView(blank);
        }
        @SuppressLint("MissingInflatedId")
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.fragment_menuview);
            int trucknum = TruckDisplayLoad.getTruck();
            truck truckList = new truck();
            food foodList = new food();
            beverages bevList = new beverages();

            linearLayout = (LinearLayout) findViewById(R.id.linearlayout2);
            TextView entree = new TextView(this);
            entree.setText("Entrees:");
            entree.setTextSize(24);
            entree.setBackgroundColor(Color.parseColor("#FBEA4E"));
            linearLayout.addView(entree);
            for (int i = 0; i <= foodList.getNumFood(); i++) { //number of foods in the truck
                if(!foodList.getSideCheck(i)){
                    if(truckList.getTruck(trucknum) == foodList.getProvider(0)){
                        if(foodList.getPizzaCheck(i)){
                            createFoodShort(foodList, i);
                        }
                    }
                    if(truckList.getTruck(trucknum) == foodList.getProvider(1)){
                        if(foodList.getTeaCheck(i)){
                            createFoodShort(foodList, i);
                        }
                    }
                    if(truckList.getTruck(trucknum) == foodList.getProvider(2)){
                        if(foodList.getThirdCheck(i)){
                            createFoodShort(foodList, i);
                        }
                    }
                }
            }
            TextView sides = new TextView(this);
            sides.setText("Sides:");
            sides.setTextSize(24);
            sides.setBackgroundColor(Color.parseColor("#FBEA4E"));
            linearLayout.addView(sides);
            for (int i = 0; i <= foodList.getNumFood(); i++) { //number of foods in the truck
                if(foodList.getSideCheck(i)){
                    if(truckList.getTruck(trucknum) == foodList.getProvider(0)){
                        if(foodList.getPizzaCheck(i)){
                            createFoodShort(foodList, i);
                        }
                    }
                    if(truckList.getTruck(trucknum) == foodList.getProvider(1)){
                        if(foodList.getTeaCheck(i)){
                            createFoodShort(foodList, i);
                        }
                    }
                    if(truckList.getTruck(trucknum) == foodList.getProvider(2)){
                        if(foodList.getThirdCheck(i)){
                            createFoodShort(foodList, i);
                        }
                    }
                }
            }
            TextView bev = new TextView(this);
            bev.setText("Beverages:");
            bev.setTextSize(24);
            bev.setBackgroundColor(Color.parseColor("#FBEA4E"));
            linearLayout.addView(bev);
            for (int i = 0; i <= bevList.getNumBev(); i++) { //number of beverages in the truck
                if(truckList.getTruck(trucknum) == foodList.getProvider(0)){
                    if(bevList.getPizzaCheck(i)){
                        createBevShort(bevList, i);
                    }
                }
                if(truckList.getTruck(trucknum) == foodList.getProvider(1)){
                    if(bevList.getTeaCheck(i)){
                        createBevShort(bevList, i);
                    }
                }
                if(truckList.getTruck(trucknum) == foodList.getProvider(2)){
                    if(bevList.getThirdCheck(i)){
                        createBevShort(bevList, i);
                    }
                }
            }
        }

}
