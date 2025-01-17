package com.example.kiosk4;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    private final String name;
    private final List<MenuItem> menuItems;

    public Menu(String name) {
        this.name = name;
        this.menuItems = new ArrayList<>();
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