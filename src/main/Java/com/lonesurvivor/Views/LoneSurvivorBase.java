package com.lonesurvivor.Views;

import com.lonesurvivor.GameEngine.GameEngine;
import org.json.simple.parser.ParseException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class LoneSurvivorBase extends JFrame
        implements ActionListener {
    public static LoneSurvivorBase GUI;//singleton instance of class to use global
    GameEngine gameEngine;// init gameEngine in class
    //Fields
    private Container c;
    private JLabel title;
    private JTextField input;
    private JLabel display;
    private JTextPane input1;
    private JButton north;
    private JButton south;
    private JButton east;
    private JButton west;
    private JButton reset;
    private JLabel file;
    private JTextPane commandFeedBack;


    public LoneSurvivorBase(GameEngine gameEngine) throws IOException, ParseException {//Lone Survivor Base Camp
        GUI = this; // init global singleton (ctor) is used
        this.gameEngine = gameEngine;//init gameEngine within class
        setTitle("Survivor");
        setBounds(300, 100, 1200, 900);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        c = getContentPane();//Container as base frame
        c.setBackground(Color.DARK_GRAY);
        c.setLayout(null);


        title = new JLabel("Lone Survivor");//Lable displaying name "crash site"
        title.setFont(new Font("Arial", Font.PLAIN, 75));
        title.setForeground(Color.ORANGE);
        title.setSize(500, 230);
        title.setLocation(340, 30);
        c.add(title);



        input = new JTextField();//input field for player
        input.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 15));
        input.setSize(200, 40);
        input.setLocation(15, 790);
        c.add(input);

        display = new JLabel("DISPLAY");//label text display
        display.setFont(new Font("Arial", Font.PLAIN, 20));
        display.setForeground(Color.ORANGE);
        display.setSize(150, 100);
        display.setLocation(10, 475);
        c.add(display);

        input1 = new JTextPane();
        input1.setFont(new Font("Arial", Font.PLAIN, 15));
        input1.setSize(356, 235);
        input1.setLocation(15, 550);
        //input1.setLineWrap(true);
        c.add(input1);
        setText("Hello");

        north = new JButton("North");
        north.setFont(new Font("Arial", Font.PLAIN, 15));
        north.setSize(100, 20);
        north.setLocation(645, 470);
        north.addActionListener(this);
        c.add(north);

        south = new JButton("South");
        south.setFont(new Font("Arial", Font.PLAIN, 15));
        south.setSize(100, 20);
        south.setLocation(645, 525);
        south.addActionListener(this);
        c.add(south);

        west = new JButton("West");
        west.setFont(new Font("Arial", Font.PLAIN, 15));
        west.setSize(100, 20);
        west.setLocation(539, 500);
        west.addActionListener(this);
        c.add(west);

        east = new JButton("East");
        east.setFont(new Font("Arial", Font.PLAIN, 15));
        east.setSize(100, 20);
        east.setLocation(750, 500);
        east.addActionListener(this);
        c.add(east);

        reset = new JButton("SUBMIT");
        reset.setFont(new Font("Arial", Font.PLAIN, 15));
        reset.setSize(80, 30);
        reset.setLayout(new BorderLayout());
        reset.setLocation(220, 795);
        reset.addActionListener(this);
        c.add(reset);

        file = new JLabel("File");
        file.setFont(new Font("Arial", Font.PLAIN, 20));
        file.setForeground(Color.ORANGE);
        file.setSize(500, 25);
        file.setBackground(Color.BLACK);
        file.setLocation(10, 50);
        c.add(file);



        setVisible(true);
       // gameEngine.startGame();
    }
    public void setText(String text){

        input1.setText(text);
    }

    public void setMultipleText(String... text){// a varags to add strings
        String joinText = String.join("\n", text);//variable to hold text and join it together
        setText(joinText);//call setText method to join input1 text
    }

    @Override
    public void actionPerformed(ActionEvent e) {// Set String Var  commmand to pass as switch var (each click has a command in it.)
        String command = e.getActionCommand();//returns the command string assc with the action ('click)
        switch(command){
            case "North":
                gameEngine.handleInput("go north");//calling the method handleInput() in GameEngine Class to parse the "clicks"
                break;
            case "South":
                gameEngine.handleInput("go south");
                break;
            case "East":
                gameEngine.handleInput("go east");
                break;
            case "West":
                gameEngine.handleInput("go west");
                break;
            case "SUBMIT":
                gameEngine.handleInput(input.getText());//

                break;
            default://Do nothing



        }

    }
}
