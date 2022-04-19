package com.lonesurvivor.Items;

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
        //Location playerLocation = Player.getInstance().getCurrentLocation();
        ArrayList<String> items = Player.getInstance().getInventory();
        for (String item : items) {
            if (item.equals("radio")) {
                LocationFrame.textDisplayGui("This radio looks like it can still work! You try to turn it on....IT DOES WORK!");
                timer.schedule(new TimerTask() {
                    public void run() {
                        playMusic();
                    }
                }, 2800);
            } else {
                LocationFrame.textDisplayGui("You do not have a radio.");
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
                    try {
                        sendMessage();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            },3000);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            ex.printStackTrace();
        }
    }

    public void sendMessage() throws InterruptedException {
        timer.schedule(new TimerTask() {
            public void run(){
                stopAudio();
            }
        },8000);
        LocationFrame.textDisplayGui("You: If someone *BREAK* is out there *BREAK* please send some help! *BREAK* I was in flight 210, *BREAK* my name is Rennie!!!");
        Thread.sleep(5000);
        LocationFrame.textDisplayGui("YOU: I SAY AGAIN! *BREAK* S.O.S my name is RENNIE! *OUT*");
    }

    public void stopAudio(){
        clip.stop();
        timer.schedule(new TimerTask() {
            public void run(){
                rescued();
            }
        },5000);
    }

    public void rescued(){
        LocationFrame.textDisplayGui("Wait a second...you here breaking static and a faint voice emerging from the static. You turn up the volume. " +
                "\nVOICE: COPY! RENNIE this is DAVID! *BREAK* We have your location *BREAK* standby for helicopter rescue in 5! *OUT*");
        try{
            Thread.sleep(8000);
        }catch(InterruptedException e){
        e.printStackTrace();
        }
        System.exit(0);
    }
}
