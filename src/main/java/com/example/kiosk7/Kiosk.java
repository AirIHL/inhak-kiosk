package com.example.kiosk7;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Kiosk {

    private final List<Cart> carts;
    private final List<Menu> menus;
    private final Scanner scanner;

    CustomerType soldier = CustomerType.SOLDIER;
    CustomerType student = CustomerType.STUDENT;
    CustomerType ordinary = CustomerType.ORDINARY;
    CustomerType crew = CustomerType.CREW;

    public Kiosk(List<Menu> menus) {
        this.carts = new ArrayList<>();
        this.menus = menus;
        this.scanner = new Scanner(System.in);
    }


    public void start() {

        boolean running = true;
        while (running) {
            try {

                showMainMenu();     // 메인메뉴 호출
                System.out.println();

                int choice = scanner.nextInt();     // 카테고리 선택

                System.out.println();

                // 입력받은 숫자를 토대로 어떤 작업을 수행할지 if 문으로 판단
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

                    // 선택한 메뉴를 보여줌
                    MenuItem selectedItem = selectedMenu.getMenuItems().get(menuChoice - 1);
                    System.out.println();
                    System.out.printf("선택한 메뉴: %-15s | W%-4.1f | %s%n", selectedItem.getName(), selectedItem.getPrice(), selectedItem.getDescribe());
                    System.out.println();
                    System.out.println("위 메뉴를 장바구니에 추가하시겠습니까?");
                    System.out.println("1. 확인          2. 취소");

                    //  메뉴를 장바구니에 추가 할지 입력받고 그 값에 따라 수행
                    int inCart = scanner.nextInt();
                    if (inCart == 2)
                        continue;
                    else if (inCart != 1)
                        throw new IllegalArgumentException("잘못된 입력입니다.");


                    // 수량 입력 및 오류 출력
                    System.out.println();
                    System.out.println("수량을 입력하세요: ");
                    int quantity = scanner.nextInt();

                    if (quantity <= 0) {
                        throw new IllegalArgumentException("수량은 1개 이상이어야 합니다.");
                    }

                    addToCart(selectedItem, quantity);

                    System.out.println();
                    System.out.println(selectedItem.getName() + " " + quantity + "개가 장바구니에 추가되었습니다.");
                    System.out.println();

                    //  장바구니에 메뉴가 있으면 Order 출력
                    if (!carts.isEmpty()) {
                        System.out.println("[ ORDER MENU ]");
                        System.out.println();
                        System.out.println("4. Orders");
                        System.out.println("5. Cancel");
                        int orderDecision = scanner.nextInt();
                        if (orderDecision == 4) {
                            System.out.println();
                            System.out.println("아래와 같이 주문하시겠습니까?");
                            System.out.println();
                            showCart();
                            System.out.println();
                            totalPrice();
                            System.out.println();
                            System.out.println("1. 주문          2. 메뉴판");
                            System.out.println();

                            //  메뉴를 더 추가할지 아니면 이대로 주문할지 입력 받음
                            int finalDecision = scanner.nextInt();

                            if (finalDecision == 1) {

                                //  할인정보 입력 받음
                                System.out.println();
                                System.out.println("할인 정보를 입력해주세요.");
                                System.out.println();
                                System.out.println("1. 일반인    2. 군인    3. 학생    4. 직원");
                                int cType = scanner.nextInt();
                                System.out.println();

                                double totalPrice = 0;
                                double discountPrice = 0;

                                for (Cart item : carts) {
                                    MenuItem menuItem = item.getMenuItem();
                                    totalPrice += item.getTotalPrice();
                                }

                                System.out.println("금액은 W " + totalPrice + " 입니다.");
                                System.out.println();

                                switch (cType) {
                                    case 1:
                                        System.out.println(ordinary.getCustomerType() + "은 할인이 없습니다.");
                                        break;
                                    case 2:
                                        System.out.println(soldier.getCustomerType() + "은 할인이 5% 됩니다.");
                                        discountPrice = totalPrice - (totalPrice * 0.05);
                                        System.out.println("할인 후 금액은 W " + discountPrice + " 입니다.");
                                        break;
                                    case 3:
                                        System.out.println(student.getCustomerType() + "은 할인이 3% 됩니다.");
                                        discountPrice = totalPrice - (totalPrice * 0.03);
                                        System.out.println("할인 후 금액은 W " + discountPrice + " 입니다.");
                                        break;
                                    case 4:
                                        System.out.println(crew.getCustomerType() + "은 할인이 10% 됩니다.");
                                        discountPrice = totalPrice - (totalPrice * 0.1);
                                        System.out.println("할인 후 금액은 W " + discountPrice + " 입니다.");
                                        break;
                                }
                                System.out.println();
                                System.out.println("주문이 완료 되었습니다!");
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
        carts.stream()
                .forEach(item -> System.out.printf("%-15s | W%-4.1f | %s - %d개%n",
                        item.getMenuItem().getName(),
                        item.getMenuItem().getPrice(),
                        item.getMenuItem().getDescribe(),
                        item.getQuantity()));
    }

    private void totalPrice() {

        System.out.println("[ TOTAL ]");

        double totalPrice = carts.stream()
                .mapToDouble(Cart::getTotalPrice)
                .sum();
        System.out.printf("W%.1f%n", totalPrice);
        System.out.println();
    }
}