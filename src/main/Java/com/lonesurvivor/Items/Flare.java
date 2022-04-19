package com.lonesurvivor.Items;

import com.lonesurvivor.Models.Player;
import com.lonesurvivor.Views.LocationFrame;
import com.lonesurvivor.Views.MasterGui;

import java.io.IOException;
import java.util.ArrayList;

public class Flare extends Item {
    public void popSmoke() throws InterruptedException{
        String locationName = Player.getInstance().getCurrentLocation().getName();
        ArrayList<String> items = Player.getInstance().getInventory();

        for (int aItem = 0; aItem < items.size(); aItem++) {
            if(locationName.equals("cockpit") || locationName.equals("first class") || locationName.equals("service area") || locationName.equals("economy class")){
                LocationFrame.textDisplayGui("Maybe using a flare in a plane is not a good idea..no one will see it!");
            }
            else if (items.get(aItem).equals("flare")) {
                LocationFrame.textDisplayGui("Popped flare into the sky! Maybe there is a team that will rescue you!");
                items.remove(aItem);
                try {
                    MasterGui.refreshInventoryBox();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else{
                LocationFrame.textDisplayGui("You do not have a flare");
            }
        }

    }
}
