package com.example.kiosk4;

import java.util.List;
import java.util.Scanner;

public class Kiosk {

    private final List<Menu> menus;
    private final Scanner scanner;

    public Kiosk(List<Menu> menus) {
        this.menus = menus;
        this.scanner = new Scanner(System.in);
    }


    public void start() {

        boolean running = true;
        while (running) {
            showMainMenu();
            int choice = scanner.nextInt();

            if (choice == 0) {
                running = false;
                continue;
            }

            if (choice >= 1 && choice <= menus.size()) {
                Menu selectedMenu = menus.get(choice - 1);
                selectedMenu.showMenu();

                int menuChoice = scanner.nextInt();
                if (menuChoice != 0) {
                    MenuItem selectedItem = selectedMenu.getMenuItems().get(menuChoice - 1);
                    System.out.printf("선택하신 메뉴: %s | W%.1f | %s%n",
                            selectedItem.getName(),
                            selectedItem.getPrice(),
                            selectedItem.getDescribe());
                }
            }
        }
    }

    private void showMainMenu() {
        System.out.println("[ MAIN MENU ]");
        for (int i = 0; i < menus.size(); i++) {
            System.out.printf("%-2d. %-10s%n", (i + 1), menus.get(i).getName());
        }
        System.out.printf("%-2d. %-10s | %s%n", 0, "종료", "종료");
    }
}