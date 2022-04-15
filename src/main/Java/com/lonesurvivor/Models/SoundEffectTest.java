package com.lonesurvivor.Models;
/*
This is a Method to test the sounds and implement effects,
Sample sounds rightclick play to invoke UI
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SoundEffectTest extends JFrame {
     SoundEffects soundEffects;
    // Constructor
    public SoundEffectTest() {
        // Pre-load all the sound files
        SoundEffects.init();
        SoundEffects.volume = SoundEffects.Volume.LOW;  // un-mute

        // Set up UI components
        Container cp = this.getContentPane();
        cp.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton btnSound1 = new JButton("ATTACK");
        btnSound1.addActionListener(e -> SoundEffects.ATTACK.play());
        cp.add(btnSound1);
        JButton btnSound2 = new JButton("BEAR");
        btnSound2.addActionListener(e -> SoundEffects.BEAR.play());
        cp.add(btnSound2);
        JButton btnSound3 = new JButton("FLEE");
        btnSound3.addActionListener(e -> SoundEffects.FLEE.play());
        cp.add(btnSound3);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Test SoundEffct");
        this.pack();
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new SoundEffectTest();
    }
}
