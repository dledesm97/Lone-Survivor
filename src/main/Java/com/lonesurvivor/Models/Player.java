package com.lonesurvivor.Models;

import com.lonesurvivor.Utils.DisplayScreen;
import com.lonesurvivor.Utils.JSONParserClass;
import com.lonesurvivor.Views.LocationFrame;
import com.lonesurvivor.Views.MasterGui;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.*;


public class Player {
    private static Player player = null;
    //CONSTANTS
    private static final String GAME_INFO_PATH = "gameInfo.txt";

    // Fields ***************************************

    private String name;
    private ArrayList<String> inventory = new ArrayList<>();
    private Location playerLocation;
    private List<Location> locations;
//    private JSONParserClass jsonParserClass = getInstance();
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

    // Business Methods ***********************************

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
    }

    public void lookEngine(String noun) {
        //get location from JSON file
        //based on player's location, will determine where player can go, what they can do
        //You look around and see to the north: first class, south: service area
        //you see the following items: flashlight, life jacket, etc.

        LocationFrame.textDisplayGui("You look around and see: \n" + playerLocation.getDescription() +
                                               "\nItems: " + playerLocation.getItems() + "\nDirections: " + playerLocation.getDirection());
    }

    public void useEngine(String noun){
        Item item = new Item(noun);
    }

    public void quitEngine(String noun){
        if (noun.equals("game")) {
            LocationFrame.textDisplayGui("Quitting now...");
            System.exit(0);
        }
    }
    
    public void attackEngine(String noun){
            if (playerLocation.getNpc().getName().equalsIgnoreCase(noun)){
                LocationFrame.textDisplayGui("You attacked " + playerLocation.getNpc().getName());
            }
            else{
                LocationFrame.textDisplayGui("Can't attack that");
            }
    }

    public void helpEngine(String noun) throws IOException {
        if (noun.equals("commands")) {
            LocationFrame.textDisplayGui(jsonParserClass.commandParser().get(2).toString());
            //reader = new FileReader("src/Java/External_Files/CommandList.json");
            //JSONArray fileInfo = (JSONArray) jsonParser.parse(reader);
            //JSONObject gameInfo = (JSONObject) fileInfo.get(2);
            //System.out.println(gameInfo);
            // LoneSurvivorBase.GUI.setMultipleText(jsonParserClass.commandParser().get(2).toString());// Stringify the jpclass
            LocationFrame.textDisplayGui(JSONParserClass.getInstance().commandParser().get(2).toString());
        }
        else if (noun.equals("game")){
            LocationFrame.textDisplayGui(DisplayScreen.showScreen(GAME_INFO_PATH));
        }
    }

    // Accessor Methods **********************

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
