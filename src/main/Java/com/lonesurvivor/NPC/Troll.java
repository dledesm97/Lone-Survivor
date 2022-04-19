package com.lonesurvivor.NPC;

import com.lonesurvivor.Views.LocationFrame;

import java.util.Timer;
import java.util.TimerTask;

public class Troll {
    Timer timer = new Timer();
    public void firstDialogue(){
        LocationFrame.textDisplayGui("You: Woah!! are you a TROLL??\n You really live under bridges??");
        timer.schedule(new TimerTask() {
            public void run() {
                secondDialogue();
            }
        }, 4000);
    }

    public void secondDialogue(){
        LocationFrame.textDisplayGui("You: Woah!! are you a TROLL??\n You really live under bridges??");
        LocationFrame.textDisplayGui("Troll: Yes we really live under bridges!!\nBut listen you are not the first to pass through \nhere, this has happened for years that there \nmaybe a village of people around \nhere by now");
        timer.schedule(new TimerTask() {
            public void run() {
                thirdDialogue();
            }
        }, 5500);
    }

    public void thirdDialogue(){
        LocationFrame.textDisplayGui("You: Woah!! are you a TROLL??\n You really live under bridges??");
        LocationFrame.textDisplayGui("Troll: Yes we really live under bridges!!\nBut listen you are not the first to pass through here\nThis has happened for years that there maybe a \nvillage of people around here by now");
        LocationFrame.textDisplayGui("You: You are a pretty nice TROLL, maybe I \nshouldn't ATTACK you");
    }
}
