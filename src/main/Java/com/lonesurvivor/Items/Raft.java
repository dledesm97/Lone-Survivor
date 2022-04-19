package com.lonesurvivor.Items;

//import com.lonesurvivor.Models.Location;
import com.lonesurvivor.Models.Player;
import com.lonesurvivor.Views.LocationFrame;

import java.util.ArrayList;

public class Raft extends Item{
    public void use() {
        //Location playerLocation = Player.getInstance().getCurrentLocation();
        ArrayList<String> items = Player.getInstance().getInventory();

        for (String item : items) {
            if (item.equals("raft")) {
                LocationFrame.textDisplayGui("This raft might be able to help you...but not here.");
            } else {
                LocationFrame.textDisplayGui("You do not have a raft.");
            }
        }
    }
}
