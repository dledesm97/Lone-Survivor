//JSONParseClassTEst
package com.lonesurvivor.Utils;

import com.lonesurvivor.Models.Location;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

public class JSONParserClassTest {
    private JSONParser jsonParser;

    @Test
    public void createdListOfLocationsFromJson(){
        List<Location> locations;
        locations = JSONParserClass.getInstance().locationParser("json/PlaneCrash.json");
        assertNotNull(locations);
    }

    @Test
    public void emptyListOfLocations(){
        List<Location> locations;
        locations = JSONParserClass.getInstance().locationParser("");
        assertNotNull(locations);
    }

}