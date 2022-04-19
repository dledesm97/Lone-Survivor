package com.lonesurvivor.Models;

import com.lonesurvivor.Items.Item;
import com.lonesurvivor.NPC.NPC;
import com.lonesurvivor.Utils.JSONParserClass;
import com.lonesurvivor.Views.LocationFrame;
import com.lonesurvivor.Views.MasterGui;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.*;


public class Player {

    private static Player player = null;

    // Fields ***************************************
    private ArrayList<String> inventory = new ArrayList<>();
    private Location currentLocation;
    private List<Location> locations;
    private int health = 50;
    private int attackPoints = 10;
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
        for (Map.Entry<String, String> set : currentLocation.getDirection().entrySet()) {
            if (set.getKey().equalsIgnoreCase(noun)){
                for(Location aLocation : locations){
                    if (set.getValue().equals(aLocation.getName())) {
                        setCurrentLocation(aLocation);
                        actionTracker++;
                        MasterGui.refreshFrames();
                    }
                }
            }
        }
    }

    public void getEngine(String noun) throws IOException {
        for (String aItem : currentLocation.getItems()){
            if (aItem.equalsIgnoreCase(noun)){
                addItems(aItem);
                getCurrentLocation().getItems().remove(aItem);
                LocationFrame.textDisplayGui("You picked up a " + aItem.toUpperCase() + ".");
                MasterGui.refreshInventoryBox();
            }
        }
    }

    public void lookEngine(String noun) {
        //get location from JSON file
        //based on player's location, will determine where player can go, what they can do
        //You look around and see to the north: first class, south: service area
        //you see the following items: flashlight, life jacket, etc.

        String locationName = Player.getInstance().getCurrentLocation().getName();
        if(locationName.equals("forest") || locationName.equals("forest dead end") || locationName.equals("bridge") || locationName.equals("village") || locationName.equals("outside plane") || locationName.equals("economy class")) {
            LocationFrame.textDisplayGui(viewLocItems());
        }
        else{
            LocationFrame.textDisplayGui("Its dark, you need to USE a FLASHLIGHT to do that.\nIf you don't have one maybe you should try and LOOK AROUND somewhere in the PLANE for one.");
        }
        actionTracker++;
    }

    public void useEngine(String noun) throws IOException, InterruptedException {
        Item item = new Item(noun);
        item.useItem();
        actionTracker++;
    }

    public void quitEngine(String noun){
        if (noun.equals("game")) {
            LocationFrame.textDisplayGui("Quitting now...");
            System.exit(0);
        }
    }

    public void attackEngine(String noun){
            if (currentLocation.getNpc().getName().equalsIgnoreCase(noun)){
                AttackEngine attackEngine = new AttackEngine();
                attackEngine.attack();
//                LocationFrame.textDisplayGui("You attacked " + currentLocation.getNpc().getName());


            }
            else{
                LocationFrame.textDisplayGui("You can't attack that!");
            }
    }

    public void helpEngine(String noun) throws IOException, ParseException {
        if (noun.equals("commands")) {
            LocationFrame.textDisplayGui(JSONParserClass.getInstance().commandParser().get(2).toString());
        }
        else if (noun.equals("game")){
           LocationFrame.textDisplayGui(JSONParserClass.getInstance().parseGameInfo());
        }
    }

    //view formatted string message of items in the current location
    public String viewLocItems(){
        String separator = " ";
        StringBuilder itemMsg = new StringBuilder();
        if(currentLocation.hasItems()){
            for(String item : currentLocation.getItems()){
                itemMsg.append(separator);
                itemMsg.append(item.toUpperCase());
                separator = ", ";
            }
            return "\nAfter some searching you can see the following items:" + itemMsg + ".";
        } else {
            return "You couldn't find any items, try searching somewhere else.";
        }
    }

    public void talkEngine(String noun){
        NPC npc = new NPC();
        npc.talking(noun);
    }

    // Accessor Methods **********************
    public ArrayList<String> getItems() {
        return inventory;
    }

    public void addItems(String aItem) {
        inventory.add(aItem);
    }

    public void setCurrentLocation(Location location) {
        currentLocation = location;
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
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

    public int getAttackPoints() {
        return attackPoints;
    }

    public void setAttackPoints(int attackPoints) {
        this.attackPoints = attackPoints;
    }

    public ArrayList<String> getInventory() {
        return inventory;
    }
}
