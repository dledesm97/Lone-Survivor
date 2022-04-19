package com.lonesurvivor.Items;

import com.lonesurvivor.Models.Location;
import com.lonesurvivor.Models.Player;
import com.lonesurvivor.Views.LocationFrame;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Radio extends Item{
    Timer timer = new Timer();
    public static Clip clip = null;

    public void turnOn() throws InterruptedException {
        Location playerLocation = Player.getInstance().getPlayerLocation();
        ArrayList<String> items = Player.getInstance().getInventory();
        for (int aItem = 0; aItem < items.size(); aItem++) {
            if (items.get(aItem).equals("radio")) {
                LocationFrame.textDisplayGui("This radio looks like it can still work! I am going to try to turn it on....IT DOES WORK!");
                timer.schedule(new TimerTask() {
                    public void run() {
                        playMusic();
                    }
                }, 2800);
            } else {
                LocationFrame.textDisplayGui("You do not have a radio");
            }
        }
    }

    public void playMusic(){
        AudioInputStream audioStream = null;
        File getFx = new File("src/main/resources/soundFX/radio-transmission.wav");
        try {
            audioStream = AudioSystem.getAudioInputStream(getFx);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
            timer.schedule(new TimerTask() {
                public void run(){
                    sendMessage();
                }
            },3000);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            ex.printStackTrace();
        }
    }

    public void sendMessage(){
        LocationFrame.textDisplayGui("You: If someone is out there please send some help! I was in flight 210, my name is Rennie");
        timer.schedule(new TimerTask() {
            public void run(){
                stopAudio();
            }
        },8000);
    }

    public void stopAudio(){
        clip.stop();
        timer.schedule(new TimerTask() {
            public void run(){
                rescued();
            }
        },10000);
    }

    public void rescued(){
        LocationFrame.textDisplayGui("Wait a second...the call worked! There is a team here to rescue me!");
        try{
            Thread.sleep(5000);
        }catch(InterruptedException e){
        e.printStackTrace();
        }
        System.exit(0);
    }
}
