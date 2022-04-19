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

    //constants
    private static final String GREY_STYLING = "#3D3D3D";
    private static final String DARK_GREY_STYLING = "#333333";

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

    //separators
    private JSeparator bottomSeparator;
    private JSeparator topSeperator;

    private JLabel imageLabel;
    private JFrame frame;

    private JLabel playerStatsLabel;
    private JTextArea playerStatsDisplay;
    private JScrollPane jScroll;




    public LocationFrame() throws IOException {

        Font monospacedFont = new Font(Font.MONOSPACED,  Font.PLAIN, 13);

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
        north = new JButton("N");
        south = new JButton("S");
        east = new JButton("E");
        west = new JButton("W");

        //exit btns' styling
        north.setBackground(Color.WHITE);
        north.setFont(new Font(Font.MONOSPACED, Font.BOLD, 15));
        north.setForeground(Color.RED);
        north.setFocusPainted(false);
        north.setBorder(null);

        south.setBackground(Color.WHITE);
        south.setFont(new Font(Font.MONOSPACED, Font.BOLD, 15));
        south.setForeground(Color.RED);
        south.setFocusPainted(false);
        south.setBorder(null);;

        east.setBackground(Color.WHITE);
        east.setFont(new Font(Font.MONOSPACED, Font.BOLD, 15));
        east.setForeground(Color.RED);
        east.setFocusPainted(false);
        east.setBorder(null);

        west.setBackground(Color.WHITE);
        west.setFont(new Font(Font.MONOSPACED, Font.BOLD, 15));
        west.setForeground(Color.RED);
        west.setFocusPainted(false);
        west.setBorder(null);

        //player input command section
        enterComdsLabel = new JLabel ("Enter Commands");
        enterComdsLabel.setForeground(Color.WHITE);
        enterComdsLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 11));

        //enter btn styling
        enterBtn = new JButton ("ENTER");
        enterBtn.setBackground(Color.WHITE);
        enterBtn.setFont(new Font(Font.MONOSPACED, Font.BOLD, 13));
        enterBtn.setForeground(Color.RED);
        enterBtn.setFocusPainted(false);
        enterBtn.setBorder(null);
        commandInput = new JTextField (5);
        commandInput.setBackground(Color.decode(DARK_GREY_STYLING));
        commandInput.setForeground(Color.white);
        commandInput.setBorder(new LineBorder(Color.decode(GREY_STYLING)));
        commandInput.setCaretColor(Color.WHITE);
        commandInput.setFont(new Font(Font.MONOSPACED, Font.ITALIC, 10));

        //text display properties
        textDisplay = new JTextArea (10, 5);
        textDisplay.setEnabled (false);
        textDisplay.setBackground(Color.decode(DARK_GREY_STYLING));
        textDisplay.setDisabledTextColor(Color.WHITE);
//        textDisplay.setBorder(new LineBorder(Color.decode(GREY_STYLING), 8));
        textDisplay.setFont(monospacedFont);
        textDisplay.setLineWrap(true);
        jScroll = new JScrollPane(textDisplay); //enable scrollable text-box
        jScroll.setBorder(new LineBorder(Color.decode(GREY_STYLING)));

        //separator --styling-- has 2 default colors to override
        bottomSeparator = new JSeparator();
        bottomSeparator.setForeground(Color.decode(GREY_STYLING));//top line color
        bottomSeparator.setBackground(Color.decode(GREY_STYLING).brighter()); //bottom line color

        //player's status
        playerStatsLabel = new JLabel ();
        playerStatsDisplay = new JTextArea (5, 5);
        playerStatsDisplay.setEnabled(false);
        playerStatsDisplay.setBackground(Color.decode(DARK_GREY_STYLING));
        playerStatsDisplay.setBorder(new LineBorder(Color.decode(GREY_STYLING)));
        playerStatsDisplay.setFont(monospacedFont);
        playerStatsDisplay.setDisabledTextColor(Color.WHITE);

        //top separator styling
        topSeperator = new JSeparator ();
        topSeperator.setForeground(Color.decode(GREY_STYLING));
        topSeperator.setBackground(Color.decode(GREY_STYLING).brighter());

        //btn's action listeners
        enterBtn.addActionListener(this);
        north.addActionListener(this);
        south.addActionListener(this);
        east.addActionListener(this);
        west.addActionListener(this);
        commandInput.addActionListener(this);
        soundItem.addActionListener(this);

        //frame size & layout
        setPreferredSize(new Dimension(626, 705));
        setLayout(null);
        setBackground(Color.decode("#292929"));

        //left-hand menu component
        add(menuBar);

        //exits component
        add(north);
        add(south);
        add(west);
        add(east);

        //userinput component
        add(enterComdsLabel);
        add(enterBtn);
        add(commandInput);

        add(bottomSeparator);

        add(jScroll);
        add(topSeperator);
        add(playerStatsDisplay);
        add(playerStatsLabel);

        //set component bounds (only needed by Absolute Positioning)
        north.setBounds(500, 625, 55, 25);
        south.setBounds(500, 675, 55, 25);
        east.setBounds(555, 650, 55, 25);
        menuBar.setBounds(0, 0, 1160, 30);
        west.setBounds(445, 650, 55, 25);
        jScroll.setBounds(15, 495, 410, 125);
        commandInput.setBounds(20, 650, 205, 30);
        enterComdsLabel.setBounds(20, 625, 100, 25);
        enterBtn.setBounds(245, 650, 80, 30);
//        InventoryBox.setBounds (445, 495, 165, 125);
        bottomSeparator.setBounds(0, 470, 1160, 5);
        playerStatsDisplay.setBounds(15, 50, 595, 55);
        topSeperator.setBounds(0, 115, 1160, 5);
        playerStatsLabel.setBounds(15, 30, 100, 25);

        //setting up player data
        setImageLabel();
        setInventoryBox();
        setPlayerStatsDisplay();
        locationMapper();

    }

    public void setImageLabel() throws IOException {
        if(imageLabel == null) {
            InputStream imageFile = getFileFromResourceAsStream(Player.getInstance().getCurrentLocation().getImage());
            Image locationImageFile = ImageIO.read(imageFile);
            ImageIcon imageIcon = new ImageIcon(locationImageFile); // load the image to a imageIcon
            Image image = imageIcon.getImage(); // transform it
            Image newImg = image.getScaledInstance(590, 320, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
            imageLabel = new JLabel(new ImageIcon(newImg));
            add(imageLabel);
            imageLabel.setBounds(15, 130, 595, 320);
        } else {
            remove(imageLabel);
            InputStream imageFile = getFileFromResourceAsStream(Player.getInstance().getCurrentLocation().getImage());
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
        inventoryBox.setBackground(Color.decode(DARK_GREY_STYLING));
        inventoryBox.setBorder(new LineBorder(Color.decode(GREY_STYLING)));
        inventoryBox.setBounds(445, 495, 165, 125);
        inventoryBox.setForeground(Color.WHITE);
        inventoryBox.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        add(inventoryBox);
        revalidate();
        repaint();
    }
    public void locationMapper(){
        textDisplayGui(Player.getInstance().getCurrentLocation().getDescription());
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
        playerStatsDisplay.setText("\n  Day: " + GameEngine.getInstance().getDayCount() + "   Health: " + Player.getInstance().getHealth()
            + "   Location: " + Player.getInstance().getCurrentLocation().getName().toUpperCase());
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
                case "ENTER":
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
