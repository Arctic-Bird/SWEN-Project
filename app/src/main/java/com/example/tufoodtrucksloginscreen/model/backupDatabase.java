package com.example.tufoodtrucksloginscreen.model;

public class backupDatabase {
    public static class truck{
        String[] name = {"Pizza Boys", "Tea Time", "Third Truck"};
        String[] location = {"SomeNumber York Road", "SomeNumber Cross Campus Boulevard", "SomeNumber TowsonTown Boulevard"};
        Double[] rating = {4.9, 4.8, 3.9};
        String[] readSchedule ={"1970-01-01T00:00:01.234+00:00", "1970-01-01T00:00:12.345+00:00", "1970-01-01T00:00:00.247+00:00"};
        String[] Website = {"www.fakewebsite.com", "www.fakewebsite2.com", "www.fakewebsite3.com"};

        public String getTruck(int i){
            return name[i];
        }

        public int getNumTrucks(){
            return name.length - 1;
        }

        public String getLocations(int i) { return location[i];}

        public String getRating(int i) { return rating[i].toString();}

        public String getSchedule(int i){ return readSchedule[i];}

        public String getWebsite(int i){ return Website[i];}
    }

    public static class food{
        String[] provider = {"Pizza Boys", "Tea Time", "Third Truck"};
        String[] name = {"Sausage Pizza", "Hawaiian Pizza", "Pepperoni Pizza", "Anchovies Pizza", "Breadsticks"};
        Boolean[] inPizzaBoys = {true, true, true, true, true};
        Boolean[] inTeaTime = {false, false, false, false, false};
        Boolean[] inThirdTruck = {false, false, false, false, false};
        Boolean[] isMeat = {true, false, true, false, false};
        Boolean[] isVegetarian = {false, true, false, false, true};
        Boolean[] isVegan = {false, false, false, false, false};
        Boolean[] isSeafood = {false, false, false, true, false};
        Double[] cost = {11.17, 11.17, 11.17, 12.17, 8.49};
        Boolean[] isOnPromo = {false, false, false, false, false};
        Boolean[] isFish = {false, false, false, true, false};
        Boolean[] isSide = {false, false, false, false, true};

        public String getFood(int i) { return name[i]; }

        public int getNumFood(){
            return name.length - 1;
        }

        public String getCost(int i) { return cost[i].toString(); }

        public Boolean getVegi(int i) { return isVegetarian[i]; }

        public Boolean getVeg(int i) { return isVegan[i]; }

        public Boolean getSideCheck(int i) { return isSide[i]; }

        public String getProvider(int i) { return provider[i]; }

        public Boolean getPizzaCheck(int i) { return inPizzaBoys[i]; }

        public Boolean getTeaCheck(int i) { return inTeaTime[i]; }

        public Boolean getThirdCheck(int i) { return inThirdTruck[i]; }
    }

    public static class beverages{
        String[] provider = {"Pizza Boys", "Tea Time", "Third Truck"};
        String[] name = {"Boba", "Kombucha", "Iced Tea"};
        Boolean[] inPizzaBoys = {false, false, false};
        Boolean[] inTeaTime = {true, true, false};
        Boolean[] inThirdTruck = {false, false, true};
        Double[] cost = {3.50, 4.77, 4.12};
        Boolean[] isOnPromo = {false, false, false};

        public String getBev(int i) { return name[i]; }

        public int getNumBev(){
            return name.length - 1;
        }

        public String getCost(int i) { return cost[i].toString(); }

        public Boolean getPizzaCheck(int i) { return inPizzaBoys[i]; }

        public Boolean getTeaCheck(int i) { return inTeaTime[i]; }

        public Boolean getThirdCheck(int i) { return inThirdTruck[i]; }

    }

}
