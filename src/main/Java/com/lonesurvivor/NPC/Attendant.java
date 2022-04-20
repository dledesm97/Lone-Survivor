package com.lonesurvivor.NPC;

import com.lonesurvivor.Views.LocationFrame;

import java.util.Timer;
import java.util.TimerTask;

public class Attendant {

    Timer timer = new Timer();
    public void firstDialogue(){
        LocationFrame.textDisplayGui("YOU: Hey you made it! you look like you are in \ncritical condition!");
        timer.schedule(new TimerTask() {
            public void run() {
                secondDialogue();
            }
        }, 3000);
    }

    public void secondDialogue(){
        LocationFrame.textDisplayGui("YOU: Hey you made it! You look like you are in critical condition!");
        LocationFrame.textDisplayGui("ATTENDANT: Please..you need to do something!!! I AM IN SO MUCH PAIN!");
        timer.schedule(new TimerTask() {
            public void run() {
                thirdDialogue();
            }
        }, 4000);
    }

    public void thirdDialogue(){
        LocationFrame.textDisplayGui("YOU: Hey you made it! you look like you are in critical condition");
        LocationFrame.textDisplayGui("ATTENDANT: Please..you need to do something!!! I AM IN SO MUCH PAIN!");
        LocationFrame.textDisplayGui("YOU: I don't know what to do! Should I ATTACK the ATTENDANT and end her misery or just move on?");
    }
}
