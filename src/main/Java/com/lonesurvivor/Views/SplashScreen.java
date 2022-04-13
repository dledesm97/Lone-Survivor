package com.lonesurvivor.Views;

import com.lonesurvivor.Utils.JSONParserClass;
import org.json.simple.parser.ParseException;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class SplashScreen extends JPanel implements ActionListener {
    private JButton jcomp1;
    private JLabel imageLabel;

    public SplashScreen() throws IOException {
        //construct components
        jcomp1 = new JButton ("Start");

        //adjust size and set layout
        setPreferredSize (new Dimension (626, 705));
        setLayout (null);
        setBackground(Color.LIGHT_GRAY);


        //set props for components
        InputStream imageFile = getFileFromResourceAsStream("images/lone_survivor.png");
        Image splashPageFile = ImageIO.read(imageFile);


        ImageIcon imageIcon = new ImageIcon(splashPageFile); // load the image to a imageIcon
        Image image = imageIcon.getImage(); // transform it
        Image newImg = image.getScaledInstance(626, 705,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        imageLabel = new JLabel( new ImageIcon(newImg)); //

        jcomp1.setOpaque(true);
        jcomp1.addActionListener(this);

        //add components
        add (jcomp1);
        add (imageLabel);


        //set component bounds (only needed by Absolute Positioning)
        jcomp1.setBounds (275, 345, 100, 20);
        imageLabel.setBounds (0, 0, 626, 705);
    }


//    public void renderFrame(){
//        JFrame frame = new JFrame("Lone Survivor");
//        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
//        frame.getContentPane().add (new SplashScreen());
//        frame.pack();
//        frame.setVisible (true);
//
//    }
    @Override
    public void actionPerformed(ActionEvent e) {

        String command = e.getActionCommand();
            if(e.getActionCommand().equals("Start")){
                try {
                    MasterGui.renderLocationFrame();
                } catch (IOException | ParseException | java.text.ParseException ex) {
                    ex.printStackTrace();
                }
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

