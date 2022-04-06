package Java.GameFiles;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * reads and parses all the json files using Simple
 */
public class JSONParserClass {

    private JSONParser jsonParser;
    private BufferedReader in;
    private FileReader locReader;
    private FileReader commReader;
    private FileReader infoReader;
    //private FileReader outsReader;

    private JSONArray locFile;
    //private JSONObject locFile;
    private JSONArray commFile;
    private JSONObject infoFile;
    //private JSONArray outsFile;

    private List<Location> locations;
    private Location location;

    private List<JSONArray> commands;

    private JSONObject verbObj;
    private JSONObject nounObj;
    private JSONObject commObj;

    private JSONArray verbList;
    private JSONArray nounList;
    private JSONArray commList;

    private String gameInfo;

    public JSONParserClass() throws IOException, ParseException {
        InputStream is = getFileFromResourceAsStream("Java/External_Files/PlaneCrash.json");
        InputStreamReader isr = new InputStreamReader(is);
        //InputStreamReader isr1 = new InputStreamReader(getFileFromResourceAsStream("Java/External_Files/PlaneCrash.json"));
        InputStream is2 = getFileFromResourceAsStream("Java/External_Files/CommandList.json");
        InputStreamReader isr2 = new InputStreamReader(is2);
        InputStream is3 = getFileFromResourceAsStream("Java/External_Files/GameInfo.json");
        InputStreamReader isr3 = new InputStreamReader(is3);

        locations = new ArrayList<>();
        commands = new ArrayList<>();
        in = new BufferedReader(new InputStreamReader(System.in));
        jsonParser = new JSONParser();

        //locReader = new FileReader("src/Java/External_Files/PlaneCrash.json");
        //commReader = new FileReader("src/Java/External_Files/CommandList.json");
        //infoReader = new FileReader("src/Java/External_Files/GameInfo.json");
        //outsReader = new FileReader("src/Java/External_Files/Outside.json");

        //locFile = (JSONArray) jsonParser.parse(locReader);

        //commFile = (JSONArray) jsonParser.parse(commReader);
        //infoFile = (JSONObject) jsonParser.parse(infoReader);
        //outsFile = (JSONArray) jsonParser.parse(outsReader);

        locFile = (JSONArray) jsonParser.parse(isr);
        commFile = (JSONArray) jsonParser.parse(isr2);
        infoFile = (JSONObject) jsonParser.parse(isr3);

    }

    public List<Location> locationParser() {

        //for (Object o : locFile)
        //for (int i = 0; i < locFile.size(); i++)
        for (Object o : locFile){
            //JSONObject obj = (JSONObject) locFile.get("cockpit");

            JSONObject obj = (JSONObject) o;
            String name = (String) obj.get("locationName");
            String description = (String) obj.get("locationDescription");
            JSONArray locItems = (JSONArray) obj.get("locationItems");
            JSONObject locDirections = (JSONObject) obj.get("locationDirections");
            //JSONArray locDirections = (JSONArray) obj.get("locationDirections");

            location = new Location(name, description, locItems, locDirections);
            //System.out.println(location);
            locations.add(location);
        }
        return locations;
    }

    /*public List<Location> outsideParser() {
        //for (Object o : locFile)
        //for (int i = 0; i < locFile.size(); i++)
        for (Object o : outsFile){
            //JSONObject obj = (JSONObject) locFile.get("cockpit");

            JSONObject obj = (JSONObject) o;
            String name = (String) obj.get("locationName");
            String description = (String) obj.get("locationDescription");
            JSONArray locItems = (JSONArray) obj.get("locationItems");
            JSONObject locDirections = (JSONObject) obj.get("locationDirections");
            //JSONArray locDirections = (JSONArray) obj.get("locationDirections");

            location = new Location(name, description, locItems, locDirections);
            //System.out.println(location);
            locations.add(location);
        }
        return locations;
    }*/

    public List<JSONArray> commandParser() {
        verbObj = (JSONObject) commFile.get(0);
        nounObj = (JSONObject) commFile.get(1);
        commObj = (JSONObject) commFile.get(2);

        verbList = (JSONArray) verbObj.get("verb");
        nounList = (JSONArray) nounObj.get("noun");
        commList = (JSONArray) commObj.get("valid commands");

        commands.add(0, verbList);
        commands.add(1, nounList);
        commands.add(2, commList);

        return commands;
    }

    public String gameInfoParser() {
        gameInfo = (String) infoFile.get("gameInfo");

        return gameInfo;
    }

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
