package com.example.kiosk6;

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
                    System.out.printf("선택한 메뉴: %-15s | W%-4.1f | %s%n", selectedItem.getName(), selectedItem.getPrice(), selectedItem.getDescribe());
                    System.out.println("위 메뉴를 장바구니에 추가하시겠습니까?");
                    System.out.println("1. 확인          2. 취소");

                    int inCart = scanner.nextInt();
                    if (inCart == 2)
                        continue;
                    else if (inCart != 1)
                        throw new IllegalArgumentException("잘못된 입력입니다.");

                    System.out.println("수량을 입력하세요: ");
                    int quantity = scanner.nextInt();

                    if (quantity <= 0) {
                        throw new IllegalArgumentException("수량은 1개 이상이어야 합니다.");
                    }

                    addToCart(selectedItem, quantity);

                    System.out.println(selectedItem.getName() + " " + quantity + "개가 장바구니에 추가되었습니다.");

                    if (!carts.isEmpty()) {
                        System.out.println("[ ORDER MENU ]");
                        System.out.println("4. Orders");
                        System.out.println("5. Cancel");
                        int orderDecision = scanner.nextInt();
                        if (orderDecision == 4) {
                            System.out.println("아래와 같이 주문하시겠습니까?");
                            System.out.println();
                            showCart();
                            System.out.println();
                            totalPrice();
                            System.out.println();
                            System.out.println("1. 주문          2. 메뉴판");

                            int finalDecision = scanner.nextInt();

                            if (finalDecision == 1) {

                                System.out.println();
                                double totalPrice = 0;

                                for (Cart item : carts) {
                                    MenuItem menuItem = item.getMenuItem();
                                    totalPrice += item.getTotalPrice();
                                }
                                System.out.println("주문이 완료되었습니다. 금액은 W " + totalPrice + " 입니다.");
                                System.out.println();
                                System.out.println();
                                carts.clear();
                                continue;
                            } else if (finalDecision == 2) {
                                continue;
                            } else
                                throw new IllegalArgumentException("잘못된 입력입니다.");

                        } else if (orderDecision == 5) {
                            System.out.println("주문을 취소합니다.");
                            continue;
                        } else
                            throw new IllegalArgumentException("잘못된 입력입니다.");
                    }
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
        for (Cart item : carts) {
            MenuItem menuItem = item.getMenuItem();
            System.out.printf("%-15s | W%-4.1f | %s - %d개%n", menuItem.getName(), menuItem.getPrice(), menuItem.getDescribe(), item.getQuantity());
        }
    }

    private void totalPrice() {

        System.out.println("[ TOTAL ]");

        double totalPrice = 0;
        for (Cart item : carts) {
            MenuItem menuItem = item.getMenuItem();
            totalPrice += item.getTotalPrice();
        }
        System.out.printf("W%.1f%n", totalPrice);
        System.out.println();
    }

}