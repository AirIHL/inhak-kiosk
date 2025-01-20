package com.example.kiosk7;

public class MenuItem {
    private final String name;
    private final double price;
    private final String describe;

    public MenuItem(String name, double price, String describe) {
        this.name = name;
        this.price = price;
        this.describe = describe;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getDescribe() {
        return describe;
    }
}
