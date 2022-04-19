package com.lonesurvivor.Items;

import com.lonesurvivor.Models.Player;
import com.lonesurvivor.Views.LocationFrame;

import java.io.IOException;

public class Item {

    private String itemName;

    public Item() {}

    public Item(String itemName) {
        this.itemName = itemName;
    }

    public void useItem() throws IOException, InterruptedException {
        if(Player.getInstance().getInventory().isEmpty()){
            LocationFrame.textDisplayGui("There are no items available");

        }
        //depending on the item that is passed they will have a different functionality
        switch (itemName.toLowerCase()){
            case "flare":
                Flare flare = new Flare();
                try {
                    flare.popSmoke();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
            case "radio":
                Radio radio = new Radio();
                try {
                    radio.turnOn();
                }catch (InterruptedException e) {
                    e.printStackTrace();
                }

                break;
            case "raft":
                Raft raft = new Raft();
                raft.use();
                break;
            case "compass":
                Compass compass = new Compass();
                compass.useCompass();
                break;
            case "flashlight":
                Flashlight flashlight = new Flashlight();
                flashlight.use();
                break;
//            case "lifejacket":
//                LoneSurvivorBase.GUI.setMultipleText("I'm going to put this life jacket on, never know when you will fall into a body of water...");
//                break;
        }
    }

    public String getItemName() {
        return itemName;
    }

    @Override
    public String toString() {
        return "Item{" +
                "itemName='" + itemName + '\'' +
                '}';
    }
}
