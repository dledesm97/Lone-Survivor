package com.lonesurvivor.Models;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class MusicClass {
    public void audioFile() {
        try {
            File song  =  new File("src/main/resources/Get Up OffaThat Thing.wav");
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(song);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.flush();

        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
    }
}
