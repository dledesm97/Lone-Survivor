package com.lonesurvivor.Items;

import com.lonesurvivor.Models.Location;
import com.lonesurvivor.Models.Player;
import com.lonesurvivor.Utils.JSONParserClass;
import com.lonesurvivor.Views.LocationFrame;
import com.lonesurvivor.Views.MasterGui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Compass {

    public void useCompass(){
        Location playerLocation = Player.getInstance().getPlayerLocation();
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
