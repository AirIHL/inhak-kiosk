package com.example.kiosk2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        List<MenuItem> menuItems = new ArrayList<>();   // List 선언

        // add 함수를 통해 new MenuItem(이름, 가격, 설명) List에 삽입
        menuItems.add(new MenuItem("ShakeBurger", 6.9, "토마토, 양상추, 쉑소스가 토핑된 치즈버거"));
        menuItems.add(new MenuItem("SmokeShake", 8.9, "베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거"));
        menuItems.add(new MenuItem("CheeseBurger", 6.9, "포테이토 번과 비프패티, 치즈가 토핑된 치즈버거"));
        menuItems.add(new MenuItem("HamBurger", 5.4, "비프패티를 기반으로 야채가 들어간 기본버거"));

        // Scanner 선언
        Scanner scan = new Scanner(System.in);

        boolean running = true;     // while문의 조건 boolean
        while(running) {

            // 출력 부분
            System.out.println("[ SHAKE SHAKE MENU ]");
            // 반복문을 활용해 List 안에 있는 MenuItem을 하나씩 출력
            for (int i = 0; i < menuItems.size(); i++) {
                MenuItem item = menuItems.get(i);
                System.out.printf("%-2d. %-15s | W%-4.1f | %s%n", (i + 1), item.getName(), item.getPrice(), item.getDescribe());
            }
            System.out.printf("%-2d. %-10s | %s%n", 0, "종료", "종료");

            int choice = scan.nextInt();    // 사용자로부터 숫자 입력 받음

            // 입력 받은 숫자를 바탕으로 수행할 로직을 실행
            switch (choice) {
                case 0:
                    running = false;
                    break;
                case 1:
                    System.out.printf("%-15s | W%-4.1f | %s%n", menuItems.get(0).getName(), menuItems.get(0).getPrice(), menuItems.get(0).getDescribe());
                    System.out.println("");
                    break;
                case 2:
                    System.out.printf("%-15s | W%-4.1f | %s%n", menuItems.get(1).getName(), menuItems.get(1).getPrice(), menuItems.get(1).getDescribe());
                    System.out.println("");
                    break;
                case 3:
                    System.out.printf("%-15s | W%-4.1f | %s%n", menuItems.get(2).getName(), menuItems.get(2).getPrice(), menuItems.get(2).getDescribe());
                    System.out.println("");
                    break;
                case 4:
                    System.out.printf("%-15s | W%-4.1f | %s%n", menuItems.get(3).getName(), menuItems.get(3).getPrice(), menuItems.get(3).getDescribe());
                    System.out.println("");
                    break;
                default:
                    System.out.println("숫자를 잘 못 입력하셨습니다!");
                    System.out.println("다시 입력해 주세요");
                    System.out.println("");
            }
        }
    }
}