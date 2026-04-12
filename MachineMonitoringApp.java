package com.induscore;
import java.util.Timer;
import java.util.TimerTask;
public class MachineMonitoringApp {
    private static final double TEMP_THRESHOLD = 90.0;
    private static final double VIBRATION_THRESHOLD = 7.5;
    public static void main(String[] args) {
        Machine machine = new Machine("CNC Lathe");
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
    @Override
    public void run() {
        machine.updateData();
        monitorMachine(machine);
    }
    }, 0, 2000); // Update every 2 seconds
}
    private static void monitorMachine(Machine machine) {
        double temperature = machine.getTemperature();
        double vibration = machine.getVibrationLevel();
        System.out.println("Monitoring " + machine.getName() + ":");
        System.out.println("Temperature: " + temperature + "°C");
        System.out.println("Vibration Level: " + vibration);
        if (temperature > TEMP_THRESHOLD) {
        System.out.println("Alert: Temperature exceeds safe limits!");
        }
        if (vibration > VIBRATION_THRESHOLD) {
        System.out.println("Alert: Vibration level exceeds safe limits!");
        }
        System.out.println("------");
    }
}