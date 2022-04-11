package com.lonesurvivor.Views;

import com.lonesurvivor.GameEngine.GameEngine;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LoneSurvivorBase extends JFrame
        implements ActionListener {
    public static LoneSurvivorBase GUI;//singleton instance of class to use global
    GameEngine gameEngine;// init gameEngine in class
    //Fields
    List<String> message = new ArrayList<>();//Because the Json file is a List we create an ArrayList ot add
    private Container c;
    private JLabel title;
    private JTextField input;
    private JLabel display;
    private JLabel msg;
    private JTextPane input1;
    private JButton north;
    private JButton south;
    private JButton east;
    private JButton west;
    private JButton enter;
    private JButton submit;
    private JLabel file;
    private JPanel enterButton;
    private JTextPane commandFeedBack;
    private JTextPane viewMap;



    public LoneSurvivorBase(GameEngine gameEngine) throws IOException, ParseException, java.text.ParseException {//Lone Survivor Base Camp
        GUI = this; // init global singleton (ctor)
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
        title.setForeground(Color.WHITE);
        title.setSize(500, 90);
        title.setLocation(340, 30);
        c.add(title);
        title.setVisible(false);


        enterButton = new JPanel();//The JPanel for the splash page
        enterButton.setBounds(1,1,1200,900);
        enterButton.setBackground(Color.WHITE);
        enterButton.setVisible(true);

        input = new JTextField();//input field for player
        input.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 15));
        input.setSize(200, 40);
        input.setLocation(15, 790);
        c.add(input);
        input.setVisible(false);

        display = new JLabel("DISPLAY");//label text display
        display.setFont(new Font("Arial", Font.PLAIN, 20));
        display.setForeground(Color.WHITE);
        display.setSize(150, 100);
        display.setLocation(10, 475);
        c.add(display);
        display.setVisible(false);

        msg = new JLabel("PLAYER_MSG");//label text display
        msg.setFont(new Font("Arial", Font.PLAIN, 20));
        msg.setForeground(Color.WHITE);
        msg.setSize(150, 100);
        msg.setLocation(453, 520);
        c.add(msg);
        msg.setVisible(false);

        input1 = new JTextPane();
        input1.setFont(new Font("Arial", Font.PLAIN, 15));
        input1.setBackground(Color.DARK_GRAY);
        input1.setForeground(Color.RED);
        input1.setSize(356, 235);
        input1.setLocation(15, 550);
        //input1.setLineWrap(true);
        c.add(input1);
        input1.setVisible(false);


        north = new JButton("North");
        north.setFont(new Font("Arial", Font.PLAIN, 15));
        north.setForeground(Color.RED);
        north.setSize(100, 20);
        north.setLocation(985, 680);
        north.addActionListener(this);
        c.add(north);
        north.setVisible(false);

        south = new JButton("South");
        south.setFont(new Font("Arial", Font.PLAIN, 15));
        south.setForeground(Color.RED);
        south.setSize(100, 20);
        south.setLocation(985, 725);
        south.addActionListener(this);
        c.add(south);
        south.setVisible(false);

        west = new JButton("West");
        west.setFont(new Font("Arial", Font.PLAIN, 15));
        west.setForeground(Color.RED);
        west.setSize(100, 20);
        west.setLocation(939, 703);
        west.addActionListener(this);
        c.add(west);
        west.setVisible(false);

        east = new JButton("East");
        east.setFont(new Font("Arial", Font.PLAIN, 15));
        east.setForeground(Color.RED);
        east.setSize(100, 20);
        east.setLocation(1025, 703);
        east.addActionListener(this);
        c.add(east);
        east.setVisible(false);

        submit = new JButton("SUBMIT");
        submit.setFont(new Font("Arial", Font.PLAIN, 15));
        submit.setSize(80, 30);
        submit.setLayout(new BorderLayout());
        submit.setLocation(220, 795);
        submit.addActionListener(this);
        c.add(submit);
        submit.setVisible(false);

        file = new JLabel("File");
        file.setFont(new Font("Arial", Font.PLAIN, 20));
        file.setForeground(Color.WHITE);
        file.setSize(500, 25);
        file.setBackground(Color.BLACK);
        file.setLocation(10, 50);
        c.add(file);
        file.setVisible(false);

       //Splashpage
       splashPage();
       //CommandPrompts
        commandMsgReturn();
        //ViewMap
        viewMapPane();

        setVisible(true);
        gameEngine.startGame();
    }
    public void setText(String text){//Method to set the incoming text
        message.add(text);//Storing all messages in a arraylist of Strings


    }
    public void getMessages(){//void helper method that shows  messages TODO: If condition filter result to textpanes
        viewMap.setText(String.join("\n", message));//Message in Array List print ot GUI viewMap
        message.clear();// message clears old message when
    }

    public void  setMultipleText(String... text){// a varags to add text strings
        String joinText = String.join("\n", text);//variable to hold text and join it together
        setText(joinText);//call setText method to join input1 text

    }
    public void splashPage(){// Izzy would enter Splash Page file info here
        enter = new JButton("Enter");
        enter.setFont(new Font("Arial", Font.PLAIN, 15));
        enter.setSize(80, 30);
        enter.setForeground(Color.RED);
        enter.setLocation(220, 795);
        enter.addActionListener(this);
        enterButton.add(enter);
        c.add(enterButton);
        enter.setVisible(true);
        ImageIcon image;
        enterButton.add(new JLabel( new ImageIcon("src/main/resources/LoneSurvivor.png")));//SplashPage place holder file
        enterButton.setLayout(new FlowLayout());
        enterButton.setVisible(true);
        //enterButton.add(image);

    }
    public void commandMsgReturn(){
        commandFeedBack = new JTextPane();
        commandFeedBack.setFont(new Font("Arial", Font.PLAIN, 15));
        commandFeedBack.setBackground(Color.WHITE);
        commandFeedBack.setForeground(Color.RED);
        commandFeedBack.setSize(356, 235);
        commandFeedBack.setLocation(455, 590);
        c.add(commandFeedBack);
        commandFeedBack.setVisible(false);
    }
    public void viewMapPane(){//TODO : create label
        viewMap = new JTextPane();
        viewMap.setFont(new Font("Arial", Font.PLAIN, 15));
        viewMap.setBackground(Color.WHITE);
        viewMap.setForeground(Color.RED);
        viewMap.setSize(700, 335);
        viewMap.setLocation(260, 190);
        c.add(viewMap);
        viewMap.setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {// Set String Var  commmand to pass as switch var (each click has a command in it.)
        if(e.getSource()==enter){//if enter is 'clicked' set visible to true for gamming page and false for splash page.
            viewMap.setVisible(true);
            commandFeedBack.setVisible(true);
            enterButton.setVisible(false);
            enter.setVisible(false);
            file.setVisible(true);
            submit.setVisible(true);
            east.setVisible(true);
            west.setVisible(true);
            north.setVisible(true);
            south.setVisible(true);
            input1.setVisible(true);
            display.setVisible(true);
            input.setVisible(true);
            title.setVisible(true);
            msg.setVisible(true);
        }
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
