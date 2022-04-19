package com.lonesurvivor.Views;

import com.lonesurvivor.Utils.JSONParserClass;
import org.json.simple.parser.ParseException;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;

public class SplashScreen extends JPanel implements ActionListener {

    private JButton startBtn;
    private JLabel imageLabel;

    public SplashScreen() throws IOException {
        //construct components
        startBtn = new JButton ("START");

        //adjust size and set layout of frame
        setPreferredSize (new Dimension (626, 705));
        setLayout (null);
        setBackground(Color.LIGHT_GRAY);

        //set props for components -- splash image
        InputStream imageFile = getFileFromResourceAsStream("images/lone_survivor.png");
        Image splashPageFile = ImageIO.read(imageFile);

        ImageIcon imageIcon = new ImageIcon(splashPageFile); // load the image to a imageIcon
        Image image = imageIcon.getImage(); // transform it
        Image newImg = image.getScaledInstance(626, 705,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        imageLabel = new JLabel( new ImageIcon(newImg)); //

        //button styling
        Font  f4  = new Font(Font.MONOSPACED,  Font.BOLD, 13);
        startBtn.setOpaque(true);
        startBtn.setForeground(Color.RED);
        startBtn.setFont(f4);
        startBtn.setFocusPainted(false);
        startBtn.setBackground(Color.decode("#F7F7F7"));
        startBtn.setBorder(null);
        startBtn.addActionListener(this);

        //add components
        add(startBtn);
        add(imageLabel);

        //set component bounds (only needed by Absolute Positioning)
        startBtn.setBounds (265, 465, 95, 28);
        imageLabel.setBounds (0, 0, 626, 705);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
            if(e.getActionCommand().equalsIgnoreCase("start")){
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

