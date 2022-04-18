package com.lonesurvivor.Utils;

import com.lonesurvivor.Models.Location;
import com.lonesurvivor.Models.NPC;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Singleton reads and parses all the json files using Json Simple
 */
public class JSONParserClass {

    //class singleton
    private static JSONParserClass parser = null;

    //constants
    public static final String COMMAND_LIST = "json/CommandList.json";


    //field variables
    private JSONParser jsonParser = new JSONParser(); //json simple instance
    private JSONArray locationFile;
    private JSONArray commandFile;
    private List<JSONArray> commands;

    //singleton instantiation
    public static JSONParserClass getInstance() {
        if (parser == null) {
            try {
                parser = new JSONParserClass();
            } catch (IOException | ParseException e) {
                System.out.println(e.getMessage());
                System.exit(0);
            }
        }
        return parser;
    }

    //class CTOR that initiates list of valid commands
    private JSONParserClass() throws IOException, ParseException {
        InputStream inputStream = getFileFromResourceAsStream(COMMAND_LIST);
        InputStreamReader streamReader = new InputStreamReader(inputStream);
        commandFile = (JSONArray) jsonParser.parse(streamReader);
        commands = new ArrayList<>();
    }

    //generates NPCs at some locations
    public ArrayList<NPC> npcGenerator(String file) {
        ArrayList<NPC> npcs = new ArrayList<>();
        try {
            InputStream is = getFileFromResourceAsStream(file);
            InputStreamReader isr = new InputStreamReader(is);
            locationFile = (JSONArray) jsonParser.parse(isr);
        } catch (Exception e) { //If there is no file being passed send message and exit
            System.out.println("File not found");
            System.exit(0);
        }

        for (Object o : locationFile) {
            JSONObject obj = (JSONObject) o;

            JSONObject npcInLocation = (JSONObject) obj.get("locationNPC");

            NPC newNpc = new NPC((String) npcInLocation.get("name"), (Double) npcInLocation.get("power"));
            npcs.add(newNpc);
        }
        return npcs;
    }

    public List<Location> locationGenerator(String file) {
        List<Location> locations = new ArrayList<>();
        int counter = 0;
        try {
            InputStream is = getFileFromResourceAsStream(file);
            InputStreamReader isr = new InputStreamReader(is);
            locationFile = (JSONArray) jsonParser.parse(isr);
        } catch (Exception e) { //If there is no file being passed send message and exit
            locations = null;
            System.out.println("File not found");
            System.exit(0);
        }

        for (Object o : locationFile) {
            JSONObject obj = (JSONObject) o;

            // create location instance by passing its respective args
            Location location = new Location((String) obj.get("locationName"), (String) obj.get("locationDescription"), (String) obj.get("locationImage")
                    , (JSONArray) obj.get("locationItems"), npcGenerator("json/PlaneCrash.json").get(counter), (JSONObject) obj.get("locationDirections"));

            //add the newly created location into arraylist of locations
            locations.add(location);
            counter++;
        }
        return locations;
    }

    public List<JSONArray> commandParser() {
        JSONObject verb = (JSONObject) commandFile.get(0);
        JSONObject noun = (JSONObject) commandFile.get(1);
        JSONObject command = (JSONObject) commandFile.get(2);

        JSONArray verbList = (JSONArray) verb.get("verb");
        JSONArray nounList = (JSONArray) noun.get("noun");
        JSONArray commandList = (JSONArray) command.get("valid commands");

        commands.add(0, verbList);
        commands.add(1, nounList);
        commands.add(2, commandList);

        return commands;
    }

    public String parseGameInfo(String filename) throws IOException, ParseException {
        InputStream inputStream = getFileFromResourceAsStream(filename);
        InputStreamReader isr = new InputStreamReader(inputStream);
        JSONObject infoFile = (JSONObject) jsonParser.parse(isr);
        return (String) infoFile.get("gameInfo");
    }

    //helper method that allows us to read paths from a resource folder in jar
    private static InputStream getFileFromResourceAsStream(String fileName) {
        ClassLoader classLoader = JSONParserClass.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);
        if (inputStream == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            return inputStream;
        }
    }
}