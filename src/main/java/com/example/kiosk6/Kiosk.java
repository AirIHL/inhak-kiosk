package com.example.kiosk4;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Kiosk {

    private final List<Cart> carts;
    private final List<Menu> menus;
    private final Scanner scanner;

    public Kiosk(List<Menu> menus) {
        this.carts = new ArrayList<>();
        this.menus = menus;
        this.scanner = new Scanner(System.in);
    }


    public void start() {

        boolean running = true;
        while (running) {
            try {
                showMainMenu();
                int choice = scanner.nextInt();

                if (choice == 0) {
                    running = false;
                    continue;
                }

                if (choice < 0 || choice > menus.size()) {
                    throw new IllegalArgumentException("잘못된 메뉴 번호입니다.");
                }

                if (choice >= 1 && choice <= menus.size()) {
                    Menu selectedMenu = menus.get(choice - 1);
                    selectedMenu.showMenu();

                    int menuChoice = scanner.nextInt();
                    if (menuChoice == 0) continue;

                    if (menuChoice < 0 || menuChoice > selectedMenu.getMenuItems().size()) {
                        throw new IllegalArgumentException("잘못된 메뉴 항목 번호입니다.");
                    }

                    MenuItem selectedItem = selectedMenu.getMenuItems().get(menuChoice - 1);
                    System.out.println("수량을 입력하세요: ");
                    int quantity = scanner.nextInt();

                    if (quantity <= 0) {
                        throw new IllegalArgumentException("수량은 1개 이상이어야 합니다.");
                    }

                    addToCart(selectedItem, quantity);
                    showCart();
                }
            } catch (IllegalArgumentException e) {
                System.out.println("오류: " + e.getMessage());
                scanner.nextLine(); // 입력 버퍼 비우기
            } catch (Exception e) {
                System.out.println("잘못된 입력입니다. 다시 시도해주세요.");
                scanner.nextLine(); // 입력 버퍼 비우기
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

    private void addToCart(MenuItem item, int quantity) {
        for (Cart cartItem : carts) {
            if (cartItem.getMenuItem().getName().equals(item.getName())) {
                cartItem.addQuantity(quantity);
                return;
            }
        }
        carts.add(new Cart(item, quantity));
    }

    private void showCart() {
        System.out.println("\n[ 장바구니 ]");
        if (carts.isEmpty()) {
            System.out.println("장바구니가 비어있습니다.");
            return;
        }

        double totalPrice = 0;
        for (Cart item : carts) {
            MenuItem menuItem = item.getMenuItem();
            System.out.printf("%s - %d개 | W%.1f%n",
                    menuItem.getName(),
                    item.getQuantity(),
                    item.getTotalPrice());
            totalPrice += item.getTotalPrice();
        }
        System.out.printf("\n총 금액: W%.1f%n", totalPrice);
        System.out.println();
    }
}