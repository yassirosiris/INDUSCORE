package com.induscore;
public class PredictiveMaintenance {
    private static final double TEMP_THRESHOLD = 90.0;
    private static final double VIBRATION_THRESHOLD = 7.5;

    public void checkStatus(Machine machine) {
        double temp = machine.getTemperature();
        double vibration = machine.getVibrationLevel();

        System.out.println("Analyzing " + machine.getName() + "...");

        if (temp > TEMP_THRESHOLD || vibration > VIBRATION_THRESHOLD) {
            System.out.println("!!! ALERT: Maintenance required for " + machine.getName() + " !!!");
            if (temp > TEMP_THRESHOLD) System.out.printf("Reason: Overheating (%.2f°C)%n", temp);
            if (vibration > VIBRATION_THRESHOLD) System.out.printf("Reason: Excessive Vibration (%.2f)%n", vibration);
        } else {
            System.out.println("Status: Normal operations.");
        }
        System.out.println("------");
    }

    public static void main(String[] args) {
        Machine machine = new Machine("CNC Lathe");
        PredictiveMaintenance pm = new PredictiveMaintenance();
        
        for (int i = 0; i < 5; i++) {
            machine.updateData(); 
            pm.checkStatus(machine);
            try { Thread.sleep(2000); } catch (InterruptedException e) {}
        }
    }
}