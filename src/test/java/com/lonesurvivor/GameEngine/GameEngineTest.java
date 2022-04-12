package com.lonesurvivor.GameEngine;

import com.lonesurvivor.Models.Location;
import com.lonesurvivor.Models.Player;
import com.lonesurvivor.Utils.JSONParserClass;
import org.json.simple.parser.ParseException;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

public class GameEngineTest {
    private GameEngine gameEngine = GameEngine.getInstance();
    private JSONParserClass parser = JSONParserClass.getInstance();
    private List<Location> locations;

    public GameEngineTest() {
    }

    @Test
    public void userWins() {
        locations = parser.locationParser();
        boolean winner = gameEngine.validateWin(locations.get(8),locations);
        assertTrue(winner);
    }

    @Test
    public void userContinuesPlaying(){
        locations = parser.locationParser();
        boolean winner = gameEngine.validateWin(locations.get(0),locations);
        assertFalse(winner);
    }
}