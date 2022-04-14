package com.lonesurvivor.Models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Location {

    private String name;
    private String description;
    private String image;
    private ArrayList<String> items;
    private NPC npc;
    private Map<String, String> direction;

    public Location(String name, String description, String image , ArrayList<String> items, NPC npc, HashMap<String, String> direction) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.items = items;
        this.npc = npc;
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

    public NPC getNpc() {
        return npc;
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
                ", image='" + image + '\'' +
                ", items=" + items +
                ", npc=" + npc +
                ", direction=" + direction +
                '}';
    }
}

