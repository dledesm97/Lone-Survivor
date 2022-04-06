package com.lonesurvivor.Models;

import com.lonesurvivor.GameEngine.GameEngine;
import com.lonesurvivor.Utils.JSONParserClass;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class Player {

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
            System.out.println("Invalid command!");
        }

        /*playerLocation = player.getPlayerLocation();
        if (playerLocation.getName().equals("lair - mysterious animal") || playerLocation.getName().equals("forest dead end")) {
            dayCount++;
        }*/
    }

    public void getEngine(String noun) throws IOException, ParseException {
        //get item from JSON file
        //reader = new FileReader("src/Java/External_Files/location.json");
        //JSONArray fileArray = (JSONArray) jsonParser.parse(reader);

        //JSONObject inventory = (JSONObject) fileArray.get(0); //this line needs to be based on where the player is
        //JSONObject item = (JSONObject) inventory.get("inventory");
        //String itemName = (String) inventory.get("locationItems");

        //String itemName = (String) item.get("itemName");
        //System.out.println(itemName);
        if (noun.equals("inventory")) {
            System.out.println(getItems());
        }
        List<String> playerItem = playerLocation.getItems();

        if (playerItem.contains(noun)){
            addItems(noun);
            //need to remove items from location after picking it up
            for (Location location : locations) {
                if (location.getItems().contains(noun)) {
                    location.getItems().remove(noun);
                    System.out.println(noun + " was removed from " + location.getName());
                }
            }

        }
        else {
            System.out.println("There is no " + noun);
        }

    }

    public void lookEngine(String noun) throws IOException, ParseException {
        //get location from JSON file
        //based on players location, will determine where player can go, what they can do
        //You look around and see to the north: first class, south: service area
        //you see the following items: flashlight, life jacket


        System.out.println("You look around and see: ");
        System.out.println(playerLocation.getDescription());
        System.out.println("Items: " + playerLocation.getItems());
        System.out.println("Directions: " + playerLocation.getDirection());

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

    public void useEngine(String noun){}

    public void quitEngine(String noun){
        if (noun.equals("game")) {
            System.out.println("Quitting now...");
            System.exit(0);
        }
    }

    public void helpEngine(String noun) throws IOException, ParseException {
        if (noun.equals("commands")) {
            //reader = new FileReader("src/Java/External_Files/CommandList.json");
            //JSONArray fileInfo = (JSONArray) jsonParser.parse(reader);
            //JSONObject gameInfo = (JSONObject) fileInfo.get(2);
            //System.out.println(gameInfo);
            System.out.println(jsonParserClass.commandParser().get(2));
        }
        else if (noun.equals("game")){
            //reader = new FileReader("src/Java/External_Files/GameInfo.json");
            //JSONObject fileInfo = (JSONObject) jsonParser.parse(reader);
            //String gameInfo = (String) fileInfo.get("gameInfo");
            //System.out.println(gameInfo);
            System.out.println(jsonParserClass.gameInfoParser());
        }
    }



    public void addItems(String item) {
        inventory.add(item);
        System.out.println("Player has added " + item + " to their inventory. ");
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
