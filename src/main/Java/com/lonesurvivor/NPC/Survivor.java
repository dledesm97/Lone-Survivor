package com.lonesurvivor.NPC;

import com.lonesurvivor.Views.LocationFrame;

import java.util.Timer;
import java.util.TimerTask;

public class Survivor {
    Timer timer = new Timer();
    public void firstDialogue(){
        LocationFrame.textDisplayGui("You: Finally, someone who can help me get \nout of here");
        timer.schedule(new TimerTask() {
            public void run() {
                secondDialogue();
            }
        }, 4000);
    }

    public void secondDialogue(){
        LocationFrame.textDisplayGui("You: Finally, someone who can help me get \nout of here");
        LocationFrame.textDisplayGui("Survivor: I am glad you were able to find us\n we have all went through the exact same thing\n");
        timer.schedule(new TimerTask() {
            public void run() {
                thirdDialogue();
            }
        }, 5500);
    }

    public void thirdDialogue(){
        LocationFrame.textDisplayGui("You: Finally, someone who can help me get \nout of here");
        LocationFrame.textDisplayGui("Survivor: I am glad you were able to find us\n we have all went through the exact same thing\n");
        LocationFrame.textDisplayGui("Survivor: Listen here, we all decided to stay \nhere to get away from the world, but there are \na group of engineers here in one of these \nbuildings who can help you go back");
    }
}
