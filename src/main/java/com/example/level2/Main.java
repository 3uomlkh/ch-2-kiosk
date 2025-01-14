package com.example.level2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static final List<MenuItem> menus = new ArrayList<>();
    public static void main(String[] args) {
        initializeMenu();
        runKiosk();
    }

    private static void runKiosk() {
        // try-with-resources 방식으로 블록 종료 후 자동으로 close()
        try (Scanner sc = new Scanner(System.in)) {
            while (true) {
                // 메뉴 출력
                showMenu();

                // 메뉴 입력 받기
                System.out.print("메뉴를 선택하세요: ");
                int input = sc.nextInt();

                if (input == 0) {
                    System.out.println("프로그램을 종료합니다.");
                    break;
                }

                if (isValidInput(input)) { // 입력값이 0 이외의 유효한 값일 경우
                    displaySelectedMenu(input);
                } else { // 메뉴에 없는 값을 입력할 경우
                    System.out.println("올바른 메뉴 번호를 입력해주세요.");
                }
                System.out.println();
            }
        }
    }

    private static boolean isValidInput(int input) {
        return input > 0 && input <= menus.size();
    }

    private static void initializeMenu() {
        menus.add(new MenuItem("ShackBurger", 6.9, "토마토, 양상추, 쉑소스가 토핑된 치즈버거"));
        menus.add(new MenuItem("SmokeShack", 8.9, "베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거"));
        menus.add(new MenuItem("Cheeseburger", 6.9, "포테이토 번과 비프패티, 치즈가 토핑된 치즈버거"));
        menus.add(new MenuItem("Hamburger", 5.4, "비프패티를 기반으로 야채가 들어간 기본버거"));
    }

    private static void showMenu() {
        for (int i = 0; i < menus.size(); i++) {
            MenuItem  menu = menus.get(i);
            System.out.printf("%d. %s   | W %.1f | %s%n", i + 1, menu.getName(), menu.getPrice(), menu.getDescription());
        }
        System.out.println("0. 종료      | 종료");
    }

    private static void displaySelectedMenu(int input) {
        MenuItem selectedMenu = menus.get(input - 1);
        System.out.printf("선택한 메뉴: %s | W %.1f | %s%n",
                selectedMenu.getName(),
                selectedMenu.getPrice(),
                selectedMenu.getDescription());
    }
}
