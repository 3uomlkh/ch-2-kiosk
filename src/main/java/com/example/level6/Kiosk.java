package com.example.level6;

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
                // 메인 메뉴 출력
                showMenu();

                // 메인 메뉴 입력
                int selectedMenu = getUserInput(sc, "메인 메뉴를 선택하세요: ");
                if (selectedMenu == 0) {
                    System.out.println("프로그램을 종료합니다.");
                    break;
                }

                if (isValidMenu(selectedMenu)) {
                    // 메뉴 아이템 출력
                    showMenuItems(selectedMenu);
                    // 메뉴 아이템 입력 받기
                    int selectedMenuItem = getUserInput(sc, "메뉴를 선택하세요: ");
                    // 0 입력 시 뒤로가기(메인 메뉴)
                    if (selectedMenuItem == 0) continue;
                    // 선택한 메뉴 아이템 출력
                    if (isValidMenuItem(selectedMenu, selectedMenuItem)) {
                        displaySelectedMenuItems(selectedMenu, selectedMenuItem);
                        // 장바구니 추가 질문
                        int cartSelection = getUserInput(sc, "위 메뉴를 장바구니에 추가하시겠습니까?\n1. 확인\t2. 취소\n");
                        if (cartSelection == 1) {
                            // 장바구니 추가
                            int selectedOrderMenu = getUserInput(sc, "아래 메뉴판을 보시고 메뉴를 골라 입력해주세요.\n");
                            showMenu();
                            showOrderMenu();
                        } else {
                            // 취소
                            continue;
                        }
                    } else {
                        System.out.println("올바른 메뉴 아이템 번호를 입력해주세요.");
                    }
                } else { // 메뉴에 없는 값을 입력할 경우
                    System.out.println("올바른 메뉴 번호를 입력해주세요.");
                }
                System.out.println();
            }
        }
    }

    // 메시지 출력 후 사용자 입력이 숫자인지 검사
    private int getUserInput(Scanner sc, String message) {
        System.out.print(message);
        while (!sc.hasNextInt()) {
            System.out.println("숫자를 입력해주세요.");
            sc.next();
            System.out.print(message);
        }
        return sc.nextInt();
    }

    private boolean isValidMenuItem(int menuIndex, int itemIndex) {
        return itemIndex > 0 && itemIndex <= menus.get(menuIndex - 1).getMenuItems().size();
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

    private void showOrderMenu() {
        System.out.println("[ ORDER MENU ]");
        System.out.println("4. Orders       | 장바구니를 확인 후 주문합니다.");
        System.out.println("5. Cancel       | 진행중인 주문을 취소합니다.");
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
