package com.lonesurvivor.NPC;

import com.lonesurvivor.Views.LocationFrame;

import java.util.Timer;
import java.util.TimerTask;

public class Pilot extends NPC {
    Timer timer = new Timer();

    public void firstDialogue(){
        LocationFrame.textDisplayGui("You: Woah...what happend out there?! How did we crash?");
        timer.schedule(new TimerTask() {
            public void run() {
                secondDialogue();
            }
        }, 3000);
    }

    public void secondDialogue(){
        LocationFrame.textDisplayGui("You: Woah...what happend out there?! How did we crash?");
        LocationFrame.textDisplayGui("Pilot: Wait...how are you alive?? Everyone was supposed to be dead!");
        timer.schedule(new TimerTask() {
            public void run() {
                thirdDialogue();
            }
        }, 3000);
    }

    public void thirdDialogue(){
        LocationFrame.textDisplayGui("You: Woah...what happened out there?! How did we crash?");
        LocationFrame.textDisplayGui("Pilot: Wait...how are you alive?? Everyone was supposed to be dead!");
        LocationFrame.textDisplayGui("You: What do you mean supposed to?! This was \nplanned?! "
                + "Should I ATTACK this PILOT or just go elsewhere?");
    }
}
