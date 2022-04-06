package com.lonesurvivor.GameEngine;

import com.lonesurvivor.Models.Location;
import com.lonesurvivor.Models.Player;
import com.lonesurvivor.Utils.JSONParserClass;
import com.lonesurvivor.Utils.TextParser;
import com.lonesurvivor.Views.GuiStartPage;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.List;
import java.util.Map;

/**
 * Starts and initializes game and keeps it running, the "heart" of the program
 */
public class GameEngine {

    private TextParser parser;
    //private CommandProcessor processor;
    private JSONParserClass jsonParserClass;
    //private GameLogic logic;
    private BufferedReader in; //TODO: Never used
    //private JSONParser jsonParser;
    //private FileReader reader;
    //private JSONArray file;
    private List<Location> locations;
    //private List<Location> outsides;
    //private Location location;
    private Player player;
    private List<String> command;
    private String input;
    private Location playerLocation;
    private double randNum;
    private boolean hasWon;
    public static int dayCount;


    public GameEngine() throws IOException, ParseException {
        parser = new TextParser();
        //processor = new CommandProcessor();
        jsonParserClass = new JSONParserClass();
        //logic = new GameLogic();
        //locations = new ArrayList<>();
        in = new BufferedReader(new InputStreamReader(System.in));
        locations = jsonParserClass.locationParser();
        //outsides = jsonParserClass.outsideParser();
        //jsonParser = new JSONParser();
        //reader = new FileReader("src/Java/External_Files/location.json");
        //file = (JSONArray) jsonParser.parse(reader);
        player = new Player("John Doe", locations);
        setDayCount(1);
        //randNum = Math.random();
        hasWon = false; //better name could be hasWon
    }

    /*NOTE: INITIALIZES GAME && RUNS THE GAME
        -sets default location of player
        -prints welcome message hardcode
        -while user has not won take player input using the playerInterface() method
        -checks whether won or not
     */
    public void startGame() throws IOException, ParseException  {
        player.setPlayerLocation(locations.get(2));
        playerLocation = player.getPlayerLocation();
//        System.out.println("Welcome to Lone Survivor, a text-based adventure game! ");
//        System.out.println("You were a passenger on a plane that crash landed into a forest in the middle of nowhere.");
//        System.out.println("As you awaken from unconsciousness, you quickly realize you are the only survivor aboard the crash.");
//        System.out.println("You have three days to make it back to civilization or survive until rescue. Good luck.");
//        System.out.println("******************************************************");
        //GuiClass.gui.startGame();
        GuiStartPage.gui.setMultipleText("Welcome to Lone Survivor, a text-based adventure game! ",
                "You were a passenger on a plane that crash landed into a forest in the middle of nowhere.",
                "As you awaken from unconsciousness, you quickly realize you are the only survivor aboard the crash.",
                "You have three days to make it back to civilization or survive until rescue. Good luck.",
                "******************************************************");

        //Welcome message of the game
//        System.out.println("VERSION NEW!!!!!!!!!!!!\ngit ");
//        System.out.println("Welcome to Lone Survivor, a text-based adventure game! ");
//        System.out.println("You were a passenger on a plane that crash landed into a forest in the middle of nowhere.");
//        System.out.println("As you awaken from unconsciousness, you quickly realize you are the only survivor aboard the crash.");
//        System.out.println("You have three days to make it back to civilization or survive until rescue. Good luck.");
//        System.out.println("******************************************************");

        while (!hasWon) {
            playerInterface();
            checkWin();
        }
    }

    public void checkWin() {
        if (playerLocation.equals(locations.get(8))) {
            System.out.println(playerLocation.getDescription());
            System.out.println("Congratulations and thank you for playing.");
            hasWon = true;
        }
        //TODO: either handle for rescued win on day 3 OR decide that they die
        //FIXME: edit gameinfo.json to reflect these results
        else if (dayCount == 4) {
            System.out.println("The trekking through uncharted forest takes its toll on you over 3 days and you succumb to your fatigue and injuries.");
            System.out.println("You died. Thank you for playing.");
            System.exit(0);
        }
    }

    /* NOTE:
        -prompts user to input a command
        -validates that user enter 2 word commands
        -if so then it passes that invokes commandProcessor() method to trigger each input case
     */
    public void playerInterface() throws IOException, ParseException {
        /* NOTE: DISPLAY SCREEN
            -currentDay
            -players current location
            -location description
            -displays current location directions
         */
        System.out.println("\n******************************************************");
        System.out.println("It is Day " + dayCount);
        System.out.println("You are currently located in " + player.getPlayerLocation().getName().toUpperCase());
        System.out.println(player.getPlayerLocation().getName());
        //System.out.println("Items: " + playerLocation.getItems());
        System.out.println("Directions: " + player.getPlayerLocation().getDirection());

        /* NOTE: INPUT PROMPT
            -prompts user for input
            -parses user input in TextParser.InitialInput() method
         */
        System.out.println("\nEnter a command (or 'help commands' to see a list of commands): ");
        input = in.readLine();
        parser.InitialInput(input);

        if (parser.getValidCommand().size() == 2) {
            command = parser.getValidCommand();
            //processor.processCommand(command);
            commandProcessor();
        }
    }

    public void commandProcessor() throws IOException, ParseException {

        //converted if statements to a switch. And moved commands to player class
        //move engine
        switch (command.get(0)) {
            case "go":
                player.moveEngine(command.get(1));
                break;
            case "get":
                //get engine
                player.getEngine(command.get(1));
                break;
            case "look":
                //look engine
                player.lookEngine(command.get(1));
                break;
            case "use":
                //Use engine
                //still need to implement
                break;
            case "quit":
                //quit engine
                player.quitEngine(command.get(1));
                break;
            case "help":
                //help engine
                player.helpEngine(command.get(1));
                break;
        }

        /*
        if (command.get(0).equals("go")){
            //move engine
            moveEngine(command.get(1));
        }
        else if (command.get(0).equals("get")) {
            //get engine
            getEngine(command.get(1));
        }
        else if (command.get(0).equals("look")) {
            //look engine
            lookEngine(command.get(1));
        }
        else if (command.get(0).equals("use")) {
            //Use engine
            //still need to implement
        }
        else if (command.get(0).equals("quit")) {
            //quit engine
            quitEngine(command.get(1));
        }
        else if (command.get(0).equals("help")) {
            //help engine
            helpEngine(command.get(1));
        }
        */
    }

//    private void moveEngine(String noun){
//
//        Map<String, String> playerDirection = playerLocation.getDirection();
//        String choice = playerDirection.get(noun);
//        randNum = Math.random();
//
//        try {
//            for (int i = 0; i < locations.size(); i++) {
//                if (choice.equals("???")){
//                    //randNum = Math.random();
//                    if(randNum < 0.33){
//                        //choice = "mysterious animal"
//                        //choice = "forest dead end"
//                        player.setPlayerLocation(locations.get(9));
//                    }
//                    else if (randNum >= 0.33 && randNum <= 0.66){
//                        player.setPlayerLocation(locations.get(10));
//                        //dayCount++;
//                    }
//                    else {
//                        player.setPlayerLocation(locations.get(6));
//                    }
//                }
//                else if (choice.equals("cross bridge") && noun.equals("east")) {
//                    player.setPlayerLocation(locations.get(7));
//                }
//                else if (choice.equals("cross bridge") && noun.equals("west")) {
//                    player.setPlayerLocation(locations.get(6));
//                }
//                else if (choice.equals("investigate sound")) {
//                    //randNum = Math.random();
//                    if (randNum < 0.5){
//                        player.setPlayerLocation(locations.get(8));
//                    }
//                    else {
//                        player.setPlayerLocation(locations.get(9));
//                        //dayCount++;
//                    }
//                    //player.setPlayerLocation(locations.get(8));
//
//                }
//                if (choice.equals(locations.get(i).getName())) {
//                    player.setPlayerLocation(locations.get(i));
//                }
//            }
//            playerLocation = player.getPlayerLocation();
//            if (playerLocation.getName().equals("lair - mysterious animal") || playerLocation.getName().equals("forest dead end")) {
//                dayCount++;
//            }
//        }
//        catch (NullPointerException e) {
//            System.out.println("Invalid command!");
//        }
//
//        /*playerLocation = player.getPlayerLocation();
//        if (playerLocation.getName().equals("lair - mysterious animal") || playerLocation.getName().equals("forest dead end")) {
//            dayCount++;
//        }*/
//    }

//    private void getEngine(String noun) throws IOException, ParseException {
//        //get item from JSON file
//        //reader = new FileReader("src/Java/External_Files/location.json");
//        //JSONArray fileArray = (JSONArray) jsonParser.parse(reader);
//
//        //JSONObject inventory = (JSONObject) fileArray.get(0); //this line needs to be based on where the player is
//        //JSONObject item = (JSONObject) inventory.get("inventory");
//        //String itemName = (String) inventory.get("locationItems");
//
//        //String itemName = (String) item.get("itemName");
//        //System.out.println(itemName);
//        if (noun.equals("inventory")) {
//            System.out.println(player.getItems());
//        }
//        List<String> playerItem = playerLocation.getItems();
//
//        if (playerItem.contains(noun)){
//            player.addItems(noun);
//            //need to remove items from location after picking it up
//            for (int i = 0; i < locations.size(); i++) {
//                if (locations.get(i).getItems().contains(noun)){
//                    locations.get(i).getItems().remove(noun);
//                    System.out.println(noun + " was removed from " + locations.get(i).getName());
//                }
//            }
//
//        }
//        else {
//            System.out.println("There is no " + noun);
//        }
//
//    }
//
//    private void lookEngine(String noun) throws IOException, ParseException {
//        //get location from JSON file
//        //based on players location, will determine where player can go, what they can do
//        //You look around and see to the north: first class, south: service area
//        //you see the following items: flashlight, life jacket
//
//
//        System.out.println("You look around and see: ");
//        System.out.println(playerLocation.getDescription());
//        System.out.println("Items: " + playerLocation.getItems());
//        System.out.println("Directions: " + playerLocation.getDirection());
//
//        /*for (Location location : locations) {
//            //JSONObject location = (JSONObject) fileArray.get(0);
//            //display location name, location description, then directions, then items that can be picked up
//
//            //String location = (String) locationName.get("locationName");
//            //System.out.println(obj);
//            //JSONObject location = (JSONObject) obj;
//
//            //JSONArray locItems = (JSONArray) location.get("locationItems");
//            //JSONArray locDirections = (JSONArray) location.get("locationDirections");
//
//            System.out.println(location.getName());
//            System.out.println(location.getDescription());
//            System.out.println(location.getItems());
//            System.out.println(location.getDirection());
//        }*/
//
//
//
//
//    }
//
//    private void useEngine(String noun){}
//
//    private void quitEngine(String noun){
//        if (noun.equals("game")) {
//            System.out.println("Quitting now...");
//            System.exit(0);
//        }
//    }
//
//    private void helpEngine(String noun) throws IOException, ParseException {
//        if (noun.equals("commands")) {
//            //reader = new FileReader("src/Java/External_Files/CommandList.json");
//            //JSONArray fileInfo = (JSONArray) jsonParser.parse(reader);
//            //JSONObject gameInfo = (JSONObject) fileInfo.get(2);
//            //System.out.println(gameInfo);
//            System.out.println(jsonParserClass.commandParser().get(2));
//        }
//        else if (noun.equals("game")){
//            //reader = new FileReader("src/Java/External_Files/GameInfo.json");
//            //JSONObject fileInfo = (JSONObject) jsonParser.parse(reader);
//            //String gameInfo = (String) fileInfo.get("gameInfo");
//            //System.out.println(gameInfo);
//            System.out.println(jsonParserClass.gameInfoParser());
//        }
//    }

    public int getDayCount() {
        return dayCount;
    }

    public void setDayCount(int dayCount) {
        this.dayCount = dayCount;
    }
}
