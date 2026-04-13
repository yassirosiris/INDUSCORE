package com.induscore;
import java.util.Random;

public class Machine {
    private String name;
    private double temperature;
    private double vibrationLevel;
    private boolean isRunning;

    public Machine() {
        this.name = "Default Machine";
        this.isRunning = false;
    }

    public Machine(String name) {
        this.name = name;
        this.isRunning = true; 
    }

    public void start() {
        this.isRunning = true;
        System.out.println(name + " is now STARTING...");
    }

    public void stop() {
        this.isRunning = false;
        System.out.println(name + " is now STOPPED.");
    }

    public void updateData() { 
        if (this.isRunning) {
            Random random = new Random();
            this.temperature = 40 + random.nextDouble() * 55; 
            this.vibrationLevel = random.nextDouble() * 10; 
        } else {
            this.vibrationLevel = 0; 
            this.temperature = 25; 
        }
    }

    public double getTemperature() { return temperature; }
    public double getVibrationLevel() { return vibrationLevel; }
    public String getName() { return name; }
    public boolean isRunning() { return isRunning; } // Zid hedhi ken t7eb testi biha
}