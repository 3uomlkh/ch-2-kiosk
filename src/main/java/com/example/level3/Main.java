package com.example.level3;

import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final List<MenuItem> menuItems = new ArrayList<>();
    public static void main(String[] args) {
        initializeMenu();
        Kiosk kiosk = new Kiosk(menuItems);
        kiosk.start();
    }

    private static void initializeMenu() {
        System.out.println("[ SHAKESHACK MENU ]");
        menuItems.add(new MenuItem("ShackBurger", 6.9, "토마토, 양상추, 쉑소스가 토핑된 치즈버거"));
        menuItems.add(new MenuItem("SmokeShack", 8.9, "베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거"));
        menuItems.add(new MenuItem("Cheeseburger", 6.9, "포테이토 번과 비프패티, 치즈가 토핑된 치즈버거"));
        menuItems.add(new MenuItem("Hamburger", 5.4, "비프패티를 기반으로 야채가 들어간 기본버거"));
    }
}
