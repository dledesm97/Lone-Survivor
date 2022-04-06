package com.lonesurvivor.Views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuiPlane extends JFrame implements ActionListener {

    //fields
    private JFrame frame = new JFrame();
    private JLabel label = new JLabel();
    private Container c = new Container();
   // ImageIcon image = new ImageIcon("airplane.jpg");
    private Font font = new Font("Verdana", Font.BOLD, 78);
    private JTextField textField;

    public GuiPlane() {

        //JLable that displays text
        label.setText("Plane");
       // label.setIcon(image);
        label.setBackground(Color.BLUE);
        label.setBounds(0, 0, 100, 50);
        label.setFont(font);
        c.add(label);

        //Jframe settings
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setSize(800, 600);
        frame.setVisible(true);
        frame.add(label);
        //frame.add(textField);

        //Jtext field
        textField = new JTextField();
        textField.setFont(new Font("Arial", Font.PLAIN, 15));
        textField.setSize(190, 20);
        textField.setLocation(200, 100);

        c.add(textField);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
