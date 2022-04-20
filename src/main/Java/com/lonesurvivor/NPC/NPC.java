package com.lonesurvivor.NPC;

import com.lonesurvivor.Models.Player;
import com.lonesurvivor.Views.LocationFrame;
import com.lonesurvivor.Views.MasterGui;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class NPC {
    private String name;
    private Double power;
    private Double health;
    private Boolean alive = true;

    public NPC() {
    }

    public NPC(String name, Double power, Double health) {
        this.name = name;
        this.power = power;
        this.health = health;
    }

    public void talk(String noun){
        if(noun.equals(Player.getInstance().getCurrentLocation().getNpc().getName())){
            switch(noun.toLowerCase()){
                case "pilot":
                    Pilot pilot = new Pilot();
                    pilot.firstDialogue();
                    break;
                case "attendant":
                    Attendant attendant = new Attendant();
                    attendant.firstDialogue();
                    break;
                case "troll":
                    Troll troll = new Troll();
                    troll.firstDialogue();
                    break;
                case"survivor":
                    Survivor survivor = new Survivor();
                    survivor.firstDialogue();
                    break;
                default:
                    LocationFrame.textDisplayGui("Umm they cant talk");
            }
        }
        else{
            LocationFrame.textDisplayGui("There is no " + noun + " here.");
        }
    }

    //check is if the NPC is alive or dead
    public Boolean isAlive(){
        if(health <= 0.0){
            alive = false;
        } else {
            alive = true;
        }
        return  alive;
    }

    public void attack() throws IOException {
        if(isAlive()) {
            int playerHealth = Player.getInstance().getHealth();
            if (playerHealth > 0) {
                int randHitPoint = (int) (Math.random() * (power - 2 + 1)) + 2;
                int inflictedDamage = playerHealth - randHitPoint;

                if (inflictedDamage <= 0) {
                    Player.getInstance().setHealth(0);
                } else {
                    Player.getInstance().setHealth(inflictedDamage);
                }
                LocationFrame.textDisplayGui("\n\t" + name.toUpperCase() + " attacked you!" + "\n\tYou took " + randHitPoint + " damage!"
                );
                MasterGui.refreshPlayerStats(); //refresh player's stats after setting new health

                if (Player.getInstance().getHealth() == 0) {
                    Timer timer = new Timer();
                    timer.schedule(new TimerTask() {
                        public void run() {
                            LocationFrame.textDisplayGui("\n\t" + name.toUpperCase() + " has killed you!\n\tGAME OVER ");
                        }
                    }, 3000);
                    System.exit(0);
                }
            }
        }
    }

    public String getName() {
        return name;
    }

    public Double getPower() {
        return power;
    }

    public Double getHealth() {
        return health;
    }

    public void setHealth(Double health) {
        this.health = health;
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
