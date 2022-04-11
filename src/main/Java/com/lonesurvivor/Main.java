package com.lonesurvivor;



import com.lonesurvivor.Views.MasterGui;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, ParseException, java.text.ParseException {

        MasterGui masterGui = new MasterGui();

        //TODO refactor versions
//        GameEngine game = new GameEngine();
//        GameEngine.getInstance().startGame();

//        GameEngine game = null;//create and init start of game
//        try {
//            game = new GameEngine();
//            LoneSurvivorBase StartPage = new LoneSurvivorBase(game);//create init and start UI page
//        } catch (IOException | ParseException e) {
//            System.out.println("Could not properly read file");
//        } catch (java.text.ParseException e) {
//            e.printStackTrace();
//        }
//        MusicClass music = new MusicClass(); // create and init Music Page
//       // MusicClass.audioFile();// calls method to start the music
//        Scanner scan = new Scanner(System.in);// IDK if we need this we dont use it???
//


        /*while(true) {
            System.out.println("Welcome to Lone Survivor, a text-based adventure game! ");
            System.out.println("Are you ready to play? (Y/N)"); //main - if Y, starting game, if N, re-loops to beginning
            String line = scan.nextLine();
            if (line.equalsIgnoreCase("Y")) {
                game.startGame();
            }
            else if (line.equalsIgnoreCase("N")) {
                System.out.println("Quitting now...");
                break;
            }

            else{
                System.out.println("Invalid Input!");
            }
        }*/
//        int width = 150;
//        int height = 30;
//
//        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
//        Graphics g = image.getGraphics();
//        g.setFont(new Font("SansSerif", Font.BOLD, 17));
//
//        Graphics2D g2 = (Graphics2D) g;
//        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
//        g2.drawString("LONE SURVIVOR", 10, 20);
//
//        for(int y=0; y<height; y++) {
//            StringBuilder builder = new StringBuilder();
//
//            for(int x=0; x<width; x++) {
//                builder.append(image.getRGB(x,y) == -16777216 ? " " : "@");
//            }
//            System.out.println(builder);
//        }
//

        //game.startGame();//???Game has to start before anything runs

    }
}
