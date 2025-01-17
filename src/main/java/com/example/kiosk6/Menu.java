package com.example.kiosk6;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    private final String name;
    private final List<MenuItem> menuItems;
    private final List<Cart> carts;

    public Menu(String name) {
        this.name = name;
        this.menuItems = new ArrayList<>();
        this.carts = new ArrayList<>();
    }

    public void addMenuItem(MenuItem item) {
        menuItems.add(item);
    }

    public String getName() {
        return name;
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public List<Cart> getCarts() {
        return carts;
    }

    public void showMenu() {
        System.out.println("[ " + name + " MENU ]");
        for (int i = 0; i < menuItems.size(); i++) {
            MenuItem item = menuItems.get(i);
            System.out.printf("%-2d. %-15s | W%-4.1f | %s%n",
                    (i + 1),
                    item.getName(),
                    item.getPrice(),
                    item.getDescribe());
        }
        System.out.printf("%-2d. %-10s | %s%n", 0, "종료", "종료");
    }
}
