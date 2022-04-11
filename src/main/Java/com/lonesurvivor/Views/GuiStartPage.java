package com.lonesurvivor.Views;


import com.lonesurvivor.GameEngine.GameEngine;
import com.lonesurvivor.Models.MusicClass;
import org.json.simple.parser.ParseException;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GuiStartPage implements ActionListener {
    public static GuiStartPage gui;//Singleton to use global
    Clip clip = AudioSystem.getClip();//
    //Fields
    MusicClass musicClass ;
    private String greeting;
    LoneSurvivorBase loneSurvivorBase;
    //GameEngine gameEngine;
    private JFrame frame;
    private JPanel panel1;
    private JPanel panel2;
    private JButton startButton;
    private JButton musicButton;
    private Container container;

    Font font = new Font("Verdana", Font.BOLD,78);


    public GuiStartPage(GameEngine gameEngine) throws IOException, LineUnavailableException {
        gui = this;
//        this.gameEngine = gameEngine;//init gameEngine
        JFrame frame = new JFrame(); //create Jframe object
        frame.setSize(1100,900);//set window
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//set frame to open and close on exit
        frame.setTitle("LONE SURVIVOR");//set a title for the frame

        ImageIcon image;
        frame.add(new JLabel( new ImageIcon("src/main/resources/LoneSurvivor.png")));
        frame.setLayout(new FlowLayout());
        frame.setVisible(true);

        container = frame.getContentPane();
        container.setBackground(Color.DARK_GRAY);

        //Create title text
        createTitleText();

        panel2 = new JPanel();
        panel2.setBounds(300,400,1200,900);
        panel2.setBackground(Color.DARK_GRAY);
        container.add(panel2);

        //Start Button
        startGameButton();

        //Help button shows instructions when clicked
        createHelpButton();

    }

    private void createHelpButton(){
        //Help button shows instructions when clicked
        musicButton = new JButton("Music");
        musicButton.addActionListener(this);
        musicButton.setBackground(Color.WHITE);
        musicButton.setForeground(Color.blue);
        panel2.add(musicButton);
    }

    private void startGameButton(){
        //Start game button starts game
        startButton = new JButton("ENTER");
        startButton.addActionListener(this);
        startButton.setBackground(Color.WHITE);
        startButton.setForeground(Color.blue);
        panel2.add(startButton);
    }

    private void createTitleText(){
        panel1 = new JPanel();//then create Jpanel Object
        panel1.setBounds(50,100,700,150);//then set bounds of title panel
        panel1.setBackground(Color.DARK_GRAY);// set background color of panel
        JLabel jlabel1 = new JLabel("Lone Survivor");// create label
        jlabel1.setForeground(Color.ORANGE);//title to be put on the panel
        panel1.add(jlabel1);//add label to panel
        jlabel1.setFont(font);
        container.add(panel1);//add panel to container
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
            // frame.dispose();
            try {
                LoneSurvivorBase home = new LoneSurvivorBase();
                //
                // loneSurvivorBase.setVisible(true);
                GameEngine.getInstance().startGame();
            } catch (IOException | ParseException | java.text.ParseException ex) {
                ex.printStackTrace();
            }
//        } else if (e.getSource() == musicButton && MusicClass.audioFile()) {
//
//                clip.stop();
//
//
//        }else{
//            clip.start();
//            clip.getControls();
       }

    }
}
