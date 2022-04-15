package com.lonesurvivor.Models;

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

    @Override
    public String toString() {
        return "NPC{" +
                "name='" + name + '\'' +
                ", power=" + power +
                '}';
    }
}
