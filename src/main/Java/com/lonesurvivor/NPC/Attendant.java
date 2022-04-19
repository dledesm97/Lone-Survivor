package com.lonesurvivor.NPC;

import com.lonesurvivor.Views.LocationFrame;

import java.util.Timer;
import java.util.TimerTask;

public class Attendant {

    Timer timer = new Timer();
    public void firstDialogue(){
        LocationFrame.textDisplayGui("You: Hey you made it! you look like you are in \ncritical condition");
        timer.schedule(new TimerTask() {
            public void run() {
                secondDialogue();
            }
        }, 3000);
    }

    public void secondDialogue(){
        LocationFrame.textDisplayGui("You: Hey you made it! you look like you are in critical condition");
        LocationFrame.textDisplayGui("Attendant: Please..you need to do something or I will die");
        timer.schedule(new TimerTask() {
            public void run() {
                thirdDialogue();
            }
        }, 3000);
    }

    public void thirdDialogue(){
        LocationFrame.textDisplayGui("You: Hey you made it! you look like you are in critical condition");
        LocationFrame.textDisplayGui("Attendant: Please..you need to do something or I will die");
        LocationFrame.textDisplayGui("You: I dont know what to do! should I ATTACK the ATTENDANT and end her misery or just move on");
    }
}
