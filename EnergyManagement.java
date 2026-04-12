package com.induscore;
import java.util.Random;
public class EnergyManagement {
    public static void main(String[] args) {
        double energyUsage = simulateEnergyUsage();
        if (energyUsage > 1000) {
            System.out.println("High energy usage detected. Consider rescheduling operations.");
        } else {
            System.out.println("Energy usage is within normal limits.");
        }
    }
    private static double simulateEnergyUsage() {
        Random random = new Random();
        return random.nextDouble() * 2000; // Simulated energy usage in kWh
    }
}