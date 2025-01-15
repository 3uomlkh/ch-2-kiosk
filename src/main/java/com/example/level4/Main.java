package com.example.level4;

import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final List<Menu> menus = new ArrayList<>();
    public static void main(String[] args) {
        initializeMenu();
        Kiosk kiosk = new Kiosk(menus);
        kiosk.start();
    }

    private static void initializeMenu() {
        Menu burgerMenu = new Menu("Burgers");
        burgerMenu.getMenuItems().add(new MenuItem("ShackBurger", 6.9, "토마토, 양상추, 쉑소스가 토핑된 치즈버거"));
        burgerMenu.getMenuItems().add(new MenuItem("SmokeShack", 8.9, "베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거"));
        burgerMenu.getMenuItems().add(new MenuItem("Cheeseburger", 6.9, "포테이토 번과 비프패티, 치즈가 토핑된 치즈버거"));
        burgerMenu.getMenuItems().add(new MenuItem("Hamburger", 5.4, "비프패티를 기반으로 야채가 들어간 기본버거"));

        Menu drinksMenu = new Menu("Drinks");
        drinksMenu.getMenuItems().add(new MenuItem("Coke", 1.9, "콜라"));
        drinksMenu.getMenuItems().add(new MenuItem("Sprite", 1.9, "사이다"));
        drinksMenu.getMenuItems().add(new MenuItem("Water", 1.0, "생수"));

        Menu dessertsMenu = new Menu("Desserts");
        dessertsMenu.getMenuItems().add(new MenuItem("Crème Brûlée", 3.9, "바삭한 설탕층과 부드러운 커스터드가 어우러진 프랑스 디저트"));
        dessertsMenu.getMenuItems().add(new MenuItem("Cookie", 2.5, "갓 구운 초코칩 쿠키"));
        dessertsMenu.getMenuItems().add(new MenuItem("Brownie", 3.0, "쫀득하고 달콤한 브라우니"));

        menus.add(burgerMenu);
        menus.add(drinksMenu);
        menus.add(dessertsMenu);
    }
}
