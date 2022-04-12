package com.lonesurvivor.Views;

import com.lonesurvivor.Models.Location;
import com.lonesurvivor.Models.Player;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    private Location currentLocation;
    private JSeparator separator;
    private JLabel imageLabel;
    private static String text;
    private JScrollPane jScroll;
    private JTextArea playerStatsDisplay;
    private JSeparator seperatorTwo;
    private JLabel playerStatsLabel;

    public LocationFrame() {

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
        String[] InventoryBoxItems = {"Item 1", "Item 2", "Item 3"};

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
        InventoryBox = new JList (InventoryBoxItems);
        separator = new JSeparator();
        jScroll = new JScrollPane(textDisplay);
        playerStatsDisplay = new JTextArea (5, 5);
        seperatorTwo = new JSeparator ();
        playerStatsLabel = new JLabel ("Player Stats");
        // formatting Images
        ImageIcon imageIcon = new ImageIcon("src/main/resources/3fazpri7n5t51.jpg"); // load the image to a imageIcon
        Image image = imageIcon.getImage(); // transform it
        Image newImg = image.getScaledInstance(590, 320,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        imageLabel = new JLabel( new ImageIcon(newImg));


        //set components properties

        textDisplay.setEnabled (false);
        textDisplay.setBackground(Color.lightGray);
        textDisplay.setBorder(new LineBorder(Color.BLACK));
        textDisplay.setLineWrap(true);
        textDisplay.setDisabledTextColor(Color.BLACK);
        playerStatsDisplay.setEnabled(false);
        playerStatsDisplay.setBackground(Color.lightGray);
        playerStatsDisplay.setBorder(new LineBorder(Color.BLACK));
        InventoryBox.setBackground(Color.lightGray);
        InventoryBox.setBorder(new LineBorder(Color.BLACK));
        enter.addActionListener(this);
        west.addActionListener(this);
        east.addActionListener(this);
        north.addActionListener(this);
        south.addActionListener(this);
        commandInput.addActionListener(this);




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
        add (InventoryBox);
        add (separator);
        add (imageLabel);
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
        InventoryBox.setBounds (445, 495, 165, 125);
        separator.setBounds (0, 470, 1160, 5);
        playerStatsDisplay.setBounds (15, 50, 595, 55);
        seperatorTwo.setBounds (0, 115, 1160, 5);
        playerStatsLabel.setBounds (15, 30, 100, 25);
        imageLabel.setBounds(15, 130, 595, 320);


        //setting up player data
        setPlayerStatsDisplay();
        locationMapper();

    }

    public void locationMapper(){
        textDisplayGui(Player.getInstance().getPlayerLocation().getDescription());
    }

//    public void renderFrame(){
//        JFrame frame = new JFrame("Lone Survivor");
//        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
//        frame.getContentPane().add (new LocationFrame(currentLocation));
//        frame.pack();
//        frame.setVisible (true);
//
//    }

    public static void textParser(){
        text = commandInput.getText();
        game.handleInput(text);
        commandInput.setText("");
    }

    public void locationParser(String direction){
        //player.move(direction)
        //setCurrentLocation(player.getPlayerLocation);

    }

    public static void textDisplayGui(String displayText){
        textDisplay.setText("");
        textDisplay.setText(displayText);
    }

    public void setPlayerStatsDisplay(){
        playerStatsDisplay.setText(Player.getInstance().getName() + "\n" + Player.getInstance().getHealth());
    }

    @Override
    public void actionPerformed(ActionEvent e) {

           String command = e.getActionCommand();
            switch(command){
                case "N":
                    System.out.println("testN");
                    updateUI();
                    break;
                case "S":
                    System.out.println("testS");
                    break;
                case "E":
                    System.out.println("testE");
                    break;
                case "W":
                    System.out.println("testW");
                    break;
                case "Enter":
                    textParser();
                    break;
                default://Do nothing
        }
        }

    public void setCurrentLocation(Location currentLocation) {
        this.currentLocation = currentLocation;
    }


}
