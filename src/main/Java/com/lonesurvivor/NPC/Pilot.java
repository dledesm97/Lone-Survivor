package com.lonesurvivor.NPC;

import com.lonesurvivor.Views.LocationFrame;

import java.util.Timer;
import java.util.TimerTask;

public class Pilot extends NPC {
    Timer timer = new Timer();

    public void firstDialogue(){
        LocationFrame.textDisplayGui("YOU: Woah...what happend out there?! How did we crash?");
        timer.schedule(new TimerTask() {
            public void run() {
                secondDialogue();
            }
        }, 3000);
    }

    public void secondDialogue(){
        LocationFrame.textDisplayGui("YOU: Woah...what happend out there?! How did we crash?");
        LocationFrame.textDisplayGui("PILOT: Wait...how are you alive?? Everyone was supposed to be dead!");
        timer.schedule(new TimerTask() {
            public void run() {
                thirdDialogue();
            }
        }, 5000);
    }

    public void thirdDialogue(){
        LocationFrame.textDisplayGui("YOU: Woah...what happened out there?! How did we crash?");
        LocationFrame.textDisplayGui("PILOT: Wait...how are you alive?? Everyone was supposed to be dead!");
        LocationFrame.textDisplayGui("YOU: What do you mean supposed to?! This was \nplanned?! "
                + "Do you ATTACK the PILOT or just let him succumb to his wounds naturally?");
    }
}
