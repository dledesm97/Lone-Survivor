package com.lonesurvivor.Models;



import java.util.LinkedHashSet;
import java.util.Set;

public class Player {

    private String name;
    //private ArrayList<String> inventory;
    private Set<String> inventory;
    private Location playerLocation;

    public Player() {

        inventory = new LinkedHashSet<>();
    }

    /*public Player(String name, ArrayList<String> items) {
        this.name = name;
        this.inventory = items;
    }*/

    public String getName() {
        return name;
    }

    public Set<String> getItems() {

        return inventory;
    }

    public void addItems(String item) {
        inventory.add(item);
        System.out.println("Player has added " + item + " to their inventory. ");
    }

    public void setPlayerLocation(Location location) {
        playerLocation = location;
    }

    public Location getPlayerLocation() {
        return playerLocation;
    }

    public void performAction() {}

}
