package com.example.level2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<MenuItem> menus = new ArrayList<>();
        menus.add(new MenuItem("ShackBurger", 6.9, "토마토, 양상추, 쉑소스가 토핑된 치즈버거"));
        menus.add(new MenuItem("SmokeShack", 8.9, "베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거"));
        menus.add(new MenuItem("Cheeseburger", 6.9, "포테이토 번과 비프패티, 치즈가 토핑된 치즈버거"));
        menus.add(new MenuItem("Hamburger", 5.4, "비프패티를 기반으로 야채가 들어간 기본버거"));

        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < menus.size(); i++) {
            String name = menus.get(i).getName();
            double price = menus.get(i).getPrice();
            String description = menus.get(i).getDescription();

            System.out.println(i+1 + ". " + name + "   | W " + price + " | " + description);
        }
        System.out.println("0. 종료      | 종료");
    }
}
