package com.example.level3;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Kiosk {
    private List<MenuItem> menuItems = new ArrayList<>();

    public Kiosk(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }

    public void start() {
        try (Scanner sc = new Scanner(System.in)) {
            while (true) {
                // 메뉴 출력
                showMenu();
                System.out.print("메뉴를 선택하세요: ");

                // 메뉴 입력 받기
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

    private boolean isValidInput(int input) {
        return input > 0 && input <= menuItems.size();
    }

    private void showMenu() {
        for (int i = 0; i < menuItems.size(); i++) {
            MenuItem menu = menuItems.get(i);
            System.out.printf("%d. %s   | W %.1f | %s%n", i + 1, menu.getName(), menu.getPrice(), menu.getDescription());
        }
        System.out.println("0. 종료      | 종료");
    }

    private void displaySelectedMenu(int input) {
        MenuItem selectedMenu = menuItems.get(input - 1);
        System.out.printf("선택한 메뉴: %s | W %.1f | %s%n",
                selectedMenu.getName(),
                selectedMenu.getPrice(),
                selectedMenu.getDescription());
    }
}
