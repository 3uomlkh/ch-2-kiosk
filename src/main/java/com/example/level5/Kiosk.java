package com.example.level5;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Kiosk {
    private List<Menu> menus = new ArrayList<>();

    public Kiosk(List<Menu> menus) {
        this.menus = menus;
    }

    public void start() {
        try (Scanner sc = new Scanner(System.in)) {
            while (true) {
                // 메뉴 출력
                showMenu();
                System.out.print("메인 메뉴를 선택하세요: ");

                if (!sc.hasNextInt()) {
                    System.out.println("숫자를 입력해주세요.");
                    sc.next();
                    continue;
                }

                // 메뉴 입력 받기
                int selectedMenu = sc.nextInt();
                if (selectedMenu == 0) {
                    System.out.println("프로그램을 종료합니다.");
                    break;
                }

                if (isValidMenu(selectedMenu)) {
                    // 메뉴 아이템 출력
                    showMenuItems(selectedMenu);
                    // 메뉴 아이템 입력 받기
                    System.out.print("메뉴를 선택하세요: ");
                    int selectedMenuItem = sc.nextInt();
                    // 0 입력 시 뒤로가기(메인 메뉴)
                    if (selectedMenuItem == 0) continue;
                    // 선택한 메뉴 아이템 출력
                    displaySelectedMenuItems(selectedMenu, selectedMenuItem);
                } else { // 메뉴에 없는 값을 입력할 경우
                    System.out.println("올바른 메뉴 번호를 입력해주세요.");
                }
                System.out.println();
            }
        }
    }

    private boolean isValidMenu(int input) {
        return input > 0 && input <= menus.size();
    }

    private void showMenu() {
        System.out.println("[ MAIN MENU ]");
        for (int i = 0; i < menus.size(); i++) {
            System.out.printf("%d. %s", i + 1, menus.get(i).getCategory());
            System.out.println();
        }
        System.out.println("0. 종료      | 종료");
    }

    private void showMenuItems(int input) {
        System.out.printf("[ %s MENU ]", menus.get(input - 1).getCategory());
        System.out.println();
        int size = menus.get(input - 1).getMenuItems().size();
        for (int i = 0; i < size; i++) {
            MenuItem menuItem = menus.get(input - 1).getMenuItems().get(i);
            System.out.printf("%d. %s   | W %.1f | %s%n", i + 1,
                    menuItem.getName(), menuItem.getPrice(), menuItem.getDescription());
        }
        System.out.println("0. 뒤로가기");
    }

    private void displaySelectedMenuItems(int i1, int i2) {
        MenuItem menuItem = menus.get(i1 - 1).getMenuItems().get(i2 - 1);
        System.out.printf("선택한 메뉴: %s | W %.1f | %s%n",
                menuItem.getName(),
                menuItem.getPrice(),
                menuItem.getDescription());
    }
}
