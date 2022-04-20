package com.lonesurvivor.Models;

import com.lonesurvivor.NPC.NPC;
import com.lonesurvivor.Views.LocationFrame;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

/*
 * Attack Engine Util that handles turn by turn combat
 */

public class AttackEngine {

    private AttackEngine(){}

    public static void attack() {
        Location currentLocation = Player.getInstance().getCurrentLocation();
        NPC opponent = currentLocation.getNpc();
        int opponentHealth = opponent.getHealth().intValue();
        int playerPower = Player.getInstance().getAttackPoints();

        if (opponentHealth > 0) {
            int randHitPoint = (int) (Math.random() * (playerPower - 2 + 1)) + 2;
            int inflictedDamage = opponentHealth - randHitPoint;

            if (inflictedDamage <= 0) {
                currentLocation.getNpc().setHealth((double) 0);
            } else {
                currentLocation.getNpc().setHealth((double) inflictedDamage);
            }

            LocationFrame.textDisplayGui("\n\t" + currentLocation.getNpc().getName().toUpperCase() + "'s Health: " + currentLocation.getNpc().getHealth().intValue()
                    + "\n\tYou attacked the " + currentLocation.getNpc().getName().toUpperCase()
                    + "!!\n\tYou inflicted " + randHitPoint + " damage!"
            );

            if (!currentLocation.getNpc().isAlive()) {
                LocationFrame.textDisplayGui("\n\tWhat makes the grass grow?\n\tBLOOD BLOOD BLOOD! \n\tYou have killed " + currentLocation.getNpc().getName().toUpperCase() + "!!!!");
            }

            Player.getInstance().setActionTracker(2);

        } else{
            LocationFrame.textDisplayGui(currentLocation.getNpc().getName().toUpperCase() + " is already dead. Calm down killer.");
        }

        //delay opponent from attacking for 1.8 seconds to make it more fluid
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                try {
                    opponent.attack();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }, 1900);
    }
}
