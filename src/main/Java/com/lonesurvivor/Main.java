package com.lonesurvivor;

import com.lonesurvivor.GameEngine.GameEngine;
import org.json.simple.parser.ParseException;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, ParseException {
        Scanner scan = new Scanner(System.in); //TODO: NEVER USED

        //TODO: MAY BE SINGLETON
        GameEngine game = new GameEngine();

        //TODO: CAN BE CONSTANTS
        int width = 150;
        int height = 30;


        //TODO: CAN BE EXTRACTED TO OWN CLASS
        //NOTE: LONE SURVIVOR LOGO IS A GRAPHIC that is drawn dynamically
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        g.setFont(new Font("SansSerif", Font.BOLD, 17));
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.drawString("LONE SURVIVOR", 10, 20);


        for(int y=0; y<height; y++) {
            StringBuilder builder = new StringBuilder();

            for(int x=0; x<width; x++) {
                builder.append(image.getRGB(x,y) == -16777216 ? " " : "@");
            }
            System.out.println(builder);
        }

        //STARTS OUR GAME USING GameEngine CLASS
        game.startGame();

    }
}
