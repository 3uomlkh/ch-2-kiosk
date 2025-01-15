package com.example.level4;

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
                System.out.print("메뉴를 선택하세요: ");

                if (!sc.hasNextInt()) {
                    System.out.println("숫자를 입력해주세요.");
                    sc.next();
                    continue;
                }

                // 메인 메뉴 입력 받기
                int selectedMenu = sc.nextInt();
                if (selectedMenu == 0) {
                    System.out.println("프로그램을 종료합니다.");
                    break;
                }

            }
        }
    }

    private void showMenu() {
        System.out.println("[ MAIN MENU ]");
        for (int i = 0; i < menus.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, menus.get(i).getCategory());
        }
        System.out.println("0. 종료      | 종료");
    }
}
