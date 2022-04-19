//JSONParseClassTEst
package com.lonesurvivor.Utils;

import com.lonesurvivor.Models.Location;
import com.lonesurvivor.Models.NPC;
import org.json.simple.parser.JSONParser;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class JSONParserClassTest {
    private JSONParser jsonParser;

    @Test
    public void createdListOfLocationsFromJson(){
        List<Location> locations;
        locations = JSONParserClass.getInstance().locationGenerator("json/PlaneCrash.json");
        assertNotNull(locations);
    }

//    @Test
//    public void emptyListOfLocations(){
//        List<Location> locations;
//        locations = JSONParserClass.getInstance().locationGenerator("");
//        assertNotNull(locations);
//    }

    @Test
    public void checkListSize(){
        List<Location> locations;
        locations = JSONParserClass.getInstance().locationGenerator("json/PlaneCrash.json");
        assertEquals(locations.size(),11);
    }

    @Test
    public void npcGenerator() {
        ArrayList<NPC> npc;
        npc = JSONParserClass.getInstance().npcGenerator("json/PlaneCrash.json");
        assertEquals(11,npc.size());
    }

}