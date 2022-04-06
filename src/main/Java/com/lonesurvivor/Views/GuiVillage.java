package com.lonesurvivor.Views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuiVillage extends JFrame implements ActionListener {

    //Fields
    //fields
    JFrame frame = new JFrame();
    JLabel label = new JLabel();
    ImageIcon image = new ImageIcon("plogo.png");
    Font font = new Font("Verdana", Font.BOLD,78);

    public GuiVillage(){

        //JLable that displays text
        label.setText("Village");
        label.setIcon(image);
        label.setBounds(0,0,100,50);
        label.setFont(font);

        //Jframe settings
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());
        frame.setSize(800,600);
        frame.setVisible(true);
        frame.add(label);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
