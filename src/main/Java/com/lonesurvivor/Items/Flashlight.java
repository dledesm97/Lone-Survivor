package com.lonesurvivor.Items;

import com.lonesurvivor.Models.Location;
import com.lonesurvivor.Models.Player;
import com.lonesurvivor.Views.LocationFrame;

import java.util.ArrayList;

public class Flashlight {
    public void use(){
        String locationName = Player.getInstance().getPlayerLocation().getName();
        Location playerLocation = Player.getInstance().getPlayerLocation();
        ArrayList<String> items = Player.getInstance().getInventory();
        for (int aItem = 0; aItem < items.size(); aItem++) {
            if(locationName.equals("forest") || locationName.equals("forest dead end") || locationName.equals("bridge") || locationName.equals("village") || locationName.equals("tlg building")
                    || locationName.equals("outside plane")){
                LocationFrame.textDisplayGui("It's bright outside...this flashlight won't make much of a difference\n you can LOOK AROUND here");
            }
            if (items.get(aItem).equals("flashlight")) {
                LocationFrame.textDisplayGui("All I see is " + playerLocation.getItems() +"and some exits..\n" + playerLocation.getDirection());
            }
        }
    }
}
