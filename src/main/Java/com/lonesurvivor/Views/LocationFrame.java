package com.lonesurvivor.Views;

import com.lonesurvivor.GameEngine.GameEngine;
import com.lonesurvivor.Models.MusicClass;
import com.lonesurvivor.Models.Player;
import com.lonesurvivor.Utils.JSONParserClass;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;

import static com.lonesurvivor.Views.MasterGui.game;


public class LocationFrame extends JPanel  implements ActionListener{

    private static JTextArea textDisplay;
    private static JTextField commandInput;
    private static String text;

    //exits
    private JButton north;
    private JButton south;
    private JButton east;
    private JButton west;

    //left-hand drop down menu
    private JMenuBar menuBar;

    //components for user command input
    private JLabel enterComdsLabel;
    private JButton enterBtn;

    //players inventory
    private JList inventoryBox;
    private JSeparator separator;

    private JLabel imageLabel;

    private JScrollPane jScroll;
    private JTextArea playerStatsDisplay;
    private JSeparator seperatorTwo;
    private JLabel playerStatsLabel;
    private JFrame frame;

    public LocationFrame() throws IOException {

        //File dropdown menu
        JMenu fileMenu = new JMenu ("File"); //1st dropdown header
        JMenuItem exitItem = new JMenuItem ("Exit");
        fileMenu.add(exitItem);

        //Help dropdown menu
        JMenu helpMenu = new JMenu ("Help"); //2nd dropdown header
        JMenuItem commandsItem = new JMenuItem ("Commands");
        JMenuItem soundItem = new JMenuItem ("Sound");
        JMenuItem aboutItem = new JMenuItem ("About");
        helpMenu.add(commandsItem);
        helpMenu.add(soundItem);
        helpMenu.add(aboutItem);

        //add the dropdown menus to the main menu
        menuBar = new JMenuBar();
        menuBar.add (fileMenu);
        menuBar.add (helpMenu);

        //construct exit --directional-- components
        north = new JButton ("N");
        south = new JButton ("S");
        east = new JButton ("E");
        west = new JButton ("W");

        //player input command section
        enterComdsLabel = new JLabel ("Enter Commands");
        enterBtn = new JButton ("Enter");
        commandInput = new JTextField (5);

        //text display properties
        textDisplay = new JTextArea (10, 5);
        textDisplay.setEnabled (false);
        textDisplay.setBackground(Color.lightGray);
        textDisplay.setDisabledTextColor(Color.BLACK);
        textDisplay.setBorder(new LineBorder(Color.BLACK));
        textDisplay.setLineWrap(true);
        jScroll = new JScrollPane(textDisplay); //enable scrollable text-box
        separator = new JSeparator();

        //player's status
        playerStatsLabel = new JLabel ("Player Stats");
        playerStatsDisplay = new JTextArea (5, 5);
        playerStatsDisplay.setEnabled(false);
        playerStatsDisplay.setBackground(Color.lightGray);
        playerStatsDisplay.setBorder(new LineBorder(Color.BLACK));
        playerStatsDisplay.setDisabledTextColor(Color.BLACK);
        seperatorTwo = new JSeparator ();


        //btn's action listeners
        enterBtn.addActionListener(this);
        north.addActionListener(this);
        south.addActionListener(this);
        east.addActionListener(this);
        west.addActionListener(this);
        commandInput.addActionListener(this);
        soundItem.addActionListener(this);

        //adjust size and set layout
        setPreferredSize(new Dimension(626, 705));
        setLayout(null);
        setBackground(Color.LIGHT_GRAY);

        //left-hand menu component
        add (menuBar);

        //exits component
        add (north);
        add (south);
        add (west);
        add (east);

        //userinput component
        add (enterComdsLabel);
        add (enterBtn);
        add (commandInput);

        add (separator);

        add (jScroll);
        add (seperatorTwo);
        add (playerStatsDisplay);
        add (playerStatsLabel);

        //set component bounds (only needed by Absolute Positioning)
        north.setBounds (500, 625, 55, 25);
        south.setBounds (500, 675, 55, 25);
        east.setBounds (555, 650, 55, 25);
        menuBar.setBounds (0, 0, 1160, 30);
        west.setBounds (445, 650, 55, 25);
        jScroll.setBounds(15, 495, 410, 125);
        commandInput.setBounds (20, 650, 205, 30);
        enterComdsLabel.setBounds (20, 625, 100, 25);
        enterBtn.setBounds (245, 650, 80, 30);
//        InventoryBox.setBounds (445, 495, 165, 125);
        separator.setBounds (0, 470, 1160, 5);
        playerStatsDisplay.setBounds (15, 50, 595, 55);
        seperatorTwo.setBounds (0, 115, 1160, 5);
        playerStatsLabel.setBounds (15, 30, 100, 25);
//        imageLabel.setBounds(15, 130, 595, 320);


        //setting up player data
        setImageLabel();
        setInventoryBox();
        setPlayerStatsDisplay();
        locationMapper();

    }

    public void setImageLabel() throws IOException {
        if(imageLabel == null) {
            InputStream imageFile = getFileFromResourceAsStream(Player.getInstance().getPlayerLocation().getImage());
            Image locationImageFile = ImageIO.read(imageFile);
            ImageIcon imageIcon = new ImageIcon(locationImageFile); // load the image to a imageIcon
            Image image = imageIcon.getImage(); // transform it
            Image newImg = image.getScaledInstance(590, 320, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
            imageLabel = new JLabel(new ImageIcon(newImg));
            add(imageLabel);
            imageLabel.setBounds(15, 130, 595, 320);
        } else {
            remove(imageLabel);
            InputStream imageFile = getFileFromResourceAsStream(Player.getInstance().getPlayerLocation().getImage());
            Image locationImageFile = ImageIO.read(imageFile);
            ImageIcon imageIcon = new ImageIcon(locationImageFile); // load the image to a imageIcon

            Image image = imageIcon.getImage(); // transform it
            Image newImg = image.getScaledInstance(590, 320, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
            imageLabel = new JLabel(new ImageIcon(newImg));

            add(imageLabel);
            imageLabel.setBounds(15, 130, 595, 320);
            setPlayerStatsDisplay();
            locationMapper();
        }
        revalidate();
        repaint();
    }

    public void setInventoryBox(){
        if (inventoryBox != null) {
            remove(inventoryBox);
        }
        inventoryBox = new JList(Player.getInstance().getItems().toArray());
        inventoryBox.setBackground(Color.lightGray);
        inventoryBox.setBorder(new LineBorder(Color.BLACK));
        inventoryBox.setBounds(445, 495, 165, 125);
        add(inventoryBox);
        revalidate();
        repaint();
    }
    public void locationMapper(){
        textDisplayGui(Player.getInstance().getPlayerLocation().getDescription());
    }

    //parses text input in GUI
    public static void textParser(){
        text = commandInput.getText();
        game.handleInput(text);
        commandInput.setText("");
    }

    //prints text to GUI Text-Box
    public static void textDisplayGui(String displayText){
        textDisplay.setText("");
        textDisplay.setText(displayText);
    }

    //displays player status in GUI
    public void setPlayerStatsDisplay(){
        playerStatsDisplay.setText("  Day: " + GameEngine.getInstance().getDayCount() + "\tHealth: " + Player.getInstance().getHealth());
    }

    private void renderSoundFrame() {
        MusicClass musicClass = new MusicClass();
        frame = new JFrame("Sound Controls");
        frame.setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().add (musicClass);
        frame.pack();
        frame.setVisible (true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == commandInput) {
            textParser();
        }

        String command = e.getActionCommand();
        switch(command){
                case "N":
                    game.handleInput("go north");
                    break;
                case "S":
                    game.handleInput("go south");
                    break;
                case "E":
                    game.handleInput("go east");
                    break;
                case "W":
                    game.handleInput("go west");
                    break;
                case "Enter":
                    textParser();
                    break;
                case "Sound":
                    renderSoundFrame();
                default://Do nothing
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
