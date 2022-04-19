package com.lonesurvivor.Models;

import com.lonesurvivor.Views.MasterGui;
import org.json.simple.parser.ParseException;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class MusicClass extends JPanel implements ActionListener {
    public static Clip clip = null;
    private JButton audioOff;
    private JButton audioOn;
    private static MusicClass musicClass = null;
    private static volatile boolean playing;

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
                File getFx = new File("src/main/resources/soundFX/item-equip-6904.wav");
                try {
                    audioStream = AudioSystem.getAudioInputStream(getFx);
                    clip = AudioSystem.getClip();
                    clip.open(audioStream);
                    clip.start();
                } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
                    ex.printStackTrace();
                }
            case "go":
                File goFx = new File("src/main/resources/soundFX/clean-fast-swooshaiff-14784.wav");
                try {
                    audioStream = AudioSystem.getAudioInputStream(goFx);
                    clip = AudioSystem.getClip();
                    clip.open(audioStream);
                    clip.start();
                } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
                    ex.printStackTrace();
                }
            default:
                //
        }

    }

    public static void backgroundFx() {

            File song = new File("src/main/resources/soundFX/ambient-01_junglehillswav-14614.wav");
            AudioInputStream audioStream = null;
            try {
                audioStream = AudioSystem.getAudioInputStream(song);
            } catch (UnsupportedAudioFileException | IOException ex) {
                ex.printStackTrace();
            }

            try {
                clip = AudioSystem.getClip();
            } catch (LineUnavailableException ex) {
                ex.printStackTrace();
            }
            try {
                clip.open(audioStream);
            } catch (LineUnavailableException | IOException ex) {
                ex.printStackTrace();
            }
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.getControls();
            clip.flush();

    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == audioOn) {
            if (!clip.isActive()) {
                backgroundFx();
            } else {
                System.out.println("Clip is already playing!!!");
            }
        }

        else if(e.getSource()==audioOff) {

            clip.close();
            clip.stop();


    }

//            playing = false;
//            while (!playing) {
//                playing = true;
//                File song = new File("src/main/resources/soundFX/ambient-01_junglehillswav-14614.wav");
//                AudioInputStream audioStream = null;
//                try {
//                    audioStream = AudioSystem.getAudioInputStream(song);
//                } catch (UnsupportedAudioFileException | IOException ex) {
//                    ex.printStackTrace();
//                }
//
//                try {
//                    clip = AudioSystem.getClip();
//                } catch (LineUnavailableException ex) {
//                    ex.printStackTrace();
//                }
//                try {
//                    clip.open(audioStream);
//                } catch (LineUnavailableException | IOException ex) {
//                    ex.printStackTrace();
//                }
//                clip.start();
//                clip.loop(Clip.LOOP_CONTINUOUSLY);
//                clip.getControls();
//                clip.flush();




    }
}

