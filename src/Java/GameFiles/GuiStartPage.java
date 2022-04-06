package Java.GameFiles;


import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class GuiStartPage implements ActionListener {
    public static GuiStartPage gui;//Singleton

    //Fields
    private String greeting;
    GameEngine gameEngine;
    private JFrame frame;
    private JPanel panel1;
    private JPanel panel2;
    private JButton jbutton1;
    private JButton jbutton2;
    Container container;
    JOptionPane jOption = new JOptionPane();
    JPanel textPanel =  new JPanel();
    JTextArea textArea = new JTextArea();
    Font font = new Font("Verdana", Font.BOLD,78);


    public GuiStartPage() throws IOException {
        gui = this;
        JFrame frame = new JFrame(); //create Jframe object
        frame.setSize(800,600);//set window
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//set frame to open and close on exit
        frame.setTitle("LONE SURVIVOR");//set a title for the frame
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);


        textPanel.setBackground(Color.BLACK);
        textPanel.setForeground(Color.WHITE);
        textArea.setBackground(Color.BLACK);
        textArea.setForeground(Color.WHITE);
        textArea.setText("Welcome to Lone Survivor");



        container = frame.getContentPane();

        //Create title text
        createTitleText();

        panel2 = new JPanel();
        panel2.setBounds(300,400,200,100);
        //panel2.setBackground(Color.black);
        container.add(panel2);

        textPanel.setBounds(400,400,200,400);
        container.add(textPanel);
        textPanel.add(textArea);

        //Start Button
        startGameButton();

        //Help button shows instructions when clicked
        createHelpButton();

//        JOptionPane jp = new JOptionPane();
//        greeting = JOptionPane.showInputDialog("Welcome to Lone Survivor: Enter your Name");




    }
    public void setText(String text){

        textArea.setText(text);
    }
    public void setMultipleText(String... text){// a varags to add strings
        String joinText = String.join("\n", text);//take everything and join it together
        setText(joinText);// join text
    }

    private void createHelpButton(){
        //Help button shows instructions when clicked
        jbutton2 = new JButton("HELP");
        jbutton2.addActionListener(this);
        jbutton2.setBackground(Color.WHITE);
        jbutton2.setForeground(Color.blue);
        panel2.add(jbutton2);
    }

    private void startGameButton(){
        //Start game button starts game
        jbutton1 = new JButton("START");
        jbutton1.addActionListener(this);
        jbutton1.setBackground(Color.WHITE);
        jbutton1.setForeground(Color.blue);
        panel2.add(jbutton1);
    }

    private void createTitleText(){
        panel1 = new JPanel();//then create Jpanel Object
        panel1.setBounds(50,100,700,150);//then set bounds of title panel
        panel1.setBackground(Color.BLACK);// set background color of panel
        JLabel jlabel1 = new JLabel("Lone Survivor");// create label
        jlabel1.setForeground(Color.WHITE);//title to be put on the panel
        panel1.add(jlabel1);//add label to panel
        jlabel1.setFont(font);
        container.add(panel1);//add panel to container
    }

    public void startGame(){
        jbutton1.setVisible(false);
        jbutton2.setVisible(false);
    }


    public static void guiShow() throws IOException, ParseException {


    }

    @Override
    public void actionPerformed(ActionEvent e) {
       if(e.getSource()== jbutton1){
           //frame.dispose();
           GuiPlane plane = new GuiPlane();
       }
       else if(e.getSource()==jbutton2){

           JOptionPane.showMessageDialog(null," help","Help Commands", JOptionPane.INFORMATION_MESSAGE);

       }

     }

}
