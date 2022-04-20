package com.lonesurvivor.GameEngine;

import com.lonesurvivor.Models.Location;
import com.lonesurvivor.Models.MusicClass;
import com.lonesurvivor.Models.Player;
import com.lonesurvivor.Utils.JSONParserClass;
import com.lonesurvivor.Utils.TextParser;
import org.json.simple.parser.ParseException;
import java.io.*;
import java.util.List;
import static com.lonesurvivor.Views.LocationFrame.textDisplayGui;

public class GameEngine {

    //singleton
    private static GameEngine engine = null;

    //constants
    private static final String LOCATION_PATH = "json/PlaneCrash.json";
    private final int MAX_HEALTH = 50;

    //field variables
    private TextParser parser;
    private List<Location> locations;
    private List<String> command;
    public static int dayCount = 1;
    private boolean hasWon = false;

    public static GameEngine getInstance(){
        if(engine == null){
            try{
                engine = new GameEngine();
            }catch (IOException | ParseException e){
                System.out.println(e.getMessage());
                System.exit(0);
            }
        }
        return engine;
    }

    // private ctor initializes the object and sets the locations list and default player location
    private GameEngine() throws IOException, ParseException {
        parser = new TextParser();
        locations = JSONParserClass.getInstance().locationGenerator(LOCATION_PATH);
        Player.getInstance().setHealth(MAX_HEALTH);
        Player.getInstance().setLocations(locations);
        Player.getInstance().setCurrentLocation(locations.get(2));
    }

    //public method that runs the game
    public boolean startGame() throws InterruptedException {
        dayTracker();
        checkWin(Player.getInstance().getCurrentLocation());
        if (hasWon){
            textDisplayGui("\nYou've entered the building and HOOORAY! You have regrouped with Clay, Izzy, Percell, and David.\n You are in good hands with them, they will take over from here!\nCONGRATULATIONS!! Thanks for playing!");
            Thread.sleep(3000);
            hasWon = true; //TODO CHECK IF THIS BROKE ANYTHING
        }
        return hasWon;
    }

    /*Tracks the Current Day's count
     for every 10 action points, a day passes & action points is reset.
     */
    public void dayTracker(){
        if (Player.getInstance().getActionTracker() >= 10){
            dayCount++;
            Player.getInstance().setActionTracker(0);
        }
    }

    //verifies if player has won the game
    public boolean checkWin(Location playerLocation) throws InterruptedException {
        if(playerLocation.getName().equals(locations.get(9).getName())) {
            return hasWon = true;
        } else if (dayCount == 4) {
            textDisplayGui("The trekking through uncharted forest takes its toll on you over 3 days and you succumb to your fatigue and injuries.\n You died. Better luck next time!");
            Thread.sleep(4000);
            System.exit(0);
        }
        return false;
    }

    // handles user input from GUI
    public void handleInput(String input){
        try {
            parser.InitialInput(input);
            if (parser.getValidCommand().size() == 2) {
                command = parser.getValidCommand();
                commandProcessor();
            }
        } catch (IOException | ParseException e) {
            System.out.println(e.getMessage());
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
    }

    //triggers valid player commands
    public void commandProcessor() throws IOException, ParseException, java.text.ParseException {
        switch (command.get(0)) {
            case "go":
                try {
                    Player.getInstance().moveEngine(command.get(1));
                    MusicClass.soundFx(command.get(0));
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                break;
            case "get":
                try {
                    Player.getInstance().getEngine(command.get(1));
                    MusicClass.soundFx(command.get(0));
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                break;
            case "look":
                try {
                    Player.getInstance().lookEngine(command.get(1));
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                break;
            case "use":
                try{
                    Player.getInstance().useEngine(command.get(1));
                }catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                break;
            case "quit":
                try{
                    Player.getInstance().quitEngine(command.get(1));
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                break;
            case "help":
                try{
                    Player.getInstance().helpEngine(command.get(1));
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                break;
            case "attack":
               try{
                   Player.getInstance().attackEngine(command.get(1));
                   MusicClass.soundFx(command.get(0));
               }catch (Exception e) {
                   System.out.println(e.getMessage());
               }
               break;
            case "talk":
                try{
                    Player.getInstance().talkEngine(command.get(1));
                }catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                break;
            default:

        }
    }

    public int getDayCount() {
        return dayCount;
    }
}
