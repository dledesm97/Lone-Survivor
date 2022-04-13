package com.lonesurvivor.Views;

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
    
    private JButton north;
    private JButton south;
    private JButton west;
    private JMenuBar menuBar;
    private JButton east;
    private static JTextArea textDisplay;
    private static JTextField commandInput;
    private JLabel jcomp8;
    private JButton enter;
    private JList InventoryBox;
    private JSeparator separator;
    private JLabel imageLabel;
    private static String text;
    private JScrollPane jScroll;
    private JTextArea playerStatsDisplay;
    private JSeparator seperatorTwo;
    private JLabel playerStatsLabel;
    private JFrame frame;

    public LocationFrame() throws IOException {

        //construct preComponents
        JMenu fileMenu = new JMenu ("File");
        JMenuItem exitItem = new JMenuItem ("Exit");
        fileMenu.add (exitItem);
        JMenu helpMenu = new JMenu ("Help");
        JMenuItem commandsItem = new JMenuItem ("Commands");
        helpMenu.add (commandsItem);
        JMenuItem soundItem = new JMenuItem ("Sound");
        helpMenu.add (soundItem);
        JMenuItem aboutItem = new JMenuItem ("About");
        helpMenu.add (aboutItem);
        //String[] InventoryBoxItems = {"Item 1", "Item 2", "Item 3"};

        //construct components
        north = new JButton ("N");
        south = new JButton ("S");
        west = new JButton ("E"); //FIXME Swap the directions
        menuBar = new JMenuBar();
        menuBar.add (fileMenu);
        menuBar.add (helpMenu);
        east = new JButton ("W"); //FIXME Swap the directions
        textDisplay = new JTextArea (10, 5);
        commandInput = new JTextField (5);
        jcomp8 = new JLabel ("Enter Commands");
        enter = new JButton ("Enter");
        separator = new JSeparator();
        jScroll = new JScrollPane(textDisplay);
        playerStatsDisplay = new JTextArea (5, 5);
        seperatorTwo = new JSeparator ();
        playerStatsLabel = new JLabel ("Player Stats");
        // formatting Images
//        InputStream imageFile = getFileFromResourceAsStream(Player.getInstance().getPlayerLocation().getImage());
//        System.out.println(Player.getInstance().getPlayerLocation().toString());
//        Image locationImageFile = ImageIO.read(imageFile);
//        ImageIcon imageIcon = new ImageIcon(locationImageFile); // load the image to a imageIcon
//        Image image = imageIcon.getImage(); // transform it
//        Image newImg = image.getScaledInstance(590, 320,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        //imageLabel = new JLabel();


        //set components properties

        textDisplay.setEnabled (false);
        textDisplay.setBackground(Color.lightGray);
        textDisplay.setBorder(new LineBorder(Color.BLACK));
        textDisplay.setLineWrap(true);
        textDisplay.setDisabledTextColor(Color.BLACK);
        playerStatsDisplay.setEnabled(false);
        playerStatsDisplay.setBackground(Color.lightGray);
        playerStatsDisplay.setBorder(new LineBorder(Color.BLACK));
//        InventoryBox.setBackground(Color.lightGray);
//        InventoryBox.setBorder(new LineBorder(Color.BLACK));
        enter.addActionListener(this);
        west.addActionListener(this);
        east.addActionListener(this);
        north.addActionListener(this);
        south.addActionListener(this);
        commandInput.addActionListener(this);
        soundItem.addActionListener(this);




        //adjust size and set layout
        setPreferredSize (new Dimension (626, 705));
        setLayout (null);
        setBackground(Color.LIGHT_GRAY);

        //add components
        add (north);
        add (south);
        add (west);
        add (menuBar);
        add (east);
        add (commandInput);
        add (jcomp8);
        add (enter);
       // add (InventoryBox);
        add (separator);
       // add (imageLabel);
        add (jScroll);
        add (seperatorTwo);
        add (playerStatsDisplay);
        add (playerStatsLabel);

        //set component bounds (only needed by Absolute Positioning)
        north.setBounds (500, 625, 55, 25);
        south.setBounds (500, 675, 55, 25);
        west.setBounds (555, 650, 55, 25);
        menuBar.setBounds (0, 0, 1160, 30);
        east.setBounds (445, 650, 55, 25);
        jScroll.setBounds(15, 495, 410, 125);
        commandInput.setBounds (20, 650, 205, 30);
        jcomp8.setBounds (20, 625, 100, 25);
        enter.setBounds (245, 650, 80, 30);
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
            System.out.println(Player.getInstance().getPlayerLocation().toString());
            Image locationImageFile = ImageIO.read(imageFile);
            ImageIcon imageIcon = new ImageIcon(locationImageFile); // load the image to a imageIcon
            Image image = imageIcon.getImage(); // transform it
            Image newImg = image.getScaledInstance(590, 320, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
            imageLabel = new JLabel(new ImageIcon(newImg));
            add(imageLabel);
            imageLabel.setBounds(15, 130, 595, 320);
        }
        else {
            remove(imageLabel);
            InputStream imageFile = getFileFromResourceAsStream(Player.getInstance().getPlayerLocation().getImage());
            System.out.println(Player.getInstance().getPlayerLocation().toString());
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
        if (InventoryBox != null) {
            remove(InventoryBox);
        }
        InventoryBox = new JList(Player.getInstance().getItems().toArray());
        InventoryBox.setBackground(Color.lightGray);
        InventoryBox.setBorder(new LineBorder(Color.BLACK));
        InventoryBox.setBounds(445, 495, 165, 125);
        add(InventoryBox);
        revalidate();
        repaint();
    }
    public void locationMapper(){
        textDisplayGui(Player.getInstance().getPlayerLocation().getDescription());
    }


    public static void textParser(){
        text = commandInput.getText();
        game.handleInput(text);
        commandInput.setText("");
    }


    public static void textDisplayGui(String displayText){
        textDisplay.setText("");
        textDisplay.setText(displayText);
    }

    public void setPlayerStatsDisplay(){
        playerStatsDisplay.setText(Player.getInstance().getName() + "\n" + Player.getInstance().getHealth());
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
