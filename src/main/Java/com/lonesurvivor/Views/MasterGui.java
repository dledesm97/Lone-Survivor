package com.lonesurvivor.Views;

import com.lonesurvivor.GameEngine.GameEngine;
import org.json.simple.parser.ParseException;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


public class MasterGui implements ActionListener {

    public static GameEngine game = GameEngine.getInstance();

    private static SplashScreen splashScreen;

    static {
        try {
            splashScreen = new SplashScreen();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static LocationFrame locationFrame;

    static {
        try {
            locationFrame = new LocationFrame();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static JFrame frame;

    public MasterGui() throws IOException, ParseException, InterruptedException {
        boolean won = false;

        frame = new JFrame("Lone Survivor");
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add (splashScreen);
        frame.pack();
        frame.setVisible (true);

        while(won == false) {
            won = game.startGame();
            Thread.sleep(4000); //wait 3 seconds
        }
        System.exit(0);
        }

    public static void renderLocationFrame() throws IOException, ParseException, java.text.ParseException {
        frame.getContentPane().removeAll();
        frame.getContentPane().add(locationFrame);
        frame.revalidate();
        frame.repaint();
    }

    public static void refreshFrames() throws IOException {
     locationFrame.setImageLabel();
    }

    public static void refreshInventoryBox() throws IOException {
        locationFrame.setInventoryBox();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

