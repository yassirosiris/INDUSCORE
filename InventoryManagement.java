package com.induscore;
import java.util.HashMap;
import java.util.Map;
public class InventoryManagement {
    private Map<String, Integer> inventory = new HashMap<>();
    public void addStock(String item, int quantity) {
        inventory.put(item, inventory.getOrDefault(item, 0) + quantity);
    }
    public void checkInventory(String item) {
        int stock = inventory.getOrDefault(item, 0);
        if (stock < 20) {
            System.out.println("Alert: Low stock on " + item + ". Current stock: " + stock);
        } else {
            System.out.println(item + " stock is sufficient. Current stock: " + stock);
        }
    }
    public static void main(String[] args) {
        InventoryManagement inventoryManagement = new
        InventoryManagement();
        inventoryManagement.addStock("Widget", 15);
        inventoryManagement.checkInventory("Widget");
    }
}
