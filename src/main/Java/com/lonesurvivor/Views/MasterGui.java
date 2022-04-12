package com.lonesurvivor.Views;

import com.lonesurvivor.GameEngine.GameEngine;
import com.lonesurvivor.Models.Player;
import com.lonesurvivor.Utils.JSONParserClass;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


public class MasterGui implements ActionListener {

    static GameEngine game = GameEngine.getInstance();
    private static Player player = Player.getInstance();
    private static JSONParserClass jsonParserClass = JSONParserClass.getInstance();
    private static SplashScreen splashScreen = new SplashScreen();
    private static LocationFrame locationFrame = new LocationFrame(player.getPlayerLocation());
    private static JFrame frame;

    public MasterGui() throws IOException, ParseException, java.text.ParseException {

        frame = new JFrame("Lone Survivor");
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add (splashScreen);
        frame.pack();
        frame.setVisible (true);

        game.startGame();

        }

    public static void renderLocationFrame() throws IOException, ParseException, java.text.ParseException {

        frame.getContentPane().removeAll();
        frame.getContentPane().add(locationFrame);
        frame.revalidate();


    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

