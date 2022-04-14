package com.lonesurvivor.Models;

import com.lonesurvivor.GameEngine.GameEngine;
import com.lonesurvivor.Utils.DisplayScreen;
import com.lonesurvivor.Utils.JSONParserClass;
import com.lonesurvivor.Views.LocationFrame;
import com.lonesurvivor.Views.LoneSurvivorBase;
import com.lonesurvivor.Views.MasterGui;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.*;


public class Player {
    private static Player player = null;
    //CONSTANTS
    private static final String GAME_INFO_PATH = "gameInfo.txt";

    // Fields
    private String name;
   //private Set<String> inventory = new LinkedHashSet<>();
    private ArrayList<String> inventory = new ArrayList<>();
    private Location playerLocation;
    private List<Location> locations;
    private JSONParserClass jsonParserClass = new JSONParserClass();
    private int health = 50;
    private int actionTracker = 0;

    public static Player getInstance(){
        if(player == null){
            try {
                player = new Player();
            }catch (IOException | ParseException e){
                System.out.println(e.getMessage());
                System.exit(0);
            }
        }
        return player;
    }

    public Player() throws IOException, ParseException {
    }

    // Business Methods

    public void moveEngine(String noun) throws IOException {
        for (Map.Entry<String, String> set : playerLocation.getDirection().entrySet()) {
            if (set.getKey().equalsIgnoreCase(noun)){
                for(Location aLocation : locations){
                    if (set.getValue().equals(aLocation.getName()))
                    {
                        setPlayerLocation(aLocation);
                        actionTracker++;
                        MasterGui.refreshFrames();
                    }
                }
            }
        }
    }

    public void getEngine(String noun) throws IOException {

        for (String aItem : playerLocation.getItems()){
            if (aItem.equalsIgnoreCase(noun)){
                addItems(aItem);
                getPlayerLocation().getItems().remove(aItem);
                LocationFrame.textDisplayGui("You picked up a " + aItem);
                MasterGui.refreshInventoryBox();
            }
        }

//        //storing the items available in that location in a list
//        List<Item> playerItem = playerLocation.getItems();
//
//        if (noun.equals("inventory")) {
//           // LoneSurvivorBase.GUI.setMultipleText(getItems().toString());
//            LocationFrame.textDisplayGui(getItems().toString());
//        }
//
//        else if (playerItem.contains(noun)) {
//            addItems(noun);
//            playerItem.remove(noun);
//            //LoneSurvivorBase.GUI.setMultipleText(noun + " was removed from " + playerLocation.getName());
//            LocationFrame.textDisplayGui(noun + " was removed from " + playerLocation.getName());
//        }
//        else {
//           // LoneSurvivorBase.GUI.setMultipleText("There is no " + noun);
//            LocationFrame.textDisplayGui("There is no " + noun);
//        }
    }

    public void lookEngine(String noun) throws IOException, ParseException {
        //get location from JSON file
        //based on players location, will determine where player can go, what they can do
        //You look around and see to the north: first class, south: service area
        //you see the following items: flashlight, life jacket


//        LoneSurvivorBase.GUI.setMultipleText("You look around and see: ");
//        LoneSurvivorBase.GUI.setMultipleText(playerLocation.getDescription());
//        LoneSurvivorBase.GUI.setMultipleText("Items: " + playerLocation.getItems());
//        LoneSurvivorBase.GUI.setMultipleText("Directions: " + playerLocation.getDirection());
        LocationFrame.textDisplayGui("You look around and see: \n" + playerLocation.getDescription() +
                                               "\nItems: " + playerLocation.getItems() + "\nDirections: " + playerLocation.getDirection());

        /*for (Location location : locations) {
            //JSONObject location = (JSONObject) fileArray.get(0);
            //display location name, location description, then directions, then items that can be picked up

            //String location = (String) locationName.get("locationName");
            //System.out.println(obj);
            //JSONObject location = (JSONObject) obj;

            //JSONArray locItems = (JSONArray) location.get("locationItems");
            //JSONArray locDirections = (JSONArray) location.get("locationDirections");

            System.out.println(location.getName());
            System.out.println(location.getDescription());
            System.out.println(location.getItems());
            System.out.println(location.getDirection());
        }*/


    }
    public void useEngine(String noun){
//        System.out.println("using" + noun);
//        if(getItems().contains(noun)){
//            System.out.println("Using " + noun);
//        }

        //Creating an Item object using the name of the item
        Item item = new Item(noun);
        //item.useItem();

    }

    public void quitEngine(String noun){
        if (noun.equals("game")) {
           // LoneSurvivorBase.GUI.setMultipleText("Quitting now...");
            LocationFrame.textDisplayGui("Quitting now...");
            System.exit(0);
        }
    }
    
    public void attackEngine(String noun){
            if (playerLocation.getNpc().getName().equalsIgnoreCase(noun)){
                LocationFrame.textDisplayGui("You attacked " + playerLocation.getNpc().getName());
            }
            else{
                System.out.println("Can't attack that");
            }
    }

    public void helpEngine(String noun) throws IOException, ParseException {
        if (noun.equals("commands")) {
            //reader = new FileReader("src/Java/External_Files/CommandList.json");
            //JSONArray fileInfo = (JSONArray) jsonParser.parse(reader);
            //JSONObject gameInfo = (JSONObject) fileInfo.get(2);
            //System.out.println(gameInfo);
            // LoneSurvivorBase.GUI.setMultipleText(jsonParserClass.commandParser().get(2).toString());// Stringify the jpclass
            LocationFrame.textDisplayGui(jsonParserClass.commandParser().get(2).toString());
        }
        else if (noun.equals("game")){
            //reader = new FileReader("src/Java/External_Files/GameInfo.json");
            //JSONObject fileInfo = (JSONObject) jsonParser.parse(reader);
            //String gameInfo = (String) fileInfo.get("gameInfo");
            //System.out.println(gameInfo);
            
            //LoneSurvivorBase.GUI.setMultipleText(DisplayScreen.showScreen(GAME_INFO_PATH));
            LocationFrame.textDisplayGui(DisplayScreen.showScreen(GAME_INFO_PATH));
//            DisplayScreen.showScreen(GAME_INFO_PATH);
        }
    }


    // Accessor Methods

    public String getName() {
        return name;
    }

    public ArrayList<String> getItems() {
        return inventory;
    }

    public void addItems(String aItem) {
        inventory.add(aItem);
    }

    public void setPlayerLocation(Location location) {
        playerLocation = location;
    }

    public Location getPlayerLocation() {
        return playerLocation;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    public int getActionTracker() {
        return actionTracker;
    }

    public void setActionTracker(int actionTracker) {
        this.actionTracker = actionTracker;
    }
}
