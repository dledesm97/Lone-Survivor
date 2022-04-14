package com.lonesurvivor.GameEngine;

import com.lonesurvivor.Models.Item;
import com.lonesurvivor.Models.Location;
import com.lonesurvivor.Models.Player;
import com.lonesurvivor.Utils.JSONParserClass;
import com.lonesurvivor.Utils.TextParser;
import org.json.simple.parser.ParseException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static com.lonesurvivor.Views.LocationFrame.textDisplayGui;


public class GameEngine {

    private static GameEngine engine = null;
    private static final String LOCATION_PATH="json/PlaneCrash.json";

    private TextParser parser;
    private List<Location> locations;
    private List<String> command;
    public static int dayCount = 1;
    private boolean hasWon;

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

    public GameEngine() throws IOException, ParseException {
        // initializes the object and sets the locations list and default player location
        parser = new TextParser();
        locations = JSONParserClass.getInstance().locationParser(LOCATION_PATH);
        Player.getInstance().setLocations(locations);
        Player.getInstance().setPlayerLocation(locations.get(2));


    }

    /*NOTE: INITIALIZES GAME && RUNS THE GAME
        -sets default location of player
        -prints welcome message hardcode
        -while user has not won take player input using the playerInterface() method
        -checks whether won or not
     */

    public void startGame() throws IOException, ParseException, java.text.ParseException {

        while (!hasWon) {
            checkWin();
            dayTracker();
        }
    }


    public void dayTracker(){

    //for every 10 action points, a day pass and it get reset.
        if (Player.getInstance().getActionTracker() == 10){
            dayCount++;
            Player.getInstance().setActionTracker(0);
        }
    }



    public void checkWin() {
        if(Player.getInstance().getPlayerLocation() == locations.get(8)) {
            textDisplayGui("Congratulations , you won !!!");
                hasWon = true;
            }
        //TODO: either handle for rescued win on day 3 OR decide that they die
        //FIXME: edit gameinfo.json to reflect these results
        else if (dayCount == 4) {
            textDisplayGui("The trekking through uncharted forest takes its toll on you over 3 days and you succumb to your fatigue and injuries.\n You died. Thank you for playing.");
            System.exit(0);
        }

    }

        //redundant
//    public boolean validateWin(Location currentLocation, List<Location> locationList){
//        if (currentLocation.equals(locationList.get(8))) {
//            return true;
//        }
//        return false;
//    }


    /* NOTE:
        -prompts user to input a command
        -validates that user enter 2 word commands
        -if so then it passes that invokes commandProcessor() method to trigger each input case
     */
    public void playerInterface() {}

    public void handleInput(String input){// public method handleInput if you give it input from GUI (copy and paste from above)
        try {
            parser.InitialInput(input);

            if (parser.getValidCommand().size() == 2) {
                command = parser.getValidCommand();
                //processor.processCommand(command);
                commandProcessor();
            }
            //TODO update printed text to UI
            //checkWin();TODO ???
        } catch (IOException | ParseException e) {
            System.out.println(e.getMessage());
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
    }

    public void commandProcessor() throws IOException, ParseException, java.text.ParseException {

        //converted if statements to a switch. And moved commands to player class
        //move engine
        switch (command.get(0)) {
            case "go":
                try {
                    Player.getInstance().moveEngine(command.get(1));
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                break;
            case "get":
                //get engine
                Player.getInstance().getEngine(command.get(1));
                break;
            case "look":
                //look engine
                Player.getInstance().lookEngine(command.get(1));
                break;
            case "use":
                Player.getInstance().useEngine(command.get(1));
            case "quit":
                //quit engine
                Player.getInstance().quitEngine(command.get(1));
                break;
            case "help":
                //help engine
                Player.getInstance().helpEngine(command.get(1));
                break;
            case "attack":
                //conflict engine
                Player.getInstance().conflictResolutionEngine(command.get(0));
        }
    }
    public int getDayCount() {
        return dayCount;
    }

    public void setDayCount(int dayCount) {
        GameEngine.dayCount = dayCount;
    }
}
