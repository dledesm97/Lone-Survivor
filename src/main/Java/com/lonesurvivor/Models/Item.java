package com.lonesurvivor.Models;

//import com.lonesurvivor.Views.LoneSurvivorBase;

import java.util.Locale;

public class Item {

    private String itemName;

    public Item() {}

    public Item(String itemName) {
        this.itemName = itemName;
    }

//    public void useItem(){
//        //depending on the item that is passed they will have a different functionality
//        switch (itemName.toLowerCase()){
//            case "flare":
//                LoneSurvivorBase.GUI.setMultipleText("Hopefully someone can see my signal");
//                break;
//            case "radio":
//                LoneSurvivorBase.GUI.setMultipleText("Maybe I can play some music or get in contact with someone");
//                break;
//            case "raft":
//                LoneSurvivorBase.GUI.setMultipleText("I wonder what I can do with this raft");
//                break;
//            case "compass":
//                LoneSurvivorBase.GUI.setMultipleText("At least now I know which direction I am going");
//                break;
//            case "flashlight":
//                LoneSurvivorBase.GUI.setMultipleText("Finally....I can see!");
//                break;
//            case "lifejacket":
//                LoneSurvivorBase.GUI.setMultipleText("I'm going to put this life jacket on, never know when you will fall into a body of water...");
//                break;
//        }
//    }

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
