package com.lonesurvivor.Models;

import com.lonesurvivor.GameEngine.GameEngine;
import com.lonesurvivor.Utils.DisplayScreen;
import com.lonesurvivor.Utils.JSONParserClass;
import com.lonesurvivor.Views.LoneSurvivorBase;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class Player {
    //CONSTANTS
    private static final String GAME_INFO_PATH = "gameInfo.txt";

    // Fields
    private String name;
    private Set<String> inventory = new LinkedHashSet<>();
    private Location playerLocation;
    private List<Location> locations;
    private JSONParserClass jsonParserClass = new JSONParserClass();

    public Player(String name, List<Location> locations) throws IOException, ParseException {
        this.name = name;
        this.locations = locations;


    // instantiate when class is instantiated
    // inventory = new LinkedHashSet<>();
    }

    // Business Methods

    public void moveEngine(String noun){

        Map<String, String> playerDirection = playerLocation.getDirection();
        String choice = playerDirection.get(noun);
        double randNum = Math.random();

        try {
            for (Location location : locations) {
                if (choice.equals("???")) {
                    //randNum = Math.random();
                    if (randNum < 0.33) {
                        //choice = "mysterious animal"
                        //choice = "forest dead end"
                        setPlayerLocation(locations.get(9));
                    } else if (randNum >= 0.33 && randNum <= 0.66) {
                        setPlayerLocation(locations.get(10));
                        //dayCount++;
                    } else {
                        setPlayerLocation(locations.get(6));
                    }
                } else if (choice.equals("cross bridge") && noun.equals("east")) {
                    setPlayerLocation(locations.get(7));
                } else if (choice.equals("cross bridge") && noun.equals("west")) {
                    setPlayerLocation(locations.get(6));
                } else if (choice.equals("investigate sound")) {
                    //randNum = Math.random();
                    if (randNum < 0.5) {
                        setPlayerLocation(locations.get(8));
                    } else {
                        setPlayerLocation(locations.get(9));
                        //dayCount++;
                    }
                    //player.setPlayerLocation(locations.get(8));

                }
                if (choice.equals(location.getName())) {
                    setPlayerLocation(location);
                }
            }
            playerLocation = getPlayerLocation();
            if (playerLocation.getName().equals("lair - mysterious animal") || playerLocation.getName().equals("forest dead end")) {
                GameEngine.dayCount++;
            }
        }
        catch (NullPointerException e) {
            LoneSurvivorBase.GUI.setMultipleText("Invalid command!");
        }

        /*playerLocation = player.getPlayerLocation();
        if (playerLocation.getName().equals("lair - mysterious animal") || playerLocation.getName().equals("forest dead end")) {
            dayCount++;
        }*/
    }

    public void getEngine(String noun) throws IOException, ParseException {
        //storing the items available in that location in a list
        List<Item> playerItem = playerLocation.getItems();

        if (noun.equals("inventory")) {
            LoneSurvivorBase.GUI.setMultipleText(getItems().toString());
        }

        else if (playerItem.contains(noun)) {
            addItems(noun);
            playerItem.remove(noun);
            LoneSurvivorBase.GUI.setMultipleText(noun + " was removed from " + playerLocation.getName());
        }
        else {
            LoneSurvivorBase.GUI.setMultipleText("There is no " + noun);
        }
//            if (playerLocation.getItems().contains(noun)) {
//                playerLocation.getItems().remove(noun);
//                System.out.println(noun + " was removed from " + playerLocation.getName());
//            }
//            else{
//                System.out.println("There is no" + noun);
//            }

        //need to remove items from location after picking it up
//            for (Location location : locations) {
//                if (location.getItems().contains(noun)) {
//                    location.getItems().remove(noun);
//                    System.out.println(noun + " was removed from " + location.getName());
//                }
//            }
//        else {
//            System.out.println("There is no " + noun);
//        }

    }

    public void lookEngine(String noun) throws IOException, ParseException {
        //get location from JSON file
        //based on players location, will determine where player can go, what they can do
        //You look around and see to the north: first class, south: service area
        //you see the following items: flashlight, life jacket


        LoneSurvivorBase.GUI.setMultipleText("You look around and see: ");
        LoneSurvivorBase.GUI.setMultipleText(playerLocation.getDescription());
        LoneSurvivorBase.GUI.setMultipleText("Items: " + playerLocation.getItems());
        LoneSurvivorBase.GUI.setMultipleText("Directions: " + playerLocation.getDirection());

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
        item.useItem();

    }

    public void quitEngine(String noun){
        if (noun.equals("game")) {
            LoneSurvivorBase.GUI.setMultipleText("Quitting now...");
            System.exit(0);
        }
    }

    public void helpEngine(String noun) throws IOException, ParseException {
        if (noun.equals("commands")) {
            //reader = new FileReader("src/Java/External_Files/CommandList.json");
            //JSONArray fileInfo = (JSONArray) jsonParser.parse(reader);
            //JSONObject gameInfo = (JSONObject) fileInfo.get(2);
            //System.out.println(gameInfo);
            LoneSurvivorBase.GUI.setMultipleText(jsonParserClass.commandParser().get(2).toString());// Stringify the jpclass
        }
        else if (noun.equals("game")){
            //reader = new FileReader("src/Java/External_Files/GameInfo.json");
            //JSONObject fileInfo = (JSONObject) jsonParser.parse(reader);
            //String gameInfo = (String) fileInfo.get("gameInfo");
            //System.out.println(gameInfo);
            
            LoneSurvivorBase.GUI.setMultipleText(DisplayScreen.showScreen(GAME_INFO_PATH));
//            DisplayScreen.showScreen(GAME_INFO_PATH);
        }
    }



    public void addItems(String item) {
        inventory.add(item);
        LoneSurvivorBase.GUI.setMultipleText("Player has added " + item + " to their inventory. ");
    }

    // Accessor Methods

    public String getName() {
        return name;
    }

    public Set<String> getItems() {

        return inventory;
    }

    public void setPlayerLocation(Location location) {
        playerLocation = location;
    }

    public Location getPlayerLocation() {
        return playerLocation;
    }

//    // empty method
//    public void performAction() {}

}
