package com.example.kiosk6;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Menu burgermenu = new Menu("Burgers");
        Menu drinkmenu = new Menu("Drinks");
        Menu dessertmenu = new Menu("Desserts");

        // add 함수를 통해 new shakeMenu(이름, 가격, 설명) List에 삽입
        burgermenu.addMenuItem(new MenuItem("ShakeBurger", 6.9, "토마토, 양상추, 쉑소스가 토핑된 치즈버거"));
        burgermenu.addMenuItem(new MenuItem("SmokeShake", 8.9, "베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거"));
        burgermenu.addMenuItem(new MenuItem("CheeseBurger", 6.9, "포테이토 번과 비프패티, 치즈가 토핑된 치즈버거"));
        burgermenu.addMenuItem(new MenuItem("HamBurger", 5.4, "비프패티를 기반으로 야채가 들어간 기본버거"));
        drinkmenu.addMenuItem(new MenuItem("Lemonade", 4.5, "매장에서 직접 만드는 상큼한 레몬에이드"));
        drinkmenu.addMenuItem(new MenuItem("Fresh Brewed Iced Tea", 3.7, "직접 유기농 홍차를 우려낸 아이스 티"));
        drinkmenu.addMenuItem(new MenuItem("Abita Root Beer", 5.0, "청량감 있는 독특한 미국식 무알콜 탄산음료"));
        drinkmenu.addMenuItem(new MenuItem("Fountain Soda", 2.9, "코카콜라, 코카콜라 제로, 스프라이트, 환타 오렌지, 환타 그레이프, 환타 파인애플"));
        dessertmenu.addMenuItem(new MenuItem("Classic Hand-Spun Shakes", 6.8, "쫀득하고 진한 커스터드가 들어간 클래식 쉐이크"));
        dessertmenu.addMenuItem(new MenuItem("Floats", 6.8, "부드러운 바닐라 커스터드와 톡톡 터지는 탄산이 만나 탄생한 색다른 음료"));
        dessertmenu.addMenuItem(new MenuItem("CONCRETES", 6.2, "쫀득하고 진한 맛의 커스터드와 다양한 믹스-인이 만나 탄생한 쉐이크쉑만의 디저트 메뉴"));
        dessertmenu.addMenuItem(new MenuItem("Cup & Cones", 5.7, "매일 점포에서 신선하게 제조하는 쫀득하고 진한 아이스크림"));

        List<Menu> menus = Arrays.asList(burgermenu, drinkmenu, dessertmenu);
        Kiosk kiosk = new Kiosk(menus);
        kiosk.start();
    }
}