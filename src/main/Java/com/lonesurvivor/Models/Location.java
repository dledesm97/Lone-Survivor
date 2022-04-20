package com.lonesurvivor.Models;

import com.lonesurvivor.NPC.NPC;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Location {

    private String name;
    private String description;
    private String image;
    private ArrayList<String> items;
    private NPC npc;
    private Map<String, String> direction;
    private Boolean itemsPresent = false;
    private Boolean presentNPC = false;
    private Boolean isAlive = false;

    public Location(String name, String description, String image , ArrayList<String> items, NPC npc, HashMap<String, String> direction) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.items = items;
        this.npc = npc;
        this.direction = direction;
    }

    public Boolean hasItems(){
        if (items.size() > 0){
            return itemsPresent = true;
        } else{
            return itemsPresent = false;
        }
    }

    //checks if npc/opponent is present
    public boolean hasNPC() {
        presentNPC = !Objects.equals(npc.getName(), "");
        return presentNPC;
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

