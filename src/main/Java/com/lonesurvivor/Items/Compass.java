package com.lonesurvivor.Items;

import com.lonesurvivor.Models.Location;
import com.lonesurvivor.Models.Player;
import com.lonesurvivor.Views.LocationFrame;

import java.util.ArrayList;

public class Compass extends Item {

    public void useCompass(){
        Location playerLocation = Player.getInstance().getCurrentLocation();
        ArrayList<String> items = Player.getInstance().getInventory();

        for (int aItem = 0; aItem < items.size(); aItem++) {
            if (items.get(aItem).equals("compass")) {
                LocationFrame.textDisplayGui(playerLocation.getDirection().toString());
            }
            else{
                LocationFrame.textDisplayGui("You do not have a compass");
            }
        }
    }
}
