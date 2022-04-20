package com.lonesurvivor.NPC;

import com.lonesurvivor.Views.LocationFrame;

import java.util.Timer;
import java.util.TimerTask;

public class Survivor {
    Timer timer = new Timer();
    public void firstDialogue(){
        LocationFrame.textDisplayGui("YOU: Finally, someone who can help me get \nout of here!!!");
        timer.schedule(new TimerTask() {
            public void run() {
                secondDialogue();
            }
        }, 4000);
    }

    public void secondDialogue(){
        LocationFrame.textDisplayGui("YOU: Finally, someone who can help me get \nout of here!!!");
        LocationFrame.textDisplayGui("SURVIVOR: I am glad you were able to find us\n we have all went through the exact same thing.\n");
        timer.schedule(new TimerTask() {
            public void run() {
                thirdDialogue();
            }
        }, 5500);
    }

    public void thirdDialogue(){
        LocationFrame.textDisplayGui("YOU: Finally, someone who can help me get \nout of here!!!");
        LocationFrame.textDisplayGui("SURVIVOR: I am glad you were able to find us\n we have all went through the exact same thing.\n");
        LocationFrame.textDisplayGui("SURVIVOR: Listen here, we all decided to stay \nand get away from the world, but there is \na group of engineers here, in one of these \nbuildings who can help you get back home.");
    }
}
