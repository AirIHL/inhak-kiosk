package com.example.kiosk1;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        boolean running = true;     // while문의 조건 boolean
        while(running) {
            // 햄버거 메뉴 정보를 넣은 배열
            String[] names = {"ShakeBurger", "SmokeShake", "CheeseBurger", "HamBurger"};
            double[] price = {6.9, 8.9, 6.9, 5.4};
            String[] describe = {"토마토, 양상추, 쉑소스가 토핑된 치즈버거", "베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거",
                    "포테이토 번과 비프패티, 치즈가 토핑된 치즈버거", "비프패티를 기반으로 야채가 들어간 기본버거"};
            // 출력 부분
            System.out.println("[ SHAKE SHAKE MENU ]");
            for (int i = 0; i < 4; i++) {
                System.out.printf("%-2d. %-15s | W%-4.1f | %s%n", (i + 1), names[i], price[i], describe[i]);
            }
            System.out.printf("%-2d. %-10s | %s%n", 0, "종료", "종료");

            int choice = scan.nextInt();    // 사용자로부터 숫자 입력 받음

            // 입력 받은 숫자를 바탕으로 수행할 로직을 실행
            switch (choice) {
                case 0:
                    running = false;
                    break;
                case 1:
                    System.out.println("ShakeBurger");
                    break;
                case 2:
                    System.out.println("SmokeShake");
                    break;
                case 3:
                    System.out.println("CheeseBurger");
                    break;
                case 4:
                    System.out.println("HamBurger");
                    break;
                default:
                    System.out.println("숫자를 잘 못 입력하셨습니다!");
                    System.out.println("다시 입력해 주세요");
            }
        }
    }
}