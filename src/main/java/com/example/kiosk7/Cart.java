package com.example.kiosk7;

import java.util.List;

public class Cart {
    private final MenuItem menuItem;
    private int quantity;

    public Cart(MenuItem menuItem, int quantity) {
        this.menuItem = menuItem;
        this.quantity = quantity;
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }

    public int getQuantity() {
        return quantity;
    }

    public void addQuantity(int quantity) {
        this.quantity += quantity;
    }

    public double getTotalPrice() {
        return menuItem.getPrice() * quantity;
    }

}