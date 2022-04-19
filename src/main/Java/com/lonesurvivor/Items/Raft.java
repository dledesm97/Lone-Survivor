package com.lonesurvivor.Items;

import com.lonesurvivor.Models.Location;
import com.lonesurvivor.Models.Player;
import com.lonesurvivor.Views.LocationFrame;

import java.util.ArrayList;

public class Raft {
    public void use() {
        Location playerLocation = Player.getInstance().getPlayerLocation();
        ArrayList<String> items = Player.getInstance().getInventory();
        for (int aItem = 0; aItem < items.size(); aItem++) {
            if (items.get(aItem).equals("raft")) {
                LocationFrame.textDisplayGui("This raft can help me");
            } else {
                LocationFrame.textDisplayGui("You do not have a raft");
            }
        }
    }
}
