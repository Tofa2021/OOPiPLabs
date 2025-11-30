package org.example.lab5.model;

public class Computer {
    private int id;
    private String name;
    private boolean isAvailable;
    private double pricePerHour;
    private String type;

    public Computer(int id, String name, double pricePerHour, String type) {
        this.id = id;
        this.name = name;
        this.pricePerHour = pricePerHour;
        this.type = type;
        this.isAvailable = true;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public boolean isAvailable() { return isAvailable; }
    public double getPricePerHour() { return pricePerHour; }
    public String getType() { return type; }
    public void setAvailable(boolean available) { isAvailable = available; }

    @Override
    public String toString() {
        return String.format("%s (%s) - $%.2f/час", name, type, pricePerHour);
    }
}
