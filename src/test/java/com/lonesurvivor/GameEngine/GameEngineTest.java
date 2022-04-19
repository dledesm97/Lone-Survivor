package com.lonesurvivor.GameEngine;

import com.lonesurvivor.Models.Location;
import com.lonesurvivor.Utils.JSONParserClass;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class GameEngineTest {

    @Test
    public void userWins() throws InterruptedException {
        List<Location> locations = JSONParserClass.getInstance().locationGenerator("json/PlaneCrash.json");
        Location playerLocation = locations.get(9);
        assertTrue(GameEngine.getInstance().checkWin(playerLocation));
    }

    @Test
    public void userContinuesPlaying() throws InterruptedException {
        List<Location> locations = JSONParserClass.getInstance().locationGenerator("json/PlaneCrash.json");
        Location playerLocation = locations.get(5);
        assertFalse(GameEngine.getInstance().checkWin(playerLocation));
    }
}