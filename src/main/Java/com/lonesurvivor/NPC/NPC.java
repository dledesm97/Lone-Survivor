package com.lonesurvivor.NPC;

import com.lonesurvivor.Models.Location;
import com.lonesurvivor.Models.Player;
import com.lonesurvivor.Utils.JSONParserClass;
import com.lonesurvivor.Views.LocationFrame;

import java.util.List;
import java.util.Locale;

public class NPC {
    String name;
    Double power;


    public NPC() {
    }

    public NPC(String name, Double power) {
        this.name = name;
        this.power = power;
    }

    public void talking(String noun){
        if(noun.equals(Player.getInstance().getCurrentLocation().getNpc().getName())){
            switch(noun.toLowerCase()){
                case "pilot":
                    Pilot pilot = new Pilot();
                    pilot.firstDialogue();
            }
        }
        else{
            LocationFrame.textDisplayGui("There is no " + noun + " here");
        }
    }

    public String getName() {
        return name;
    }

    public Double getPower() {
        return power;
    }

    public void setPower(Double power) {
        this.power = power;
    }

    @Override
    public String toString() {
        return "NPC{" +
                "name='" + name + '\'' +
                ", power=" + power +
                '}';
    }
}
