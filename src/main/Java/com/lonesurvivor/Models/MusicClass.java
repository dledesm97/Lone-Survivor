package com.lonesurvivor.Models;

import com.lonesurvivor.Views.MasterGui;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class MusicClass extends JPanel implements ActionListener{
   public static  Clip clip = null;
    private JButton audioOff;
    private JButton audioOn;
    MasterGui masterGui;
   public MusicClass(){

       audioOffButton();
       audioOnButton();
    }

    public  void audioOnButton(){
        audioOn = new JButton("audioOn");
        audioOn.setFont(new Font("Arial", Font.PLAIN, 15));
        audioOn.setSize(80, 30);
        audioOn.setLayout(new BorderLayout());
        audioOn.setLocation(280, 795);
        audioOn.addActionListener(this);
        add(audioOn);
        audioOn.setVisible(true);
    }
    public  void audioOffButton(){
        audioOff = new JButton("audioOff");
        audioOff.setFont(new Font("Arial", Font.PLAIN, 15));
        audioOff.setSize(80, 30);
        audioOff.setLayout(new BorderLayout());
        audioOff.setLocation(300, 795);
        audioOff.addActionListener(this);
        add(audioOff);
        audioOff.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()== audioOn) {
            File song = new File("src/main/resources/gameSounds.wav");
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

        }else  if(e.getSource() == audioOff){
            clip.close();
            clip.stop();
        }

    }
}

