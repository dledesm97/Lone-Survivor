package com.lonesurvivor.Models;

import com.lonesurvivor.Utils.JSONParserClass;
import com.lonesurvivor.Views.MasterGui;
import org.json.simple.parser.ParseException;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class MusicClass extends JPanel implements ActionListener {
    public static Clip clip = null;
    public static Clip backgroundClip = null;
    public static Clip attackClip = null;
    private JButton audioOff;
    private JButton audioOn;
    private static MusicClass musicClass = null;
    private boolean playing = true;



    MasterGui masterGui;

    public static MusicClass getInstance() {
        if (musicClass == null) {
            musicClass = new MusicClass();
        }
        return musicClass;
    }

    public MusicClass() {

        audioOffButton();
        audioOnButton();
    }

    public void audioOnButton() {
        audioOn = new JButton("audioOn");
        audioOn.setFont(new Font("Arial", Font.PLAIN, 15));
        audioOn.setSize(80, 30);
        audioOn.setLayout(new BorderLayout());
        audioOn.setLocation(280, 795);
        audioOn.addActionListener(this);
        add(audioOn);
        audioOn.setVisible(true);
    }

    public void audioOffButton() {
        audioOff = new JButton("audioOff");
        audioOff.setFont(new Font("Arial", Font.PLAIN, 15));
        audioOff.setSize(80, 30);
        audioOff.setLayout(new BorderLayout());
        audioOff.setLocation(300, 795);
        audioOff.addActionListener(this);
        add(audioOff);
        audioOff.setVisible(true);

    }

    public static void soundFx(String verb) {

        AudioInputStream audioStream = null;
        switch (verb) {
            case "get":
               // File getFx = new File("src/main/resources/soundFX/item-equip-6904.wav");
                InputStream getFx = getFileFromResourceAsStream("soundFX/item-equip-6904.wav");
                try {
                    audioStream = AudioSystem.getAudioInputStream(getFx);
                    clip = AudioSystem.getClip();
                    clip.open(audioStream);
                    clip.start();
                } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
                    ex.printStackTrace();
                }
                break;
            case "go":
                //File goFx = new File("src/main/resources/soundFX/clean-fast-swooshaiff-14784.wav");
                InputStream goFx = getFileFromResourceAsStream("soundFX/clean-fast-swooshaiff-14784.wav");
                try {
                    audioStream = AudioSystem.getAudioInputStream(goFx);
                    clip = AudioSystem.getClip();
                    clip.open(audioStream);
                    clip.start();
                } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
                    ex.printStackTrace();
                }
                break;
            case "attack":
                //File attackFx = new File("src/main/resources/soundFX/karate-chop-6357.wav");
                InputStream attackFx = getFileFromResourceAsStream("soundFX/karate-chop-6357.wav");
                try {
                    audioStream = AudioSystem.getAudioInputStream(attackFx);
                    attackClip = AudioSystem.getClip();
                    attackClip.open(audioStream);
                    attackClip.start();
                } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
                    ex.printStackTrace();
                }
                break;
            default:
                //
        }

    }

    public static void backgroundFx() {

        //File song = new File("src/main/resources/soundFX/ambient-01_junglehillswav-14614.wav");
        InputStream song = getFileFromResourceAsStream("soundFX/ambient-01_junglehillswav-14614.wav");
        AudioInputStream audioStream = null;
        try {
            audioStream = AudioSystem.getAudioInputStream(song);
        } catch (UnsupportedAudioFileException | IOException ex) {
            ex.printStackTrace();
        }

        try {
            backgroundClip = AudioSystem.getClip();
        } catch (LineUnavailableException ex) {
            ex.printStackTrace();
        }
        try {
            backgroundClip.open(audioStream);
        } catch (LineUnavailableException | IOException ex) {
            ex.printStackTrace();
        }
        backgroundClip.start();
        backgroundClip.loop(Clip.LOOP_CONTINUOUSLY);
        backgroundClip.getControls();
        backgroundClip.flush();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == audioOn) {
            if(!backgroundClip.isActive())
            backgroundFx();
            } else System.out.println("Clip is already playing!!!");

       if(e.getSource() == audioOff) {

//            clip.close();
           backgroundClip.stop();




    }
    }
    private static InputStream getFileFromResourceAsStream(String fileName) {
        ClassLoader classLoader = JSONParserClass.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);
        if (inputStream == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            return inputStream;
        }
    }
}

