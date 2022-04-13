package com.lonesurvivor.Models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Location {

    private String name;
    private String description;
    private String image;
    private ArrayList<String> items;
    private Map<String, String> direction;

    public Location(String name, String description, String image ,ArrayList<String> items, HashMap<String, String> direction) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.items = items;
        this.direction = direction;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public ArrayList<String> getItems() {
        return items;
    }

    public Map<String, String> getDirection() {
        return direction;
    }

    public String getImage() {
        return image;
    }

    @Override
    public String toString() {
        return "Location{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", items=" + items +
                ", direction=" + direction +
                '}';
    }
}

