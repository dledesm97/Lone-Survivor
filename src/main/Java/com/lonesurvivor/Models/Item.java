package com.lonesurvivor.Models;

public class Item {

    private String name;
    private String description;
    private String location;

    public Item() {}

    public Item(String name, String location) {
        this.name = name;
        //this.description = description;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getLocation() {
        return location;
    }
}
