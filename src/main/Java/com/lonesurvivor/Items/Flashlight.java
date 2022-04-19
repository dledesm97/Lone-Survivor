package com.lonesurvivor.Items;

import com.lonesurvivor.Models.Location;
import com.lonesurvivor.Models.Player;
import com.lonesurvivor.Views.LocationFrame;

import java.util.ArrayList;

public class Flashlight extends Item{

    public void use(){
        String locationName = Player.getInstance().getCurrentLocation().getName();
        Location playerLocation = Player.getInstance().getCurrentLocation();
        ArrayList<String> items = Player.getInstance().getInventory();

        for (String item : items) {
            if (locationName.equals("forest") || locationName.equals("forest dead end") || locationName.equals("bridge") || locationName.equals("village") || locationName.equals("tlg building")
                    || locationName.equals("outside plane")) {
                LocationFrame.textDisplayGui("It's bright outside...this FLASHLIGHT won't make much of a difference\n you can LOOK AROUND here.");
            }
            if (item.equals("flashlight")) {
                LocationFrame.textDisplayGui( Player.getInstance().viewLocItems() + "\n You notice some exits: " + playerLocation.getDirection());
            }
        }
    }
}
