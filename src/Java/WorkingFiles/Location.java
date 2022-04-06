package Java.WorkingFiles;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class Location {

    public static void main(String[] args)
    {

        JSONParser jsonParser = new JSONParser();  // This helps to read our json file

        try (FileReader reader = new FileReader("src/Java/External_Files/PlaneCrash.json"))
        {
            Object obj = jsonParser.parse(reader);

            JSONArray location = (JSONArray) obj;
            System.out.println(location);

            location.forEach( loc -> parseLocationObject( (JSONObject) loc ) );

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException | org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }
    }

    private static void parseLocationObject(JSONObject location)
    {
        //Getting player location
        JSONObject locationObject = (JSONObject) location.get("location");

        String locationDescription = (String) locationObject.get("locationDescription");
        System.out.println(locationDescription);

        String locationName = (String) locationObject.get("locationName");
        System.out.println(locationName);


    }
}