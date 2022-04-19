package com.lonesurvivor.Items;

import com.lonesurvivor.Models.Location;
import com.lonesurvivor.Models.Player;
import com.lonesurvivor.Views.LocationFrame;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Radio extends Item{

    public void turnOn() throws InterruptedException {
        Location playerLocation = Player.getInstance().getPlayerLocation();
        ArrayList<String> items = Player.getInstance().getInventory();
        for (int aItem = 0; aItem < items.size(); aItem++) {
            if (items.get(aItem).equals("radio")) {
                LocationFrame.textDisplayGui("This radio looks like it can still work! I am going to turn it on and hopefully it works....IT DOES WORK!");
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    public void run() {
                        playMusic();
                    }
                }, 2800);
            } else {
                LocationFrame.textDisplayGui("You do not have a radio");
            }
        }
    }

    public void playMusic(){
        LocationFrame.textDisplayGui("*Playing Music*");
    }
}
