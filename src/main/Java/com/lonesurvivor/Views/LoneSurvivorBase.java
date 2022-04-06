package com.lonesurvivor.Views;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoneSurvivorBase extends JFrame
        implements ActionListener {


    //Fields
    private Container c;
    private JLabel title;
    private JTextField input;
    private JLabel display;
    private JTextArea input1;
    private JButton north;
    private JButton south;
    private JButton east;
    private JButton west;
    private JButton reset;
    private JLabel file;


    public LoneSurvivorBase() {
        setTitle("HomeBase");
        setBounds(300, 90, 900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(true);

        c = getContentPane();
        c.setBackground(Color.CYAN);
        c.setLayout(null);

        title = new JLabel("Crash Site");
        title.setFont(new Font("Arial", Font.PLAIN, 40));
        title.setSize(300, 30);
        title.setLocation(300, 30);
        c.add(title);



        input = new JTextField();
        input.setFont(new Font("Arial", Font.PLAIN, 15));
        input.setSize(200, 40);
        input.setLocation(15, 525);
        c.add(input);

        display = new JLabel("Display");
        display.setFont(new Font("Arial", Font.PLAIN, 20));
        display.setSize(150, 100);
        display.setLocation(10, 385);
        c.add(display);

        input1 = new JTextArea();
        input1.setFont(new Font("Arial", Font.PLAIN, 15));
        input1.setSize(200, 75);
        input1.setLocation(15, 450);
        input1.setLineWrap(true);
        c.add(input1);

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
        reset.setSize(70, 20);
        reset.setLayout(new BorderLayout());
        reset.setLocation(220, 540);
        reset.addActionListener(this);
        c.add(reset);

        file = new JLabel("File");
        file.setFont(new Font("Arial", Font.PLAIN, 20));
        file.setSize(500, 25);
        file.setBackground(Color.BLACK);
        file.setLocation(10, 50);
        c.add(file);



        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
