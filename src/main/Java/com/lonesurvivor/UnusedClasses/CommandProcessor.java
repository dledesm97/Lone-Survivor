//package Java.GameFiles;
//
//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;
//
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.io.IOException;
//import java.util.List;
//
///**
// * checks if verb can be performed with noun (after being validated by Text Parser)
// */
//public class CommandProcessor {
//
//    private List<String> command;
//    private JSONParserClass jsonParserClass;
//    Player player;
//    JSONParser jsonParser = new JSONParser();
//    FileReader reader;
//
//    public CommandProcessor() throws IOException, ParseException {
//        jsonParserClass = new JSONParserClass();
//        player = new Player();
//    }
//
//    public void processCommand(List<String> validCommand) throws IOException, ParseException {
//        command = validCommand;
//
//        if (command.get(0).equals("go")){
//            //move engine
//            moveEngine(command.get(1));
//        }
//        else if (command.get(0).equals("get")) {
//            //get engine
//            getEngine(command.get(1));
//        }
//        else if (command.get(0).equals("look")) {
//            //look engine
//            lookEngine(command.get(1));
//        }
//        else if (command.get(0).equals("use")) {
//            //Use engine
//        }
//        else if (command.get(0).equals("quit")) {
//            //quit engine
//            quitEngine(command.get(1));
//        }
//        else if (command.get(0).equals("help")) {
//            //help engine
//            helpEngine(command.get(1));
//        }
//    }
//
//    private void moveEngine(String noun){
//        //player.setLocation();
//    }
//
//    private void getEngine(String noun) throws IOException, ParseException {
//        //get item from JSON file
//        reader = new FileReader("src/Java/External_Files/location.json");
//        JSONArray fileArray = (JSONArray) jsonParser.parse(reader);
//
//        JSONObject inventory = (JSONObject) fileArray.get(0); //this line needs to be based on where the player is
//        //JSONObject item = (JSONObject) inventory.get("inventory");
//        String itemName = (String) inventory.get("locationItems");
//
//        //String itemName = (String) item.get("itemName");
//        System.out.println(itemName);
//
//        if (noun.equals(itemName)){
//            player.addItems(itemName);
//        }
//        //player.addItems(itemName);
//    }
//
//    private void lookEngine(String noun) throws IOException, ParseException {
//        //get location from JSON file
//
//        reader = new FileReader("src/Java/External_Files/location.json");
//        JSONArray fileArray = (JSONArray) jsonParser.parse(reader);
//        System.out.println("You look around and see: "  );
//        for (Object obj : fileArray) {
//            //JSONObject location = (JSONObject) fileArray.get(0);
//            //display location name, location description, then directions, then items that can be picked up
//
//            //String location = (String) locationName.get("locationName");
//            //System.out.println(obj);
//            JSONObject location = (JSONObject) obj;
//            JSONArray locItems = (JSONArray) location.get("locationItems");
//            JSONArray locDirections = (JSONArray) location.get("locationDirections");
//
//            System.out.println(location.get("locationName"));
//            System.out.println(location.get("locationDescription"));
//            System.out.println(location.get("locationDirections"));
//            System.out.println(location.get("locationItems"));
//        }
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
//            reader = new FileReader("src/Java/External_Files/CommandList.json");
//            JSONArray fileInfo = (JSONArray) jsonParser.parse(reader);
//            JSONObject gameInfo = (JSONObject) fileInfo.get(2);
//            System.out.println(gameInfo);
//        }
//        else if (noun.equals("game")){
//            reader = new FileReader("src/Java/External_Files/GameInfo.json");
//            JSONObject fileInfo = (JSONObject) jsonParser.parse(reader);
//            String gameInfo = (String) fileInfo.get("gameInfo");
//            System.out.println(gameInfo);
//        }
//    }
//}
