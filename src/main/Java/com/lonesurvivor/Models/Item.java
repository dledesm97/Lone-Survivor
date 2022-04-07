package com.lonesurvivor.Models;

import java.util.Locale;

public class Item {

    private String itemName;

    public Item() {}

    public Item(String itemName) {
        this.itemName = itemName;
    }

    public void useItem(){
//        System.out.println("Using " + getItemName());
        switch (itemName.toLowerCase()){
            case "flare":
                System.out.println("Hopefully someone can see my signal");
                break;
            case "radio":
                System.out.println("Maybe I can play some music or get in contact with someone");
                break;
            case "raft":
                System.out.println("I wonder what I can do with this raft");
                break;
            case "compass":
                System.out.println("At least now I know which direction I am going");
                break;
            case "flashlight":
                System.out.println("Finally....I can see!");
                break;
            case "lifejacket":
                System.out.println("I'm going to put this life jacket on, never know when you will fall into a body of water...");
                break;
        }
    }

    public String getItemName() {
        return itemName;
    }

}
