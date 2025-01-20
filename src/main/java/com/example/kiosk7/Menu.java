package com.example.kiosk7;

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
        menuItems.stream()
                .forEach(menuItem -> System.out.printf("%-2d. %-15s | W%-4.1f | %s%n",
                        menuItems.indexOf(menuItem) + 1,
                        menuItem.getName(),
                        menuItem.getPrice(),
                        menuItem.getDescribe()));
        System.out.printf("%-2d. %-10s | %s%n", 0, "종료", "종료");
    }
}
