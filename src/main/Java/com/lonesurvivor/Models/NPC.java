package com.lonesurvivor.Models;

import javax.xml.crypto.Data;

public class NPC {
    String name;
    Double power;

    public NPC(String name, Double power) {
        this.name = name;
        this.power = power;
    }

    public String getName() {
        return name;
    }

    public Double getPower() {
        return power;
    }
}
